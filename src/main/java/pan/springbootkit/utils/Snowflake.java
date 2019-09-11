package pan.springbootkit.utils;

/**
 * Twitter 的分布式 id 生成算法
 *
 * SnowFlake的优点是：
 *      整体上按照时间自增排序，
 *      并且整个分布式系统内不会产生 id 碰撞(由数据中心 id 和机器 id 作区分)，
 *      并且效率较高，
 *      经测试，SnowFlake 每秒能够产生 26 万 id 左右。
 *      忽略低位大概算一下：1e18 / (3600 * 24 * 365 * 1000 * 2^22) ≈ 7.56年，即时间差超过7.56年，就会达到19位
 *          如果设置为 21 位可变字符串的 id，估计能用个 756 年，足够了
 *
 * Created by panzhangbao on 2018-12-23 23:06:47
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
public class Snowflake {

	/**
	 * 开始时间截 (2019-03-15 12:34:56)
	 *  从当天开始 id 为 14 位
	 */
	private final long twepoch = 1552624496000L;

	/**
	 * 机器 id 所占的位数
	 */
	private final long workerIdBits = 5L;

	/**
	 * 数据标识 id 所占的位数
	 */
	private final long datacenterIdBits = 5L;

	/**
	 * 支持的最大机器 id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
	 */
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

	/**
	 * 支持的最大数据标识 id，结果是 31
	 */
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

	/**
	 * 序列在 id 中占的位数
	 */
	private final long sequenceBits = 12L;

	/**
	 * 机器 id 向左移 12 位
	 */
	private final long workerIdShift = sequenceBits;

	/**
	 * 数据标识 id 向左移17位(12 + 5)
	 */
	private final long datacenterIdShift = sequenceBits + workerIdBits;

	/**
	 * 时间截向左移22位(5 + 5 + 12)
	 */
	private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	/**
	 * 生成序列的掩码，这里为 4095 (0b111111111111 = 0xfff = 4095)
	 */
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	/**
	 * 工作机器 id(0 ~ 31)
	 */
	private long workerId;

	/**
	 * 数据中心 id(0 ~ 31)
	 */
	private long datacenterId;

	/**
	 * 毫秒内序列(0 ~ 4095)
	 */
	private long sequence = 0L;

	/**
	 * 上次生成 id 的时间截
	 */
	private long lastTimestamp = -1L;

	private static Snowflake instance = null;

	public static Snowflake getInstance() {
		if (instance == null) {
			Class var0 = Snowflake.class;
			synchronized(Snowflake.class) {
				if (instance == null) {
					instance = new Snowflake(0L, 0L);
				}
			}
		}

		return instance;
	}

	//==============================Constructors=====================================
	/**
	 * 构造函数
	 * @param workerId 工作 id(0 ~ 31)
	 * @param datacenterId 数据中心 id(0 ~ 31)
	 */
	private Snowflake(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	// ==============================Methods==========================================
	/**
	 * 获得下一个 id(该方法是线程安全的)
	 * @return SnowflakeId
	 */
	private synchronized long nextId() {
		long timestamp = timeGen();

		//如果当前时间小于上一次 id 生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(
					String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}

		//如果是同一时间生成的，则进行毫秒内序列
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			//毫秒内序列溢出
			if (sequence == 0) {
				//阻塞到下一个毫秒,获得新的时间戳
				timestamp = tilNextMillis(lastTimestamp);
			}
		}
		//时间戳改变，毫秒内序列重置
		else {
			sequence = 0L;
		}

		//上次生成 id 的时间截
		lastTimestamp = timestamp;

		//移位并通过或运算拼到一起组成64位的 id
		return ((timestamp - twepoch) << timestampLeftShift)
				| (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift)
				| sequence;
	}

	/**
	 * 阻塞到下一个毫秒，直到获得新的时间戳
	 * @param lastTimestamp 上次生成 id 的时间截
	 * @return 当前时间戳
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * 返回以毫秒为单位的当前时间
	 * @return 当前时间(毫秒)
	 */
	private long timeGen() {
		return System.currentTimeMillis();
	}
}
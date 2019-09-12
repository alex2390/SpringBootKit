package pan.springbootkit.utils.algorithm.sort.allocation;

import pan.springbootkit.utils.algorithm.sort.BaseSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序
 *
 * Created by panzhangbao on 2017/6/20.
 */
public class RadixSort extends BaseSort {

	/**
	 * 基本思想：
 	 * 		将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
	 *      然后，从最低位开始，依次进行一次排序。
     *      这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
	 * 性能分析：
	 * 		时间复杂度为O (nlog(r)m)，其中r为所采取的基数，而m为堆数
	 * 稳定性：
	 * 		比其他的稳定
	 */
    public static void sort(int[] sortArray){
        /**
         * 首先确定排序的趟数
         */
        // 最大值
        int max = sortArray[0];
        for(int i = 1; i < sortArray.length; i++){
            if(sortArray[i] > max){
                max = sortArray[i];
            }
        }

        // 最大值的位数
        int maxLength = String.valueOf(max).length();

        // 建立 10 个队列
        List<ArrayList> queue = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            queue.add(new ArrayList<Integer>());
        }

        // 进行 maxLength 次分配和收集
        for(int i = 0; i < maxLength; i++){
            // 分配数组元素
            for(int j = 0; j < sortArray.length; j++){
                // 得到数字的第 maxLength + 1 位数
                int x = sortArray[j] % (int)Math.pow(10, i + 1) / (int)Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(sortArray[j]);
                queue.set(x, queue2);
            }

			// 元素计数器
            int count = 0;

            // 收集队列元素
            for(int k = 0; k < 10; k++){
                while(queue.get(k).size() > 0){
                    ArrayList<Integer> queue3 = queue.get(k);
                    sortArray[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }

        toArray("基数排序：", sortArray);
    }
}

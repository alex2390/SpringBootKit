package pan.springbootkit.utils.algorithm.sort.insert;

import pan.springbootkit.utils.algorithm.sort.BaseSort;

/**
 * 希尔排序
 *
 * Created by panzhangbao on 2017/6/19.
 */
public class ShellSort extends BaseSort {

	/**
	 * 基本思想：
	 *      算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，
	 *      每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
	 *      然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。
	 *      当增量减到1时，进行直接插入排序后，排序完成。
	 *
	 * 性能分析：
	 *  1、空间复杂度O(1)。
	 *  2、时间复杂度：由于希尔排序的时间复杂度依赖于增量序列的函数。
	 *      当n在某个特定的范围时，希尔的时间复杂度约为O(n^1.3)，最坏的情况下希尔排序的时间复杂度为O(n^2);
	 *      到目前为止，尚未得出一个最好的增量方法序列，
	 *      希尔提出的方法是d[1]=n/2,d[t]=d[t-1]/2,其中，增量取整数。
	 *
	 * 稳定性：不稳定
	 */
    public static void sort(int[] sortArray){

        double d1 = sortArray.length;
        int temp;

        while(true) {
            d1 = (int) Math.ceil(d1 / 2);
            int d = (int)d1;

            for (int x = 0; x < d; x++) {

                for (int i = x + d; i < sortArray.length; i += d) {
                    int j = i - d;
                    temp = sortArray[i];
                    for (; j >= 0 && temp < sortArray[j]; j -= d) {
                        sortArray[j + d] = sortArray[j];
                    }
                    sortArray[j + d] = temp;
                }
            }

            if (d == 1) {
                break;
            }
        }

        toArray("希尔排序：", sortArray);
    }

}

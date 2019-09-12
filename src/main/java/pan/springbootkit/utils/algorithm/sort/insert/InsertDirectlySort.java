package pan.springbootkit.utils.algorithm.sort.insert;


import pan.springbootkit.utils.algorithm.sort.BaseSort;

/**
 * 直接插入排序
 *
 * Created by panzhangbao on 2017/6/19.
 */
public class InsertDirectlySort extends BaseSort {

    /**
	 * 基本思想：
     *      在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排好顺序的，
     *      现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。
     *      如此反复循环，直到全部排好顺序。
	 * 性能分析：
	 * 		时间复杂度为O(n^2)
	 * 稳定性：
	 * 		稳定
     */
   public static void sort(int[] sortArray){
        int temp;

        for (int i = 0; i < sortArray.length; i++) {
            int j = i - 1;
            temp = sortArray[i];
            for (; j >= 0 && temp < sortArray[j]; j--){
                sortArray[j + 1] = sortArray[j];
            }
            sortArray[j + 1] = temp;
        }

        toArray("直接插入排序：",sortArray);
    }
}

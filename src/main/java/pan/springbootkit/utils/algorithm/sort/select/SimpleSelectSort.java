package pan.springbootkit.utils.algorithm.sort.select;

import pan.springbootkit.utils.algorithm.sort.BaseSort;

/**
 * 简单选择排序
 *
 * Created by panzhangbao on 2017/6/20.
 */
public class SimpleSelectSort extends BaseSort{

	/**
	 * 基本思想：
	 *      在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
	 *      然后在剩下的数当中再找最小的与第二个位置的数交换，
	 *      如此循环到倒数第二个数和最后一个数比较为止。
	 */
    public static void sort(int[] sortArray){
        //  每趟最小值的下标位置
        int position;
        //  每趟排序最小值
        int minValue;

        for(int i = 0; i < sortArray.length; i++){
            position = i;
            minValue = sortArray[i];

            for(int j = i + 1; j < sortArray.length; j++){
                if(sortArray[j] < minValue){
                    position = j;
                    minValue = sortArray[j];
                }
            }
            sortArray[position] = sortArray[i];
            sortArray[i] = minValue;
        }

        toArray("简单选择排序：", sortArray);
    }
}

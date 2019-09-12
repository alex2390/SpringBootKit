package pan.springbootkit.utils.algorithm.sort.swap;

import pan.springbootkit.utils.algorithm.sort.BaseSort;

/**
 * 冒泡排序
 *
 * Created by panzhangbao on 2017/6/20.
 */
public class BubbleSort extends BaseSort{

    /**
     * 正向冒泡
     * @param sortArray
     */
    public static void sort(int[] sortArray){
        //  排序标志，默认为未排序
        boolean isSorted;

        for(int i = 0; i < sortArray.length - 1; i++){
            isSorted = false;

            for(int j = i + 1; j < sortArray.length && !isSorted; j++){
                isSorted = true;
                if(sortArray[j - 1] > sortArray[j]){
                    swap(sortArray, j -1, j);
                    isSorted = false;
                }
            }
        }
        toArray("正向冒泡排序：" , sortArray);
    }

    /**
     * 反向冒泡
     * @param sortArray
     */
    public static void sortMin(int[] sortArray){
        //  排序标志，默认为未排序
        boolean isSorted;

        for(int i = sortArray.length - 1; i > 0; i--){
            isSorted = false;

            for(int j = sortArray.length - i; j > 0 && !isSorted; j--){
                isSorted = true;
                if(sortArray[j - 1] > sortArray[j]){
                    swap(sortArray, j -1, j);
                    isSorted = false;
                }
            }
        }

        toArray("反向冒泡排序：" , sortArray);
    }

    /**
     * 推荐使用此方法
     *
	 * @param sortArray
     */
    public static void sortImproved(int[] sortArray){

        int low = 0;
        int high = sortArray.length - 1;

        //  正向冒泡，确定最大值
        while (low < high) {
            for (int i = low; i < high; i++) {
                if (sortArray[i] > sortArray[i + 1]) {
                    swap(sortArray, i, i + 1);
                }
            }

            high--;

            //  反向冒泡，确定最小值
            for (int i = high; i > low; --i) {
                if (sortArray[i - 1] > sortArray[i]) {
                    swap(sortArray, i -1, i);
                }
            }

            low++;
        }

        toArray("双冒泡排序：" , sortArray);
    }

}

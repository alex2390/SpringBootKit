package pan.springbootkit.utils.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 基础排序
 *
 * Created by panzhangbao on 2017/6/19.
 */
@Slf4j
public class BaseSort {
    /**
     * 打印数组
     *
	 * @param array 要打印的数组
     */
    public static void toArray(int array[]){
    	StringBuilder s = new StringBuilder("\n");
        for (int i: array) {
            s.append(i).append(", ");
        }

       log.info(s.substring(0, s.length() - 2));
    }

    /**
     * 打印数组
     *
	 * @param array 要打印的数组
     * @param tip   提示内容
     */
    public static void toArray(String tip, int array[]){
        log.info(tip);

        toArray(array);
    }

    /**
     * 更改数组中两个元素位置
     *
	 * @param sortArray
     * @param m
     * @param n
     */
    public static void swap(int sortArray[], int m, int n){
        int temp = sortArray[m];
        sortArray[m] = sortArray[n];
        sortArray[n] = temp;
    }
}

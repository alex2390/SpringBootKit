package pan.springbootkit.utils.algorithm.sort.merging;

import pan.springbootkit.utils.algorithm.sort.BaseSort;

/**
 * 归并排序
 *
 * Created by panzhangbao on 2017/6/20.
 */
public class MergingSort extends BaseSort{

	/**
	 * 基本思想：
	 *      归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，
	 *      即把待排序序列分为若干个子序列，每个子序列是有序的。
	 *      然后再把有序子序列合并为整体有序序列。
	 */
    public static void sort(int[] sortArray){
        sortIt(sortArray, 0, sortArray.length - 1);
        toArray("归并排序：", sortArray);
    }

    private static void sortIt(int[] sortArray, int left, int right) {
        if(left < right){
            // 找出中间索引
            int center = (left + right) / 2;
            // 对左边数组进行递归
            sortIt(sortArray, left, center);
            // 对右边数组进行递归
            sortIt(sortArray, center + 1, right);
            // 合并
            merge(sortArray,left,center,right);
        }

    }

	private static void merge(int[] sortArray, int left, int center, int right) {
        int[] tempArray = new int[sortArray.length];
        int mid = center+1;
        // third记录中间数组的索引
        int third = left;
        int tmp = left;
        while(left <= center && mid <= right){
            // 从两个数组中取出最小的放入中间数组
            if(sortArray[left] <= sortArray[mid]){
                tempArray[third++] = sortArray[left++];
            }else{
                tempArray[third++] = sortArray[mid++];
            }
        }

        // 剩余部分依次放入中间数组
        while(mid <= right){
            tempArray[third++] = sortArray[mid++];
        }

        while(left <= center){
            tempArray[third++] = sortArray[left++];
        }

        // 将中间数组中的内容复制回原数组
        while(tmp <= right){
            sortArray[tmp] = tempArray[tmp++];
        }
    }
}

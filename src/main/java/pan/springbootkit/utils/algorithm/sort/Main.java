package pan.springbootkit.utils.algorithm.sort;

import pan.springbootkit.utils.algorithm.sort.allocation.RadixSort;
import pan.springbootkit.utils.algorithm.sort.insert.InsertDirectlySort;
import pan.springbootkit.utils.algorithm.sort.insert.ShellSort;
import pan.springbootkit.utils.algorithm.sort.merging.MergingSort;
import pan.springbootkit.utils.algorithm.sort.select.HeapSort;
import pan.springbootkit.utils.algorithm.sort.select.SimpleSelectSort;
import pan.springbootkit.utils.algorithm.sort.swap.BubbleSort;
import pan.springbootkit.utils.algorithm.sort.swap.QuickSort;

public class Main {

    public static void main(String[] args) {

    	// 要排序的数组
        int sortArray[] = {48, 23, 4, 32, 223, 66,32, 35, 27, 64, 3, 888,27};

        /**
         * 插入排序
         */
         //  直接插入排序
         InsertDirectlySort.sort(sortArray);
         //  希尔排序
         ShellSort.sort(sortArray);

        /**
         * 交换排序
         */
         // 冒泡排序
         BubbleSort.sort(sortArray);
         BubbleSort.sortMin(sortArray);
         BubbleSort.sortImproved(sortArray);

         // 快速排序
         QuickSort.sort(sortArray);

         /**
          * 选择排序
          */
         // 简单选择排序
         SimpleSelectSort.sort(sortArray);

         // 堆排序
         HeapSort.sort(sortArray);

        /**
         *  归并排序
         */
         MergingSort.sort(sortArray);

        /**
         * 基数排序
         */
        RadixSort.sort(sortArray);
    }
}

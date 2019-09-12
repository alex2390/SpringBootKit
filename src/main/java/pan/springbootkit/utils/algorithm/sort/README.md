# SortAlgorithm
Java 常见的 8 种排序算法（内排序）
# 排序分类
###### 内部排序
1. 插入排序：直接插入排序、希尔排序
2. 交换排序：冒泡排序、快速排序
3. 选择排序：直接选择排序、堆排序
4. 归并排序
5. 分配排序：箱排序、基数排序

###### 外部排序
1. 多路归并排序

# 排序特点
1. 所需辅助空间最多：归并排序
2. 所需辅助空间最少：堆排序
3. 平均速度最快
4. 不稳定：快速排序、希尔排序、堆排序

# 排序算法选择
1. 数据的规模
2. 数据的类型
3. 数据已有的顺序

>一般来说，当数据规模较小时，应该选择直接插入排序或冒泡排序。
考虑数据的类型，比如如果全部是正整数，则考虑使用桶排序为最优。
对于已经基本排好序的数据，冒泡最佳。
快排适用于大量随机数据。

![](https://images2017.cnblogs.com/blog/849589/201710/849589-20171015233043168-1867817869.png)

# 插入排序
直接插入排序
```
package pan.Sort.InsertSort;


import pan.Sort.BaseSort;

/**
 * Created by panzhangbao on 2017/6/19.
 */
public class InsertDirectlySort extends BaseSort {

    /** 基本思想：
     *      在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排好顺序的，
     *      现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。
     *      如此反复循环，直到全部排好顺序。
     */
   public static void sort(int[] sortArray){
        int temp = 0;

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
```
希尔排序
```
package pan.Sort.InsertSort;

import pan.Sort.BaseSort;

/**
 * Created by panzhangbao on 2017/6/19.
 *
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
public class ShellSort extends BaseSort {

    public static void sort(int[] sortArray){

        double d1 = sortArray.length;
        int temp = 0;

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

```
# 交换排序
冒泡排序
```
package pan.Sort.SwapSort;

import pan.Sort.BaseSort;

/**
 * Created by panzhangbao on 2017/6/20.
 */
public class BubbleSort extends BaseSort{

    /**
     * 正向冒泡
     * @param sortArray
     */
    public static void sort(int[] sortArray){
        //  每趟排序的最大值
        int maxValue;
        //  排序标志，默认为未排序
        boolean isSorted;

        for(int i = 0; i < sortArray.length - 1; i++){
            maxValue = sortArray[sortArray.length - 1];
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
        //  每趟排序的最小值
        int minValue;
        //  排序标志，默认为未排序
        boolean isSorted;

        for(int i = sortArray.length - 1; i > 0; i--){
            minValue = sortArray[i];
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
```
快速排序
```
package pan.Sort.SwapSort;

import pan.Sort.BaseSort;

/**
 * Created by panzhangbao on 2017/6/20.
 *
 * 基本思想：
 *      选择一个基准元素,通常选择第一个元素或者最后一个元素,
 *      通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
 *      此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
 *
 */
public class QuickSort extends BaseSort{
    public static void sort(int[] sortArray){
        quickSort(sortArray,0, sortArray.length - 1);
        toArray("快速排序：", sortArray);
    }

    public static void quickSort(int[] list, int low, int high) {
        if (low < high){
            int middle = getMiddle(list, low, high);  // 将list数组进行一分为二
            quickSort(list, low, middle - 1);       // 对低字表进行递归排序
            quickSort(list,middle + 1, high);       // 对高字表进行递归排序
        }
    }

    public static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];    // 数组的第一个作为中轴
        while (low < high){
            while (low < high&& list[high] >= tmp) {
                high--;
            }

            list[low] = list[high];   // 比中轴小的记录移到低端
            while (low < high&& list[low] <= tmp) {
                low++;
            }

            list[high] = list[low];   // 比中轴大的记录移到高端
        }
        list[low] = tmp;              // 中轴记录到尾
        return low;                   // 返回中轴的位置
    }

}
```
# 选择排序
简单选择排序
```
package pan.Sort.SelectSort;

import pan.Sort.BaseSort;

/**
 * Created by panzhangbao on 2017/6/20.
 *
 * 基本思想：
 *      在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 *      然后在剩下的数当中再找最小的与第二个位置的数交换，
 *      如此循环到倒数第二个数和最后一个数比较为止。
 */
public class SimpleSelectSort extends BaseSort{

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
```
堆排序
```
package pan.Sort.SelectSort;

import pan.Sort.BaseSort;

/**
 * Created by panzhangbao on 2017/6/20.
 *
 * 基本思想：
 *      堆排序是一种树形选择排序，是对直接选择排序的有效改进。
 * 堆的定义如下：
 *      具有n个元素的序列（h1,h2,...,hn),当且仅当满足（hi>=h2i,hi>=2i+1）或
 *      （hi<=h2i,hi<=2i+1）(i=1,2,...,n/2)时称之为堆。
 *      在这里只讨论满足前者条件的堆。
 *      由堆的定义可以看出，堆顶元素（即第一个元素）必为最大项（大顶堆）。
 *      完全二叉树可以很直观地表示堆的结构。
 *      堆顶为根，其它为左子树、右子树。
 *      初始时把要排序的数的序列看作是一棵顺序存储的二叉树，调整它们的存储序，使之成为一个堆，
 *      这时堆的根节点的数最大。然后将根节点与堆的最后一个节点交换。
 *      然后对前面(n-1)个数重新调整使之成为堆。
 *      依此类推，直到只有两个节点的堆，并对它们作交换，最后得到有n个节点的有序序列。
 *      从算法描述来看，堆排序需要两个过程，一是建立堆，二是堆顶与堆的最后一个元素交换位置。
 *      所以堆排序有两个函数组成。一是建堆的渗透函数，二是反复调用渗透函数实现排序的函数。
 */
public class HeapSort extends BaseSort{

    public static void sort(int[] sortArray){
        //  循环建堆
        for(int i = 0;i < sortArray.length - 1;i++){
            // 建堆
            buildMaxHeap(sortArray,sortArray.length - 1 - i);
            // 交换堆顶和最后一个元素
            swap(sortArray,0,sortArray.length - 1 - i);
        }

        toArray("堆排序：",sortArray);
    }

    // 对数组 sortArray 从 0 到 lastIndex 建大顶堆
    private static void buildMaxHeap(int[] sortArray, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //  k保存正在判断的节点
            int k = i;
            // 如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                // k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;

                // 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    // 若果右子节点的值较大
                    if (sortArray[biggerIndex] < sortArray[biggerIndex + 1]) {
                        // biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }

                // 如果k节点的值小于其较大的子节点的值
                if (sortArray[k] < sortArray[biggerIndex]) {
                    swap(sortArray, k, biggerIndex);
                    // 将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }
}
```
# 归并排序
```
package pan.Sort.MergingSort;

import pan.Sort.BaseSort;

import java.util.Arrays;

/**
 * Created by panzhangbao on 2017/6/20.
 *
 * 基本排序：
 *      归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，
 *      即把待排序序列分为若干个子序列，每个子序列是有序的。
 *      然后再把有序子序列合并为整体有序序列。
 */
public class MergingSort extends BaseSort{

    public static void sort(int[] sortArray){
        sortIt(sortArray, 0, sortArray.length - 1);
        toArray("归并排序：", sortArray);
    }

    public static void sortIt(int[] sortArray, int left, int right) {
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

    public static void merge(int[] sortArray, int left, int center, int right) {
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
//         System.out.println(Arrays.toString(sortArray));
    }
}
```
# 分配排序
基数排序
```
package pan.Sort.AllocationSort;

import pan.Sort.BaseSort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panzhangbao on 2017/6/20.
 *
 * 基本思想：
 *      将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
 *      然后，从最低位开始，依次进行一次排序。
 *      这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
 */
public class RadixSort extends BaseSort{

    public static void sort(int[] sortArray){

        // 首先确定排序的趟数;
        int max = sortArray[0];
        for(int i = 1; i < sortArray.length; i++){
            if(sortArray[i] > max){
                max = sortArray[i];
            }
        }
        int time = 0;
        // 判断位数
        while(max > 0){
            max /= 10;
            time++;
        }

        // 建立10个队列;
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for(int i = 0; i < 10; i++){
            ArrayList<Integer > queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }

        // 进行time次分配和收集;
        for(int i=0;i<time;i++){
            // 分配数组元素;
            for(int j = 0; j < sortArray.length; j++){
                // 得到数字的第time+1位数;
                int x = sortArray[j] % (int)Math.pow(10, i + 1) / (int)Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(sortArray[j]);
                queue.set(x, queue2);
            }
            int count = 0;// 元素计数器;
            // 收集队列元素;
            for(int k = 0; k < 10; k++){
                while(queue.get(k).size()>0){
                    ArrayList<Integer> queue3 = queue.get(k);
                    sortArray[count]=queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }

        toArray("基数排序：", sortArray);
    }

}
```
# Github 项目：[SortAlgorithm](https:// github.com/panzhangbao/SortAlgorithm)
# 参考
续写经典的博客：[Java常用排序算法/程序员必须掌握的8大排序算法 ](http:// blog.csdn.net/qy1387/article/details/7752973)

package com.lph.fastdfs.datafileprocess.util;


/**
 * 二分查找法
 *
 * @version V1.0
 * @author: lph
 * @date: 2020/1/10 14:46
 */
public class BinarySearchUtil {

    /**
     * f(n) = s.length = n;
     *
     * @param s
     * @param q
     * @return
     */
    public static int BrutalForceSearch(int[] s, int q) {
        for (int i = 0; i < s.length; i++) {
            if (q == s[i])
                return i;
        }
        return -1;
    }

    /**
     * f(n) = log(n)
     * 二分搜索
     *
     * @param s
     * @param q
     * @return
     */
    public static int DCSearch(int[] s, int q, int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return -1;
        else {
            int mid = (startIndex + endIndex) / 2;
            if (s[mid] == q)
                return mid;
            else {
                if (s[mid] > q)
                    return DCSearch(s, q, startIndex, mid - 1);
                else
                    return DCSearch(s, q, mid + 1, endIndex);
            }
        }
    }

    /**
     * 该形式的二分查找比上面更好，避免了方法的递归调用
     * @param srcArray
     * @param des
     * @return
     */
    public static int binarySearch(int[] srcArray, int des) {
        //定义初始最小、最大索引
        int start = 0;
        int end = srcArray.length - 1;
        //确保不会出现重复查找，越界
        while (start <= end) {
            //计算出中间索引值
            int middle = (end + start)>>>1 ;//防止溢出
            if (des == srcArray[middle]) {
                return middle;
                //判断下限
            } else if (des < srcArray[middle]) {
                end = middle - 1;
                //判断上限
            } else {
                start = middle + 1;
            }
        }
        //若没有，则返回-1
        return -1;
    }

    // 可以用来进行幂等性保证比如消息中已发送的消息id存放在内存中当有消息在次发送的时候进行二分查找看是否有消息id的存在
    public static int binarySearchs(String[] srcArray, String des) {
        //定义初始最小、最大索引
        int start = 0;
        int end = srcArray.length - 1;
        //确保不会出现重复查找，越界
        while (start <= end) {
            //计算出中间索引值
            int middle = (end + start)/2 ;//防止溢出
            if (des.compareTo(srcArray[middle]) == 0) {
                return middle;
                //判断下限
            } else if (des.compareTo(srcArray[middle]) < 0) {
                end = middle - 1;
                //判断上限
            } else {
                start = middle + 1;
            }
        }
        //若没有，则返回-1
        return -1;
    }

    public static void main(String[] args) {
        int[] s = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            s[i] = i;
        }
        int q = 10000000 - 1;
        long startTime = System.currentTimeMillis();
        System.out.println(BrutalForceSearch(s, q));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
//        long startTime = System.currentTimeMillis();
//        System.out.println(DCSearch(s, q, 0, s.length - 1));
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime-startTime);
//        int i = binarySearch(s, q);
//        System.err.println(i);

        String[] str = new String[5];
        for (int i1 = 0; i1 <5; i1++) {
            str[i1] = i1+"";
        }

        int binarySearchs = binarySearchs(str, "1");
        System.err.println(binarySearchs);
    }
}

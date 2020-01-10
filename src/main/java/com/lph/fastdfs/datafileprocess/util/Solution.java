package com.lph.fastdfs.datafileprocess.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class Solution {

    public static final int ASCLL = 256; // 不包括中文的编码范围

    public static final int CH = 65536; //包括中文的编码范围

    /**
     * 校验字符串中是否存在相同符号
     * @param str
     * @param pattern index为256的时候校验的是不包括中文的字符串，pattern为65536中文包括中文
     * @return true：不存在 false:存在
     */
    public boolean checkDifferent(String str, int pattern) {
        // write code here
        // 1. 假定 null 字符串返回 true
        // 2. 长度 > 256 则必定存在相同字符
        if (str == null || str.length() > pattern)
            return false;

        // appear 数组标记某个字符是否出现过, false 为未出现, true 为出现过
        boolean[] appear = new boolean[pattern];

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);
            if (appear[index] == true) {    // 出现过
                return false;
            }

            appear[index] = true;   // 第一次出现, 在 appear 中标记为已经出现
        }

        return true;
    }

    public String reverseString(String iniString) {
        if (StringUtils.isEmpty(iniString)) {
            return iniString;
        }
        int len = iniString.length();
        if (len <= 1)
            return iniString;
        StringBuffer sb = new StringBuffer();
        for (int i = len - 1; i >= 0; i--) {
            sb.append(iniString.charAt(i));
        }
        return sb.toString();
    }

    public boolean checkReverseEqual(String s1, String s2) {
        // write code here
        byte[] s1arr = s1.getBytes();
        byte[] s2arr = s2.getBytes();
        Arrays.sort(s1arr);
        Arrays.sort(s2arr);
        if (Arrays.equals(s1arr,s2arr)){
            return true;
        }
        return false;
    }

    public int calcCost(int A, int B)
    {
        int res=A^B;
        int count=0;
        while(res!=0)
        {
            count++;
            res&=res-1;

        }
        return count ;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean abcd = solution.checkDifferent("你好你", 65536);
        System.err.println(abcd);

//        String s = solution.reverseString("hello");
//        System.err.println(s);
//
//        boolean b = solution.checkReverseEqual("waterbottle", "erbottlewat");
//
//        System.err.println(b);
//
//        int i = solution.calcCost(2, 1);
//        System.err.println(i);
    }
}
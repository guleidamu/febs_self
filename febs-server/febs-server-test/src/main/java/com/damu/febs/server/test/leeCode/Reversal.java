package com.damu.febs.server.test.leeCode;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Reversal {

    public int reversalInteger(int x) {

        int result = 0;
        if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE && x % 10 > 7)) {
            return 0;
        }
        if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE && x % 10 < -8)) {
            return 0;
        }
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        Reversal reversal = new Reversal();
        int num = reversal.reversalInteger(987);
        System.out.println(num);
//        int i = 2 ^ 31 + 425626;
//        System.out.println("输出2^32:" + i);
    }
}


//
//
//        while (x != 0) {
//                result = result * 10 + x % 10;
//                x = x / 10;
//                }
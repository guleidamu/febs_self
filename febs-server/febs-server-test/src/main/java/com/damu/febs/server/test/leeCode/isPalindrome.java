package com.damu.febs.server.test.leeCode;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class isPalindrome {
    public boolean isP(int num){
        int original = num;
//        int target = 0;
        int result = 0;
        while(num>0){
            result = result * 10 + num % 10;
            num = num/10;
        }
        System.out.println("result的值:" + result);
        if(original == result){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int P = 85967;
        isPalindrome isPalindrome = new isPalindrome();
        System.out.println("P:" + P + "是不是回文数："+isPalindrome.isP(P));

        int P1 = 123454321;
        System.out.println("P1:" + P1 + "是不是回文数："+isPalindrome.isP(P1));
    }

}

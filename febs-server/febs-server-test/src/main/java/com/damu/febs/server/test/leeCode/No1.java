package com.damu.febs.server.test.leeCode;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No1 {

    public int[] towSum(int[] nums, int target) {
        int[] k = {2, 25, 120, 7, 5, 53, 67, 12, 3, 97, 63};
        int a = 0;
        int b = 1;
        int sum = k[a] + k[b];

        for (int i = 1; i < k.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp;
                if (sum > (k[i] + k[j])) {
                    sum = k[i] + k[j];
                    a = i;
                    b = j;
                }
//                if (k[i] < k[j]) {
//                    temp = k[j];
//                    k[j] = k[i];
//                    k[i] = temp;
//                }
            }
        }
        int[] result = {k[a], k[b]};
        for (int i : result) {
            System.out.println(i);
        }

        return null;
    }

    public static void main(String[] args) {
        No1 no1 = new No1();
        int k[] = {2};
        no1.towSum(k, 2);
    }
}

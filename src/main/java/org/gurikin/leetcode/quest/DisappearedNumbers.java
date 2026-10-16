package org.gurikin.leetcode.quest;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 *
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [2]
 *
 *
 *
 * Constraints:
 *
 *     n == nums.length
 *     1 <= n <= 105
 *     1 <= nums[i] <= n
 *
 *
 *
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */
public class DisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        var resultArr = new int[nums.length + 1];
        //new ArrayList<Integer>(nums.length + 1);
        for (int i = 0; i < nums.length; i++) {
            resultArr[nums[i]] = 1;
        }
        var result = new ArrayList<Integer>();
        for (int i = 1; i < resultArr.length; i++) {
            if (resultArr[i] == 0) {
                result.add(i);
            }
        }
        return result;
    }
}

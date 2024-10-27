package com.qs.leetcode.code.array;

import java.util.*;

/**
 * Class for k sum in arrays.
 *
 * @author LegenQS
 * @date 10/26/24 8:13 PM
 */
public class Sum {
    /**
     * <p>LC-1</p>
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up
     * to target. You may assume that each input would have exactly one solution, and you may not use the same element
     * twice. You can return the answer in any order.
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }

    /**
     * <p>LC-15</p>
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
     * and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * Notice that the solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        // target of the 3 sum
        int target = 0;
        int length = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        int i = 0;
        while (i < length - 2) {
            int j = i + 1;
            if (nums[i] + nums[j] + nums[j + 1] > target) {
                break;
            }

            int left = j, right = length - 1;
            int localTarget = target - nums[i];
            while (left < right) {
                int mid = nums[left] + nums[right];
                if (mid == localTarget) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    while(++left < right && nums[left] == nums[left - 1]){}
                    while(--right > left && nums[right] == nums[right + 1]){}
                }
                else if (mid > localTarget) {
                    right--;
                }
                else {
                    left++;
                }
            }

            while (++i < length - 2 && nums[i] == nums[i - 1]) {}
        }
        return result;
    }

    /**
     * <p>LC-18</p>
     * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c],
     * nums[d]] such that:
     * <ul>
     *     <li>0 <= a, b, c, d < n</li>
     *     <li>a, b, c, and d are distinct.</li>
     *     <li>nums[a] + nums[b] + nums[c] + nums[d] == target</li>
     * </ul>
     * You may return the answer in any order.
     *
     * <p> <b> Be careful about the precision overflow for int. </b> </p>
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        int i = 0;
        while (i < length - 3) {
            int j = i + 1;
            if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                break;
            }

            while (j < length - 2) {
                int k = j + 1;
                if ((long) nums[i] + nums[j] + nums[k] + nums[k + 1] > target) {
                    break;
                }

                int left = k, right = length - 1;
                long localTarget = (long) target - nums[i] - nums[j];
                while (left < right) {
                    int mid = nums[left] + nums[right];
                    if (mid == localTarget) {
                        result.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        while (++left < right && nums[left] == nums[left - 1]) {}
                        while (--right > left && nums[right] == nums[right + 1]) {}
                    }
                    else if (mid > localTarget) {
                        right--;
                    }
                    else {
                        left++;
                    }
                }
                while (++j < length - 2 && nums[j] == nums[j - 1]) {}
            }
            while (++i < length - 3 && nums[i] == nums[i - 1]) {}
        }
        return result;
    }
}

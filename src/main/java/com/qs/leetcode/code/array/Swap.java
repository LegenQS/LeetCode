package com.qs.leetcode.code.array;

/**
 * Class of swap algorithm used in arrays.
 *
 * @author LegenQS
 * @date 10/20/24 10:42 PM
 */
public class Swap {
    /**
     * LC-26:
     * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
     * element appears only once. The relative order of the elements should be kept the same. Then return the number of
     * unique elements in nums. Consider the number of unique elements of nums to be k, to get accepted, you need to do the
     * following things:
     *
     * Change the array nums such that the first k elements of nums contain the unique elements in the order they were
     * present in nums initially. The remaining elements of nums are not important as well as the size of nums.
     *
     * Space complexity: O(1)
     * Time complexity: O(n)
     */
    public int removeDuplicatesI(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int index = 0;
        for (int num : nums) {
            if (num == nums[index]) {
                nums[++index] = num;
            }
        }
        return index + 1;
    }

    /**
     * LC-80:
     * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique
     * element appears at most twice. The relative order of the elements should be kept the same. Since it is impossible
     * to change the length of the array in some languages, you must instead have the result be placed in the first part
     * of the array nums. More formally, if there are k elements after removing the duplicates, then the first k
     * elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
     *
     * Return k after placing the final result in the first k slots of nums. Do not allocate extra space for another
     * array. You must do this by modifying the input array in-place with O(1) extra memory.
     */
    public int removeDuplicatesII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length < 3) {
            return nums.length;
        }
        // initial start position
        int index = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[index - 2]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}

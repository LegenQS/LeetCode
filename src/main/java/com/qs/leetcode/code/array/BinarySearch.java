package com.qs.leetcode.code.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class of algorithms that use binary search.
 *
 * @author LegenQS
 * @date 10/20/24 11:09 PM
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.findMedianSortedArrays(nums1, nums2));
    }
    /**
     * <p>LC-33</p>
     * There is an integer array nums sorted in ascending order (with distinct values).
     *
     * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k
     * (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1],
     * ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become
     * [4,5,6,7,0,1,2].
     *
     * Given the array nums after the possible rotation and an integer target, return the index of target if it is in
     * nums, or -1 if it is not in nums.
     *
     * You must write an algorithm with O(log n) runtime complexity.
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            System.out.println(left + " " + right);
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] > target) {
                if (isMax(nums[mid], nums[left], nums[right])) {
                    if (nums[left] > target) {
                        left = mid + 1;
                    }
                    else {
                        right = mid;
                    }
                }
                else if (isMin(nums[mid], nums[left], nums[right])) {
                    right = mid;
                }
                else {
                    if (nums[left] > target) {
                        left = mid + 1;
                    }
                    else {
                        right = mid;
                    }
                }
            }
            else {
                if (isMin(nums[mid], nums[left], nums[right])) {
                    if (nums[right] >= target) {
                        left = mid + 1;
                    }
                    else {
                        right = mid;
                    }
                }
                else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    private static boolean isMax(int a, int b, int c) {
        return a > b && a > c;
    }

    private static boolean isMin(int a, int b, int c) {
        return a < b && a < c;
    }

    /**
     * <p>LC-33</p>
     * Simplified version, comparing the value only with the boundary and the mid.
     */
    public static int searchII(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid;
                }
                else {
                    left = mid + 1;
                }
            }
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid;
                }
            }
        }
        return -1;
    }

    /**
     * <p>LC-81</p>
     * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
     *
     * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such
     * that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
     * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
     *
     * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it
     * is not in nums. You must decrease the overall operation steps as much as possible.
     */
    public boolean searchIII(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            else if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid;
                }
                else {
                    left = mid + 1;
                }
            }
            // only adding this situation when [left, mid] is not restricted increasing.
            else if (nums[left] == nums[mid]) {
                left++;
            }
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid;
                }
            }
        }
        return false;
    }

    /**
     * <p>LC-4</p>
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     *
     * The overall run time complexity should be O(log (m+n)).
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int mid = (length1 + length2) / 2 + 1;
        // convert it to find the kst min elements in an array
        if ((length1 + length2) % 2 == 0) {
            return (findMaxValInPosition(nums1, nums2, mid - 1, 0, 0) + findMaxValInPosition(nums1, nums2, mid, 0, 0)) / 2;
        }

        return findMaxValInPosition(nums1, nums2, mid, 0, 0);
    }

    private double findMaxValInPosition(int[] nums1, int[] nums2, int position, int index1, int index2) {
        if (index1 == nums1.length) {
            return nums2[index2 + position - 1];
        }
        else if (index2 == nums2.length) {
            return nums1[index1 + position - 1];
        }
        if (position == 1) {
            return Math.min(nums1[index1], nums2[index2]);
        }

        int ret = position / 2;
        int newIdx1 = Math.min(nums1.length, index1 + ret) - 1;
        int newIdx2 = Math.min(nums2.length, index2 + ret) - 1;

        if (nums1[newIdx1] >= nums2[newIdx2]) {
            return findMaxValInPosition(nums1, nums2, position - (newIdx2 - index2), index1, ++newIdx2);
        }
        else {
            return findMaxValInPosition(nums1, nums2, position - (newIdx1 - index1), ++newIdx1, index2);
        }
    }

    /**
     * <p>LC-128</p>
     * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
     *
     * You must write an algorithm that runs in O(n) time.
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();

        int length = 0;
        for (int key : nums) {
            if (!map.containsKey(key)) {
                int left = map.getOrDefault(key - 1, 0);
                int right = map.getOrDefault(key + 1, 0);

                int curLength = left + right + 1;
                map.put(key, curLength);
                map.put(key - left, curLength);
                map.put(key + right, curLength);
                length = Math.max(length, curLength);
            }
        }
        return length;
    }
}

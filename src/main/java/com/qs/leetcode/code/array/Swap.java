package com.qs.leetcode.code.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class of swap algorithm used in arrays.
 *
 * @author LegenQS
 * @date 10/20/24 10:42 PM
 */
public class Swap {
    /**
     * <p>LC-26</p>
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
     * <p>LC-80</p>
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

    /**
     * <p>LC-27</p>
     * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the
     * elements may be changed. Then return the number of elements in nums which are not equal to val. Consider the
     * number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
     *
     * Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
     * The remaining elements of nums are not important as well as the size of nums. Return k.
     */
    public int removeElement(int[] nums, int val) {
        int pivot = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                int temp = nums[pivot];
                nums[pivot++] = nums[i];
                nums[i] = temp;
            }
        }
        return pivot;
    }

    /**
     * <p>LC-283</p>
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero
     * elements. Note that you must do this in-place without making a copy of the array.
     */
    public void moveZeroes(int[] nums) {
        int pivot = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[pivot];
                nums[pivot++] = nums[i];
                nums[i] = tmp;
            }
        }
    }

    /**
     * <p>LC-31</p>
     * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
     *
     * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3],
     * [2, 3, 1], [3,1,2], [3,2,1]. The next permutation of an array of integers is the next lexicographically greater
     * permutation of its integer. More formally, if all the permutations of the array are sorted in one container
     * according to their lexicographical order, then the next permutation of that array is the permutation that follows
     * it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest
     * possible order (i.e., sorted in ascending order).
     *
     * For example, the next permutation of arr = [1,2,3] is [1,3,2]. Similarly, the next permutation of arr = [2,3,1]
     * is [3,1,2]. While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a
     * lexicographical larger rearrangement.
     *
     * Given an array of integers nums, find the next permutation of nums. The replacement must be
     * <a href=https://en.wikipedia.org/wiki/In-place_algorithm>in place</a> and use only constant extra memory.
     */
    public void nextPermutation(int[] nums) {
        int length = nums.length;

        // find the first index that violates the descending order
        int pos = findFirstNoDesc(nums);

        // if not found, reverse the entire array
        if (pos == -1) {
            reverse(nums, 0, length - 1);
            return;
        }
        int next = -1;

        // find the next least great number (greater than nums[pos]) that to be swapped
        for (int i = pos + 1; i < length; i++) {
            if (nums[i] > nums[pos]) {
                if (next == -1) {
                    next = i;
                }
                else {
                    // get the most last one to make sure the descending order is maintained after swap
                    if (nums[next] >= nums[i]) {
                        next = i;
                    }
                }
            }
        }

        // swap the nums[pos] and the least great number, then reverse the left after pos (since they are descending)
        swap(nums, pos, next);
        reverse(nums, pos + 1, length - 1);
    }

    private int findFirstNoDesc(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                return i - 1;
            }
        }
        return -1;
    }

    /**
     * Reverse an array from index [left, right].
     */
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = tmp;
        }
    }

    /**
     * Swap the nums[left] and nums[right] with given params.
     */
    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /**
     * <p>LC-46</p>
     * Given an array nums of distinct integers, return all the possible
     * permutations. You can return the answer in any order.
     */
    // Method 1: using swap
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, 0, result);

        return result;
    }

    private void permute(int[] nums, int start, List<List<Integer>> result){
        if (start == nums.length) {
            List<Integer> current=new ArrayList<>();
            for(int num:nums){
                current.add(num);
            }
            result.add(current);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            permute(nums,start + 1, result);
            swap(nums, start, i);
        }
    }

    // Method 2: using recursive
    public List<List<Integer>> permuteII(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);

        dfsII(nums, new LinkedList<>(), result, used);

        return result;
    }

    private void dfsII(int[] nums, LinkedList<Integer> tmp, List<List<Integer>> result, boolean[] used){
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                tmp.add(nums[i]);
                dfsII(nums, tmp, result, used);
                tmp.removeLast();
                used[i] = false;
            }
        }
    }

    /**
     * <p>LC-47</p>
     * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations
     * in any order.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);

        dfs(nums, new LinkedList<>(), result, used);

        return result;
    }

    private void dfs(int[] nums, LinkedList<Integer> tmp, List<List<Integer>> result, boolean[] used){
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && !used[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            used[i] = true;
            tmp.add(nums[i]);
            dfs(nums, tmp, result, used);
            tmp.removeLast();
            used[i] = false;
        }
    }
}

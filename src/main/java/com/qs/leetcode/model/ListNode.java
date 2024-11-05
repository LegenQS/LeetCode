package com.qs.leetcode.model;

/**
 * @author LegenQS
 * @date 10/31/24 12:07AM
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}
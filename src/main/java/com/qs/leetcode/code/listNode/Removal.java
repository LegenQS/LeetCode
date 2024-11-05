package com.qs.leetcode.code.listNode;

import com.qs.leetcode.model.ListNode;

/**
 * @author LegenQS
 * @date 10/31/24 12:07 AM
 */
public class Removal {
    /**
     * <p>LC-203</p>
     * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has
     * Node.val == val, and return the new head.
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode virtualHead = new ListNode();
        ListNode tmp = virtualHead;
        tmp.next = head;

        while (tmp.next != null) {
            if (tmp.next.val == val) {
                tmp.next = tmp.next.next;
            }
            else {
                tmp = tmp.next;
            }
        }

        return virtualHead.next;
    }
}

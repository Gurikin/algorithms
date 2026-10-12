package org.gurikin.leetcode.medium;

import java.util.ArrayList;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var head = new ListNode(0);
        var tail = head;
        var reminder = 0;
        while (l1 != null || l2 != null || reminder != 0) {
            var v1 = (l1 != null) ? l1.val : 0;
            var v2 = (l2 != null) ? l2.val : 0;
            var sum = v1 + v2 + reminder;

            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            reminder = sum / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        var l1 = new ListNode(9);
        var l2 = new ListNode(9);
        l1.next = new ListNode(9);
        l2.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        var result = addTwoNumbers(l1, l2);
        System.out.println("CurrNode: " + result.val);
        while (result.next != null) {
            var currNode = result.next;
            System.out.println("CurrNode: " + currNode.val);
            result = currNode;
        }
    }
}

// Last updated: 28/07/2026, 20:22:37
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * Intuition:
 * ----------
 * We need to reverse the linked list in groups of size k while keeping any
 * remaining nodes (less than k) unchanged.
 *
 * Instead of repeatedly checking whether k nodes are available before every
 * reversal, we first count the total number of nodes in the list. This allows
 * us to know exactly how many complete groups can be reversed.
 *
 * For each complete group:
 * 1. Reverse the next k nodes using the standard iterative linked list
 *    reversal technique.
 * 2. Connect the previous group's tail to the head of the reversed group.
 * 3. The original head of the current group becomes the tail after reversal,
 *    so it is saved to connect with the next group later.
 *
 * After all complete groups have been processed, simply attach the remaining
 * nodes (if any) to the tail of the last reversed group.
 *
 * Example:
 *      1 -> 2 -> 3 -> 4 -> 5,  k = 2
 *
 *      Reverse (1,2): 2 -> 1
 *      Reverse (3,4): 4 -> 3
 *      Remaining:     5
 *
 *      Result:
 *      2 -> 1 -> 4 -> 3 -> 5
 *
 * Algorithm:
 * ----------
 * 1. Traverse the list once to count the total number of nodes.
 * 2. While at least k nodes remain:
 *      - Reverse the next k nodes.
 *      - Connect the reversed group with the previous group.
 *      - Update pointers for the next iteration.
 * 3. Connect the last reversed group's tail to the remaining unreversed nodes.
 * 4. Return the head of the first reversed group (or the original head if no
 *    reversal was performed).
 *
 * Time Complexity: O(n)
 *      - One pass to count nodes.
 *      - One pass to reverse and reconnect nodes.
 *
 * Space Complexity: O(1)
 *      - Only a constant number of pointers are used.
 */

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        int i = 0;
        ListNode newHead = null;
        ListNode prevGroupTail = null;
        ListNode nxtNode = null;

        while (i + k <= n) {
            ListNode currTail = null;
            ListNode groupOriginalHead = head;
            int count = 0;

            while (count < k) {
                nxtNode = head.next;
                head.next = currTail;
                currTail = head;
                head = nxtNode;
                count++;
            }

            if (i == 0) {
                newHead = currTail;
            } else {
                prevGroupTail.next = currTail;
            }

            prevGroupTail = groupOriginalHead;
            i += k;
        }

        if (prevGroupTail != null) {
            prevGroupTail.next = head;
        }

        return newHead != null ? newHead : head;
    }
}
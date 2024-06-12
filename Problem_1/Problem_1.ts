/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function deleteDuplicates(head: ListNode | null): ListNode | null {

    let x:ListNode | null = head;
    while(head!=null && head.next!=null){
        if(head.val == head.next.val){
            head.next = head.next.next as ListNode;
        }
        else{
            head = head.next as ListNode;
        }
    }
    return x;
};

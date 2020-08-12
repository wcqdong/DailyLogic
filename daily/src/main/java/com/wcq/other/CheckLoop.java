package com.wcq.other;

import java.util.Objects;

/**
 * 单向链表检查环
 */
public class CheckLoop {
    private class Node{
        private Object value;
        private Node next;

        public Node next() {
            return next;
        }
    }
    boolean checkLoop(Node head){
        Node slow = head;
        Node fast = head;
        while(slow != null && fast != null && fast.next() != null){
            slow = slow.next();
            fast = fast.next().next();
            if(slow == fast){
                return true;
            }
        }

        return false;
    }
}

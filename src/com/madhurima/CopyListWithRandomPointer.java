//Time complexity: O(n)
//Space Complexity: O(n) for hashmap based solution, O(1) for other solution
//Did the code run successfully in LeetCode = yes

package com.madhurima;

import org.w3c.dom.Node;

import java.util.HashMap;

public class CopyListWithRandomPointer {
}

class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class SolutionHashMap {

    HashMap<Node1, Node1> map;

    //by using hashmap and O(n) of extra space
    public Node1 copyRandomList(Node1 head) {
        if(head == null){
            return null;
        }

        map = new HashMap<>();

        Node1 copyHead = clone(head);
        Node1 curr = head;
        Node1 copyCurr = copyHead;

        while(curr != null){
            copyCurr.next = clone(curr.next);
            copyCurr.random = clone(curr.random);
            curr = curr.next;
            copyCurr = copyCurr.next;
        }

        return copyHead;

    }

    private Node1 clone(Node1 node){
        if(node == null){
            return null;
        }

        if(map.containsKey(node)){
            return map.get(node);
        }

        Node1 newNode = new Node1(node.val);
        map.put(node, newNode);
        return newNode;
    }
}


class SolutionNoExtraSpace {

    //placing copy of element next to itself and separate the list result in O(1) space
    public Node1 copyRandomList(Node1 head) {
        if(head == null) return null;

        //step1: create copy of each node and place it next to curr element
        Node1 curr = head;
        while(curr != null){
            Node1 copyCurr = new Node1(curr.val);
            copyCurr.next = curr.next;
            curr.next = copyCurr;
            curr = curr.next.next;
        }

        //step2: set the random pointers
        curr = head;
        while(curr != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        //step3: separate two linked lists
        curr = head;
        Node1 copyCurr = curr.next;
        Node1 copyHead = curr.next;

        while(curr != null){
            curr.next = curr.next.next;
            if(copyCurr.next != null){
                copyCurr.next = copyCurr.next.next;
            }
            curr = curr.next;
            copyCurr = copyCurr.next;
        }

        return copyHead;

    }
}


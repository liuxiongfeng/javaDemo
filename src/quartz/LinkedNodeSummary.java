package quartz;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author kerryfish
 * 关于java中链表的操作 
 * 1. 求单链表中结点的个数: getListLength  
 * 2. 将单链表反转: reverseList（遍历），reverseListRec（递归）  
 * 3. 查找单链表中的倒数第K个结点（k > 0）: reGetKthNode  
 * 4. 查找单链表的中间结点: getMiddleNode  
 * 5. 从尾到头打印单链表: reversePrintListStack，reversePrintListRec（递归）  
 * 6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序: mergeSortedList, mergeSortedListRec  
 * 7. 对单链表进行排序,listSort（归并）,insertionSortList（插入） 
 * 8. 判断一个单链表中是否有环: hasCycle  
 * 9. 判断两个单链表是否相交: isIntersect  
 * 10. 已知一个单链表中存在环，求进入环中的第一个节点: getFirstNodeInCycle, getFirstNodeInCycleHashMap  
 * 11. 给出一单链表头指针head和一节点指针delete，O(1)时间复杂度删除节点delete: deleteNode 
 */
public class LinkedNodeSummary {
    /**
     * @param args
     */
    public static class Node {
        int value;
        Node next;

        public Node(int n) {
            this.value = n;
            this.next = null;


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub  
        /*Scanner in = new Scanner(System.in);
        Node head = null;
        if (in.hasNextInt()) {
            head = new Node(in.nextInt());
        }
        Node temp = head;
        while (in.hasNextInt()) {
            temp.next = new Node(in.nextInt());
            temp = temp.next;
        }
        in.close();
        //int len=getListLength(head);
        Node reHead=reverseList(head);
        System.out.print(reHead);*/
        Node head = new Node(1);


        //reHead=reverseListRec(reHead);  
        //Node node_k=reGetKthNode(head,3);  
        //Node mid=getMiddleNode(head);  
        //reversePrintListRec(head);  
        //reversePrintListStack(head);  
        //Node mergeHead=mergeSortedList(head,null);  
        //Node sortHead=listSort(head);  

    }

    //求单链表中结点的个数: getListLength
    public static int getListLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    //将单链表反转,循环
    public static Node reverseList(Node head) {
        if (head == null || head.next == null) return head;
        Node pre = null;
        Node nex = null;
        while (head != null) {
            nex = head.next;
            head.next = pre;
            pre = head;
            head = nex;
        }
        return pre;
    }
}}
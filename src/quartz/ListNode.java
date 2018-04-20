package quartz;

public class ListNode {
    ListNode next=null;
    int val;
    public ListNode(int val){
        this.val=val;
    }
    public static ListNode reverseList(ListNode head){//返回反转后的链表头节点
        ListNode p1 = head;
        ListNode p2 = head.next;
        head.next =null;
        ListNode p3 = null;
        while (p2 !=null){
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 =p3;
        }
        return p1;
    }
    public static void main(String [] args){//测试部分
        ListNode head=new ListNode(0);
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        head.next=node1;
        node1.next=node2;
        node2.next=node3;

        head=reverseList(head);
        while(head!=null){
            System.out.println(head.val);
            head=head.next;
        }



    }
}
package quartz;

public class BinaryTreeCopy {
    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value){
            this.value=value;
        }
    }

    TreeNode root;

    public BinaryTreeCopy(int[] array){
        root=makeBinaryTreeByArray(array,1);
    }

    /**
     * 采用递归的方式创建一颗二叉树 
     * 传入的是二叉树的数组表示法 
     * 构造后是二叉树的二叉链表表示法 
     */
    public static TreeNode makeBinaryTreeByArray(int[] array,int index){
        if(index<array.length){
            int value=array[index];
            if(value!=0){
                TreeNode t=new TreeNode(value);
                array[index]=0;
                t.left=makeBinaryTreeByArray(array,index*2);
                t.right=makeBinaryTreeByArray(array,index*2+1);
                return t;
            }
        }
        return null;
    }

    /**
     * 先序遍历
     * 采用非递归实现
     * 需要辅助数据结构：栈
     */
    public void preOrderTraversal(){


        System.out.print("\n");
    }
    /**
     * 中序遍历
     * 采用非递归实现
     * 需要辅助数据结构：栈
     */
    public void inOrderTraversal(){


        System.out.print("\n");
    }
    /**
     * 后序遍历
     * 采用非递归实现
     * 需要辅助数据结构：栈
     */
    public void postOrderTraversal(){



        System.out.print("\n");
    }

    /**
     * 深度优先遍历，相当于先根遍历 
     * 采用非递归实现 
     * 需要辅助数据结构：栈 
     */
    public void depthOrderTraversal(){

        System.out.print("\n");
    }
    /**
     *                  13
     *                 /  \
     *               65    5
     *              /  \    \
     *             97  25   37
     *            /    /\   /
     *           22   4 28 32
     */

    /**
     * 广度优先遍历
     * 采用非递归实现
     * 需要辅助数据结构：队列
     */
    public void levelOrderTraversal(){


        System.out.print("\n");
    }

    /**
     *                  13 
     *                 /  \ 
     *               65    5 
     *              /  \    \ 
     *             97  25   37 
     *            /    /\   / 
     *           22   4 28 32 
     */
    public static void main(String[] args) {
        int[] arr={0,13,65,5,97,25,0,37,22,0,4,28,0,0,32,0};
        BinaryTreeCopy tree=new BinaryTreeCopy(arr);
        tree.depthOrderTraversal();
        tree.levelOrderTraversal();
        tree.preOrderTraversal();
        tree.inOrderTraversal();
        tree.postOrderTraversal();
    }
}
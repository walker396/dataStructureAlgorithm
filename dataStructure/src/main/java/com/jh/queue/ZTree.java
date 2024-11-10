package com.jh.queue;

import java.util.ArrayList;
import java.util.List;

public class ZTree {

    private static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value){
            this.value = value;
        }
        public TreeNode(int value,TreeNode left, TreeNode right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> levelOrder(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return null;
        }
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>(100);
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            result.add(node.value);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right !=null){
                queue.offer(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        ZTree tree = new ZTree();
        List<Integer> result = tree.levelOrder(root);
        System.out.println(result.toString());
    }
}

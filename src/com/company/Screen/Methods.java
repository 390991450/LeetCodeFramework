package com.company.Screen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.company.Bean.TreeNode;

/*这里存放所有的算法*/
public class Methods {
    public TreeNode mirrorTree(TreeNode root) {
        changeNode(root);
        return root;
    }
    public void changeNode(TreeNode root){
        if (root!=null&&(root.left!=null||root.right!=null)){
            TreeNode node = root.right;
            root.right =  root.left;
            root.left = node;
            changeNode(root.left);
            changeNode(root.right);
        }
    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null&&B != null)&&(compareNode(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B));
    }

    public boolean compareNode(TreeNode A,TreeNode B){
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return compareNode(A.left, B.left) && compareNode(A.right, B.right);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //使用队列层序遍历
        ArrayDeque<TreeNode> que = new ArrayDeque<>();
        //一个节点出时要把它的左右子节点也放进去
        if (root == null) {
            return res;
        }
        que.offer(root);
        int layer = 0;
        while (!que.isEmpty()) {
            List list = new ArrayList();
            //每次把队列中的数取完即为取完了当前层
            layer++;
            int size = que.size();
            for (int i = 0;i<size;i++){
            TreeNode node = que.pollFirst();
            list.add(node.val);
            if (node.left != null) {
                que.add(node.left);
            }
            if (node.right != null) {
                que.add(node.right);
            }
            }
            //如果layer是偶数,则反转链表
            if (layer%2==0){
                Collections.reverse(list);
            }
            res.add(list);
        }
        return res;
    }
    public int[] levelOrder1(TreeNode root) {
        List list = new ArrayList<Integer>();
        //使用队列层序遍历
        ArrayDeque<TreeNode> que = new ArrayDeque<>();
        //一个节点出时要把它的左右子节点也放进去
        if (root == null) {
            return new int[0];
        }
        que.offer(root);
        while (!que.isEmpty()) {
            TreeNode node = que.pollFirst();
            list.add(node.val);
            if (node.left != null) {
                que.add(node.left);
            }
            if (node.right != null) {
                que.add(node.right);
            }
        }
        int[] ints = new int[list.size()];
        int i = 0;
        for (Object num : list) {
            ints[i] = (int) num;
            i++;
        }
        return ints;
    }

    public char firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        ArrayList<Character> answerList = new ArrayList<>();
        ArrayList<Character> trashList = new ArrayList<>();
        for (char o : chars) {
            if (!answerList.contains(o) && !trashList.contains(o)) {
                answerList.add(o);
            } else if (answerList.contains(o)) {
                answerList.remove(Character.valueOf(o));
                trashList.add(o);
            }
        }
        if (answerList.size() == 0) {
            return ' ';
        }
        char answer = answerList.get(0);
        return answer;
    }
}

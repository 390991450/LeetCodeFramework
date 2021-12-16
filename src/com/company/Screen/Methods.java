package com.company.Screen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.company.Bean.TreeNode;

/*这里存放所有的算法*/
public class Methods {
    List<Integer> aList;
    List<Integer> bList;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //把两个树转换为char数组,进行比较
        aList = new ArrayList<>();
        changeToArray(B);
        bList = aList;
        aList = new ArrayList<>();
        changeToArray(A);
        for (int pointA = 0;pointA<aList.size();pointA++){
            if (aList.get(pointA).equals(bList.get(0))){
                boolean isEqual = true;
                for (int pointB = 1;pointB<bList.size();pointB++){
                    if (!aList.get(pointA+pointB).equals(bList.get(pointB))){
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual){
                    return true;
                }
            }
        }
        return false;
    }
    public void changeToArray(TreeNode a){
        if (a==null){
            aList.add(-1);
        }else {
            aList.add(a.val);
            changeToArray(a.left);
            changeToArray(a.right);
        }
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

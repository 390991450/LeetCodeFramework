package com.company.Screen;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.company.Bean.TreeNode;

/*这里存放所有的算法*/
public class Methods {
    public int[] levelOrder(TreeNode root) {
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

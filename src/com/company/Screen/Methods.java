package com.company.Screen;

import java.util.*;

import com.company.Bean.TreeNode;

import javax.naming.MalformedLinkException;

/*这里存放所有的算法*/

public class Methods {
    public int findJudge(int n, int[][] trust) {
        HashSet<Integer> falseSet = new HashSet<>();
        HashSet<Integer> trueSet = new HashSet<>();
        for (int[] ints:trust){
            falseSet.add(ints[0]);
            if (!falseSet.contains(ints[1])){
                trueSet.add(ints[1]);
            }
            if (trueSet.contains(ints[0])){
                trueSet.remove(ints[0]);
            }
        }
        if (trueSet.size()==1){
            int res = 0;
            for (Integer i:trueSet){
                res = i;
            }
            return res;
        }else {
            return -1;
        }
    }
    public int lengthOfLongestSubstring(String s) {
        int[] ints = new int[128];
        int right = 0;
        int left = 0;
        int res = 0;
        for (;left<s.length();left++){
            char c = s.charAt(left);
            if (ints[c]!= 0){
                //说明出现重复.right逐渐移动到重复字符所在位置
                //并且将路径上的字符置零
                res = Math.max(res,left-right);
                for (;right<ints[c]-1;right++){
                    ints[s.charAt(right)]=0;
                }
                ints[c] = left+1;
                right++;
            }else {
                ints[c] = left+1;
            }
        }
        res = Math.max(res,left-right);
        return res;
    }
    public int translateNum(int num) {
        int[] res = new int[3];
        int point = 1;
        res[0] = 1;
        String number = String.valueOf(num);
        if (num<10){
            return 1;
        }
        String pre = number.substring(0,2);
        if (pre.compareTo("26")<0&&pre.compareTo("10")>=0){
            res[1] = 2;
        }else {
            res[1] = 1;
        }
        for (int i = 3;i<=number.length();i++){
            point = (point+1)%3;
            pre = number.substring(i-2,i);
            if (pre.compareTo("26")<0&&pre.compareTo("10")>=0){
                res[point] = res[(point+1)%3]+res[(point+2)%3];
            }else {
                res[point] = res[(point+2)%3];
            }
        }
        return res[point];
    }
    public int maxValue(int[][] grid) {
        for (int i = 1;i<grid.length;i++){
            grid[i][0] = grid[i-1][0]+grid[i][0];
        }
        for (int i = 1;i<grid[0].length;i++){
            grid[0][i] += grid[0][i-1];
        }
        //初始化完成
        for (int i = 1;i<grid.length;i++){
            for (int j = 1;j< grid[0].length;j++){
                grid[i][j] = Math.max(grid[i-1][j]+grid[i][j],grid[i][j-1]+grid[i][j]);
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
    public int maxSubArray(int[] nums) {
        //因为每个位置都可以开始,所以每个位置都可以判断一次是不是要重新开始
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            res  = Math.max(dp[i],res);
        }
        return res;
    }
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = 0;
        int res = 0;
        while (min<prices.length){
            max = min;
            int point = max;
            while (point<prices.length-1){
                point++;
                if (prices[point]<=prices[min]){
                    break;
                }
                if (prices[point]>prices[max]){
                    max = point;
                }
            }
            res = Math.max(res,prices[max]-prices[min]);
            min = max+1;
        }
        return res;
    }
    public int numWays(int n) {
        int n1 = 1;
        int n2 = 2;
        if (n<=1){
            return 1;
        }else if (n==2){
            return 2;
        }
        for (int i = 3;i <= n;i++){
            int num = (n1+n2)%1000000007;
            n1 = n2;
            n2 = num;
        }
        return n2;
    }
    public int fib(int n) {
        int n1 = 0;
        int n2 = 1;
        if (n==0){
            return 0;
        }else if (n==1){
            return 1;
        }
        for (int i = 2;i <= n;i++){
            int num = (n1+n2)%1000000007;
            n1 = n2;
            n2 = num;
        }
        return n2;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root== null||(root.left==null&&root.right==null)){
            return true;
        }else if (root.left==null||root.right==null){
            return false;
        }else{
            return compareNode1(root.left,root.right);
        }
    }
    public boolean compareNode1(TreeNode left,TreeNode right){
        if (left==null&&right==null){
            return true;
        }else if (left==null||right==null||right.val!=left.val){
            return false;
        }else {
        return compareNode1(left.left,right.right)&&compareNode1(left.right,right.left);
        }
    }
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

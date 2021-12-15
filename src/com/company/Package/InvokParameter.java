package com.company.Package;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Queue;

import com.company.Bean.TreeNode;
import com.company.Utils.TrimClassName;

/*这个类存放各种数据的的注入方法*/
public class InvokParameter {
    public Object invokParameter(String str, Class clazz) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = TrimClassName.TrimClassName(clazz, "ChangeTo");
        Method method = this.getClass().getMethod(methodName, String.class);
        return method.invoke(this, str);
    }

    //数组转为树
    public Object ChangeToTreeNode(String str) {
        //去掉中括号
        String src = str.substring(1, str.length() - 1);
        //把这些数转化为数组
        String[] strList = src.split(",");
        TreeNode root;
        TreeNode result = null;
        Queue<TreeNode> queue = new LinkedList<>();
        for (int i = 0; i < strList.length; i++) {
            //第一个数直接创建，且作为result返回
            if (i == 0) {
                root = new TreeNode(Integer.parseInt(strList[i]));
                result = root;
                queue.add(root);
            }
            //判断队列是否为空，不为空则弹出一个，并且创建左右孩子赋值后进入队列
            if (!queue.isEmpty()) {
                root = queue.poll();
            } else {
                break;
            }
            //加左子树,如果数组没读完且不为空就创建，赋值
            if (i + 1 < strList.length && !strList[i + 1].equals("null")) {
                root.left = new TreeNode(Integer.parseInt(strList[i + 1]));
                queue.add(root.left);
            }
            if (i + 2 < strList.length && !strList[i + 2].equals("null")) {
                root.right = new TreeNode(Integer.parseInt(strList[i + 2]));
                queue.add(root.right);
            }
            //因为抛开第一个数字时，每次往树中加两个元素，所以i++
            i++;
        }
        return result;
    }

    public Object ChangeToString(String str) {
        return str;
    }
}

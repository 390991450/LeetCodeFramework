package com.company.Package;

import com.company.Bean.TreeNode;
import com.company.Utils.TrimClassName;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;

/*这个类存放各种数据的打印方法*/
public class Print {
    public void InvokePrint(Object obj, Class clazz) throws  InvocationTargetException, IllegalAccessException {
        String methodName = TrimClassName.TrimClassName(clazz, "Print");
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, Object.class);
        } catch (NoSuchMethodException e) {
            System.out.println("缺少输出方法");
        }
        method.invoke(this, obj);
    }
    //输出int
    public void Printint(Object obj){
        System.out.println((int) obj);
    }
    //输出TreeNode
    public void PrintTreeNode(Object obj){
        //使用队列进行层序遍历
        TreeNode node = (TreeNode)obj;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        StringBuffer res = new StringBuffer("[");
        deque.add(node);
        while (!deque.isEmpty()){
            node = deque.pollFirst();
            res.append(node.val);
            res.append(",");
            if (node.left!=null){
                deque.add(node.left);
            }
            if (node.right!=null){
                deque.add(node.right);
            }
        }
        res.deleteCharAt(res.length()-1);
        res.append("]");
        System.out.println(res);
    }
    //输出boolean
    public void Printboolean(Object obj){
        System.out.println((boolean) obj);
    }
    //输出List
    public void PrintList(Object obj){
        ArrayList<Object> list = new ArrayList<>();
        if (obj instanceof ArrayList<?>){
            list = (ArrayList<Object>)obj;
        }
        System.out.println(list.toString());
    }
    //输出int[]
    public void PrintI(Object obj) {
        int[] ints = (int[]) obj;
        StringBuffer str = new StringBuffer("[");
        for (int o : ints) {
            str.append(o);
            str.append(',');
        }
        str.deleteCharAt(str.length() - 1);
        str.append(']');
        System.out.println(str);
    }
}

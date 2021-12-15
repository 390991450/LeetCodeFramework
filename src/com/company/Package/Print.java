package com.company.Package;

import com.company.Utils.TrimClassName;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*这个类存放各种数据的打印方法*/
public class Print {
    public void InvokePrint(Object obj, Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = TrimClassName.TrimClassName(clazz, "Print");
        Method method = this.getClass().getMethod(methodName, Object.class);
        method.invoke(this, obj);
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

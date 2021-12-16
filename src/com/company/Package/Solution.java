package com.company.Package;

import com.company.Package.InvokParameter;
import com.company.Screen.Methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*这个类负责调用下层方法,实现方法的自动调用和结果的输出*/
public class Solution {
    public static void solution(String methodStr, String... parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        //我们要把method传给一个方法,让它用拿到的method取得方法库中的方法
        Methods methods = new Methods();
        Class cls = methods.getClass();
        Object[] objects = new Object[parameters.length];
        Method[] methodsArray = cls.getMethods();
        Method method = null;
        for (Method m : methodsArray) {
            if (m.getName().equals(methodStr)) {
                method = m;
                break;
            }
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        int i = 0;
        for (String parameter : parameters) {
            //在这里调用一个方法,这个方法要拿到str以及一个class对象
            //并将这个str转换为这个class对象
            Class clazz = parameterTypes[i];
            InvokParameter a = new InvokParameter();
            objects[i] = a.invokParameter(parameter, clazz);
            i++;
        }
        Object answer = method.invoke(methods, objects);
        //在这里要写一个方法,能够根据返回类型做出不同输出
        Print print = new Print();
        print.InvokePrint(answer, method.getReturnType());
    }
}

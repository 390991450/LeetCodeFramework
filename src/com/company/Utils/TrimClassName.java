package com.company.Utils;

/*在这里传入类名以及要附加字符串,生产需要的方法名*/
public class TrimClassName {
    public static String TrimClassName(Class clazz, String plus) {
        String clazzName = clazz.getName();
        //在这里处理一下类名
        String[] split = clazzName.split("\\.");
        clazzName = split[split.length - 1];
        //因为基本数据类型有一个[,所以要判断一下,有就删除
        if (clazzName.charAt(0) == '[') {
            clazzName = clazzName.substring(1, clazzName.length());
        }
        String methodName = plus + clazzName;
        return methodName;
    }
}

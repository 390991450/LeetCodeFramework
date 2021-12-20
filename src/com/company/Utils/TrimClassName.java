package com.company.Utils;

import com.company.Bean.*;
/*在这里传入类名以及要附加字符串,生产需要的方法名*/
public class TrimClassName {

    public static String TrimClassName(Class clazz, String plus) {
        String clazzName = clazz.getName();
        //在这里处理一下类名
        String[] split = clazzName.split("\\.");
        clazzName = split[split.length - 1];
        //因为基本数据类型有[,所以要判断一下,有就改为r
        int i = 0;
        for (;clazzName.charAt(i) == '[';) {
            i++;
        }
        clazzName = clazzName.substring(i,clazzName.length());
        for (int j = 0;j<i;j++){
            clazzName = "r"+clazzName;
        }
        String methodName = plus + clazzName;
        return methodName;
    }
}

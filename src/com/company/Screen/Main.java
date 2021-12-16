package com.company.Screen;

import com.company.Package.Solution;

import java.lang.reflect.InvocationTargetException;

/*在这里进行数据的输入*/
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Solution.solution("isSubStructure", "[1,2,3,4]","[3]");
    }
}

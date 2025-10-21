//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.lab2nd;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        MethodContainer obj = new MethodContainer();
        Method[] var2 = obj.getClass().getDeclaredMethods();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Method method = var2[var4];
            if (Modifier.isProtected(method.getModifiers()) || Modifier.isPrivate(method.getModifiers())) {
                method.setAccessible(true);
                int repeats = getRepeatCount(method);

                for(int i = 0; i < repeats; ++i) {
                    Object[] params = createParams(method.getParameterTypes(), i);
                    method.invoke(obj, params);
                }
            }
        }

    }

    private static int getRepeatCount(Method method) {
        RepeatAnnotation annotation = (RepeatAnnotation)method.getAnnotation(RepeatAnnotation.class);
        return annotation != null ? annotation.value() : 1;
    }

    private static Object[] createParams(Class<?>[] paramTypes, int index) {
        Object[] params = new Object[paramTypes.length];

        for(int i = 0; i < paramTypes.length; ++i) {
            if (paramTypes[i] == Integer.TYPE) {
                params[i] = index;
            } else if (paramTypes[i] == Double.TYPE) {
                params[i] = (double)index + 1.234;
            } else if (paramTypes[i] == Boolean.TYPE) {
                params[i] = index % 2 == 0;
            } else if (paramTypes[i] == String.class) {
                params[i] = "strimg" + index;
            }
        }

        return params;
    }
}

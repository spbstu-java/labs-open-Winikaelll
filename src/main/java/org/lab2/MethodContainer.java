//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.lab2;

public class MethodContainer {
    public MethodContainer() {
    }

    @RepeatAnnotation(1)
    protected void performProtectedAction(double number) {
        System.out.println("Protected method: " + number);
    }

    @RepeatAnnotation(4)
    protected String combineProtectedData(String a, String b) {
        String result = a + " " + b;
        System.out.println("Protected combine: " + result);
        return result;
    }

    @RepeatAnnotation(2)
    private void executePrivateTask(boolean flag) {
        System.out.println("Private method: " + flag);
    }

    @RepeatAnnotation(3)
    private void processPrivateValue(int value) {
        System.out.println("Private process: " + value);
    }

    public void publicMethodOne(String text) {
        System.out.println("Public: " + text);
    }
}

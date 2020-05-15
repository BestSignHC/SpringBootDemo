package com.hecheng.mockito;

public class Foo {
    public void fun1(String arg1, Integer arg2) {
        System.out.printf("%s & %d%n", arg1, arg2);
    }

    public void fun2(Bar bar) {
        System.out.printf("%s %d%n", bar.getName(), bar.getId());
    }

    public void fun3() {
        Bar bar = new Bar();
        bar.bar1();
        bar.bar2();
        bar.bar2();
    }
    public void fun4(Bar bar) {
        bar.bar1();
        bar.bar2();
        bar.bar2();
    }
}

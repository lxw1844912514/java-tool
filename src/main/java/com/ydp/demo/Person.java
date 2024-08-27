package com.ydp.demo;

public interface Person {
    @Deprecated
    public String name();

    public int age();

    @Deprecated // 废弃的意思
    public void sing();

}

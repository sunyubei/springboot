package com.nynu.springboot.entity;

/**
 * 单例模式
 */
public class Pattern {

    private static Pattern singleton;

    private Pattern(){

    }

    /**
     * 加锁的单例模式
     */
    public static Pattern getInstance(){
        if (singleton == null){
            synchronized (Pattern.class){
                if (singleton == null){
                    singleton = new Pattern();
                }
            }
        }
        return singleton;
    }

}

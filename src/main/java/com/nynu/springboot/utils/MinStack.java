package com.nynu.springboot.utils;

import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;

import java.util.Stack;

/**
 * @Author: 王纪勇
 * @Date: 2022/5/17 11:35
 * @Description: 自定义整形栈（栈中最小元素）
 */
public class MinStack {

    private Stack<A> stack;

    public MinStack(){
        stack = new Stack<A>();
    }

    /**
     * 入栈
     */
    public void push(int x){
        if (stack.isEmpty()){
            stack.push(new A(x, x));
        } else {
            A peek = stack.peek();
            stack.push(new A(x, Math.min(peek.min, x)));
        }
    }

    /**
     * 出栈
     */
    public void pop(){
        stack.pop();
    }

    /**
     * 查看栈顶元素
     */
    public int top(){
        return stack.peek().x;
    }

    /**
     * 查看栈中最小元素
     */
    public int min(){
        return stack.peek().min;
    }

    class A{
        private int x;
        private int min;

        public A(int x, int min) {
            this.x = x;
            this.min = min;
        }
    }

}

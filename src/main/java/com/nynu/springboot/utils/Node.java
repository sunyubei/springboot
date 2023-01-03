package com.nynu.springboot.utils;


import java.io.Serializable;

/**
 * @Author: 王纪勇
 * @Date: 2022/5/9 15:35
 * @Description: 单链表节点
 */
public class Node implements Serializable {
    private static final Long serialVersionUID = 1L;

    Object data;
    Node next;

    public Node(){
        next = null;
    }

    public Node(Object data){
        this.data=data;
        this.next=null;
    }

}

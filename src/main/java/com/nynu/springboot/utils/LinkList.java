package com.nynu.springboot.utils;


import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 王纪勇
 * @Date: 2022/5/9 15:55
 * @Description: 单链表
 */

@Data
public class LinkList implements Serializable {
    private static final Long serialVersionUID = 1L;

    /**
     * 头节点
     */
    public Node first;
    /**
     * 长度
     */
    public int size;

    /**
     * 无参构造函数
     */
    public LinkList(){
        this.first = null;
        this.size = 0;
    }

    /**
     * 获取链表中元素个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 判空
     */
    public boolean isEmpty(){
        return first==null;
    }

    /**
     * 创建一个空的单项链表
     */
    public LinkList createEmptyLinkList(){
        return new LinkList();
    }

    /**
     * 插入一个节点（头插法）
     */
    public void addFirst(Object obj){
        Node node = new Node(obj);
        node.next = first;
        first = node;
        size++;
    }

    /**
     * 插入一个节点（尾插法）
     */
    public void addEnd(Object obj){
        if (size == 0){
            addFirst(obj);
        } else {
            Node lastNode = getNodeByIndex(size - 1);
            Node node = new Node(obj);
            lastNode.next = node;
            size++;
        }
    }

    /**
     * 在指定位置插入一个节点
     * succes: return: 1
     * faile: return : 0
     */
    public int addAppoint(int index, Object obj){
        if (index > size){
            throw new IllegalArgumentException("位置不合法");
        }
        if (index == size){
            addEnd(obj);
            return 1;
        }
        if (index == 0){
            addFirst(obj);
            return 1;
        }

        Node prevNode = getNodeByIndex(index);
        Node afterNode = prevNode.next;

        Node node = new Node(obj);
        node.next =afterNode;
        prevNode.next = node;
        size++;
        return 1;
    }

    /**
     * 删除下标为index的节点
     */
    public int delByIndex(int index){
        if (index >= size){
            throw new IllegalArgumentException("位置不合法");
        }
        if (size == 1){

        }
        if (index == 0){
            Node node = getNodeByIndex(index);

        }

        return 1;

    }

    /**
     * 获取链表中的下标为index的节点
     */
    public Node getNodeByIndex(int index){
        if (index >= size){
            throw new IllegalArgumentException("位置不合法");
        }
        Node node = first;
        for (int i=0; i<index-1; i++){
            node = node.next;
        }
        return node;
    }

    /**
     * 获取链表中下标为index的节点的值
     */
    public Object getValueByIndex(int index){
        Node node = getNodeByIndex(index);
        return node.data;
    }

    /**
     * 获取链表中值为val的节点的下标
     */
    public int getIndexByValue(Object val){
        Node node = this.first;
        int index = 0;
        while (node.data != val){
            index++;
            node = node.next;
        }
        return index;
    }

    /**
     * 遍历输出一个链表
     */
    public void printLinkList(LinkList linkList){
        for (int i=0; i<=linkList.size; i++){
            System.out.println(linkList.getValueByIndex(i));
        }
    }

}

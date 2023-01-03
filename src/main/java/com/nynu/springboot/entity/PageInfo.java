package com.nynu.springboot.entity;

import lombok.ToString;
import org.apache.bcel.verifier.statics.LONG_Upper;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体
 */

@ToString
public class PageInfo<T> implements Serializable {

    /**
     * 起始数
     */
    private int start;

    /**
     * 每页数据条数
     */
    private int pageSize;

    /**
     * 查询到的总记录数
     */
    private int totalNum;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 保存页面数据
     */
    private List<T> list;

    /**
     * 总条数
     */
//    public static Long getTotalElements(){
//
//    }

    /**
     * 总页数
     */
//    public static int getTotaoPage(){
//
//    }












































}

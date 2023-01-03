package com.nynu.springboot.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String id;

    private String userName;

    private String passWord;

//    private Integer page;

//    private Integer limit;
}
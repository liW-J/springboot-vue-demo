package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private  Integer id;
    private  String name;
    private Integer studentNumber;
    private String collegeMajor;
    private String department;
    private String introduction;

    


}

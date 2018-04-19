package com.liuchuanzheng.greendaotest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 刘传政 on 2018/4/19 0019.
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:第六步
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private int age;
    @Generated(hash = 1156616942)
    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

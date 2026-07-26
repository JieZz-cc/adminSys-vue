package com.itheima.Api;

import com.itheima.StaticLearn.ArrayUtil;

// cloneable 接口里面没有抽象方法，表示是一个标记下接口，
// cloneable 表示一旦实现，当前类的对象就看被clone，如果没实现就不能被clone

public class User implements Cloneable{
    private int id;
    private String username;
    private String password;
    private String path;
    private int[] data;


    public User() {
    }

    public User(int id, String username, String password, String path, int[] data) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.path = path;
        this.data = data;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取
     * @return data
     */
    public int[] getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(int[] data) {
        this.data = data;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", path = " + path + ", data = " + arrToString() + "}";
    }

    public String arrToString() {
        return ArrayUtil.printArr(data);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // protected 只能本包中的类或其他包中的子类使用

//        return super.clone();       // 浅克隆

        // 深克隆
        int[] data = this.data;     // 先把被克隆对象的数组获取
        int[] newData = new int[data.length];   // 创建新数组
        System.arraycopy(data, 0, newData, 0, data.length );

        User u = (User) super.clone();
        u.data = newData;
        return u;
    }
}

package com.itheima.ToObject;

import java.util.Random;

public class Role {
    private String name;
    private int blood;


    public Role() {
    }

    public Role(String name, int blood) {
        this.name = name;
        this.blood = blood;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return blood
     */
    public int getBlood() {
        return blood;
    }

    /**
     * 设置
     * @param blood
     */
    public void setBlood(int blood) {
        this.blood = blood;
    }

    public String toString() {
        return "Role{name = " + name + ", blood = " + blood + "}";
    }
    public void attack(Role role) {
        Random r = new Random();
        int hurt = r.nextInt(20) + 1;
        int remainBlood = (role.getBlood() - hurt) > 0 ? role.getBlood() - hurt : 0;
        role.setBlood(remainBlood);
        System.out.println(
                this.name + "踢了" + role.getName() + "一脚, 造成" + hurt + "点伤害,"  + role.getName() + "还剩" + remainBlood + "点血;"
        );
    }
}

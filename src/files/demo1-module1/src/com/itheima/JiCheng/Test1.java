package com.itheima.JiCheng;

public class Test1 {
    public static void main(String[] args) {
        // 猫对象  可直接调用直接父类和间接父类中的方法
        LiHuaMao l1 = new LiHuaMao();
        l1.eat();               // 间接父类
        l1.catchMouse();        // 直接父类

        // 狗对象
        HaSiKey h1 = new HaSiKey();
        h1.eat();
        h1.drink();
        h1.watchHome();
    }
}

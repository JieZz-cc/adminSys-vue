package com.itheima.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CollectionsDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

//        System.out.println(Collections.addAll(list, "aa", "vv", "cc","dd"));    // 返回boolean类型

        // 打乱集合顺序
//        Collections.shuffle(list);


//        System.out.println(list);


        // 练习1
        Collections.addAll(list,0,0,0,1,1,1,1,1,1,1);
        Random r = new Random();
        int num = list.get(r.nextInt(list.size()));

        ArrayList<String> maleList = new ArrayList<>();
        ArrayList<String> femaleList = new ArrayList<>();

        Collections.addAll(maleList, "aa", "bb", "cc","vv","bb","nn","mm");
        Collections.addAll(femaleList, "qq", "ww", "ee","rr","tt","yy","uu");
        System.out.println(num);
        if(num == 1) {
             Collections.shuffle(maleList);
            System.out.println(maleList.get(0));
        } else {
            Collections.shuffle(femaleList);
            System.out.println(femaleList.get(0));
        }

    }
}

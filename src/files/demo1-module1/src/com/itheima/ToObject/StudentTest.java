package com.itheima.ToObject;

public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student("001", "张三", 18);
        Student s2 = new Student("002", "李四", 18);
        Student s3 = new Student("003", "王二", 18);

        Student[] arr = new  Student[3];
        arr[0] = s1;
        arr[1] = s2;
        arr[2] = s3;

        Student s4 = new Student("004", "赵六", 18);
        boolean flag = contains(arr, s4.getId());
        if (flag) {
            System.out.println("当前学生信息已存在");
        } else {
            int count = getCount(arr);
            // s1, s2, null, count = 2
            // s1, s2, s3, count = 3
            // 1.数组已经存满，加1
             if (count == arr.length) {
                Student[] newArr = createNewArr(arr);
                newArr[count] = s4;
                 printArr(newArr);
             } else {
                 arr[count] = s4;
                 printArr(arr);
             }
        }

    }

    // 打印数组数据
    public static void printArr(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getId() + " " + arr[i].getName() + " " + arr[i].getAge());
        }
    }

    // 看看数组是否已经存满
    public static int getCount(Student[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) count++;
        }
        return count;
    }

    // 创建新数组
    public static Student[] createNewArr(Student[] arr) {
        Student[] newArr = new Student[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    // 判断学生id是否重复
    public static boolean contains(Student[] arr, String id) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].getId() == id) return true;
        }
        return  false;
    }
}

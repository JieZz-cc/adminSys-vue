package com.itheima.StudentManage;

import com.itheima.ToObject.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    public static void main(String[] args) {
        // 账户
        ArrayList<User> userList = new ArrayList<>();

        // 学生数据
        ArrayList<Student> studentList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作: 1: 登录; 2: 注册; 3: 忘记密码; 4: 退出");
            String choice = sc.next();
            switch (choice) {
                case "1" -> login(userList);
                case "2" -> register(userList);
                case "3" -> forget();
                case "4" -> exit();
                default -> System.out.println("无此选项");
            }
        }
    }

    // ctrl + alt + t 快捷键
    // 登录
    public static void login(ArrayList<User> userList) {

        while (true) {
            System.out.println("请输入用户名和密码");
            Scanner sc = new Scanner(System.in);
            String loginUser = sc.next();
            System.out.println("请输入密码");
            String logWord = sc.next();
            int num = checkLogin(userList, loginUser, logWord);
            if (num == -1) {
                System.out.println("请重新确认用户名和密码");
                continue;
            } else {
                printUserArray(userList);
                break;
            }
        }
    }

    public static void register(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        // 用户名校验
        String validUser = "";
        while (true) {
            System.out.println("请输入用户名");
            String user = sc.next();

            // 用户名长度校验
            boolean userValid = checkLength(user, 3, 10);

            if (!userValid) {
                System.out.println("请输入合法用户名,长度在3至10之间");
                continue;
            } else {
                validUser = user;
                // 用户唯一性校验
                boolean userOnly = isContainsUser(list, validUser);
                if (!userOnly) {
                    continue;
                } else {
                    break;
                }
            }
        }

        // 密码校验
        String validPassWord = "";
        while (true) {
            System.out.println("请输入密码");
            String mi = sc.next();
            boolean isValid = checkMi(mi);
            if (!isValid) {
                continue;
            } else {
                validPassWord = mi;
                break;
            }
        }
        // 确认密码
        while (true) {
            System.out.println("请确认密码");
            String confirmPassWord = sc.next();
            if (confirmPassWord.equals(validPassWord)) {
                System.out.println("注册成功");
                Student student = new Student("001", "张三", 18);
                User u1 = new User(validUser, validPassWord, student);
                list.add(u1);
                printUserArray(list);
//                System.exit(0);
                break;
            } else {
                System.out.println("与上次输入密码不一致,请检查");
                continue;
            }
        }
    }

    public static void forget() {
        System.out.println("忘记密码");
    }

    public static void exit() {
        System.out.println("已退出");
        System.exit(0);         // 结束进程
    }

    // 注册 校验密码是否合格
    public static boolean checkMi(String str) {
//        Pattern compile = Pattern.compile(".*[0-9a-zA-z].*");  // 正则校验数字或字母
//        Matcher matcher = compile.matcher(str);
//        boolean flag = matcher.matches();
        String regx = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";   // 8 -16位, 必须是数字加字母的组合
        boolean flag = str.matches(regx);
        if (!flag) {
            System.out.println("密码只能是数字加字母的组合,长度在8到16之间");
            return false;
        } else {
            return true;
        }
    }

    // 校验字符串长度
    public static boolean checkLength(String str, int min, int max) {
        int len = str.length();
        if (len < min || len > max) {
//            System.out.println("请输入有效的长度, 在" + min + "~" + max + "之间");
            return false;
        } else {
            return true;
        }
    }

    // 校验用户是否存在
    public static boolean isContainsUser(ArrayList<User> list, String user) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(user)) {
                System.out.println("已存在的用户,请重新输入");
                return false;
            }
        }
        return true;
    }

    // 打印当前的用户集合里的数据
    public static void printUserArray(ArrayList<User> list) {
        System.out.println("当前用户信息");
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            System.out.println(u.getName());
            System.out.println(u.getStudents().getId() + " " + u.getStudents().getName() + " " + u.getStudents().getAge());
        }
    }

    // 用户登录校验
    public static int checkLogin(ArrayList<User> userList, String user, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equals(user) && userList.get(i).getPassWord().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    // 添加学生数据
    public static void addStudent(ArrayList<User> userList) {
        Student s1 = new Student("001", "张三", 18);
        Student s2 = new Student("002", "李四", 19);
        Student s3 = new Student("003", "王五", 20);
        Student s4 = new Student("004", "赵六", 21);
//        userList.add
    }
}

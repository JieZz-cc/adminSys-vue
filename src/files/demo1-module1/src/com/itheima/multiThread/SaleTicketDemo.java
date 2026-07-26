package com.itheima.multiThread;

public class SaleTicketDemo {
    public static void main(String[] args) {
        SaleTicket s1 = new SaleTicket();
        SaleTicket s2 = new SaleTicket();
        SaleTicket s3 = new SaleTicket();

        s1.setName("窗口1");
        s2.setName("窗口2");
        s3.setName("窗口3");

        s1.start();
        s2.start();
        s3.start();
    }
}

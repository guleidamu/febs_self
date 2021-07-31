package com.damu.febs.server.test.thread.ticket;

public class SaleTicket {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ticket());
        thread1.setName("线程1");
        Thread thread2 = new Thread(new ticket());
        thread2.setName("线程2");

        thread1.start();
        thread2.start();
    }
}

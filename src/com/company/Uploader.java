package com.company;

import java.util.concurrent.CountDownLatch;

    public class Uploader implements Runnable {
        final private CountDownLatch cdl;
        final private String name;
        final private int fileSize;
        final private int loadSpeed;


    public Uploader(CountDownLatch cdl, String name, int fileSize, int loadSpeed) {
            this.cdl = cdl;
            this.name = name;
            this.fileSize = fileSize;
            this.loadSpeed = loadSpeed;

    }

    public String getName() {
            return name;
    }


        @Override
    public void run() {
        try {
                cdl.countDown();
                System.out.println(name + " загрузка займет примерно  " + (fileSize / loadSpeed) + " секунд ");
                cdl.await();
                System.out.print(" ❏ ❐ ❑");
                Thread.sleep(fileSize / loadSpeed * 50);
                System.out.print(" ❏ ❐ ❏");
                Thread.sleep(fileSize / loadSpeed * 50);
                System.out.print(" ❏ ❐ ❑");
                Thread.sleep(fileSize / loadSpeed * 50);
                System.out.println(name + " загружен в облако хранения ");

                if (cdl.getCount() == 0) {
                    Thread.sleep(20500);/*i know this is hard code */
                    System.out.println(name + " удален из хранилища ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
    }
}

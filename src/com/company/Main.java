package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        CountDownLatch countDL = new CountDownLatch(1);
        CountDownLatch cdl = new CountDownLatch(10);


        Semaphore semaphore = new Semaphore(3);
        Uploader uploader = new Uploader(countDL, " файл ", 500, 20);
        new Thread(uploader).start();
        for (int i = 1; i <= 10; i++) {
            new Thread(new Downloader(uploader,cdl,semaphore,i)).start();
        }

    }
}

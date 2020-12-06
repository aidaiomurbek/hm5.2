package com.company;


    import java.util.concurrent.CountDownLatch;
    import java.util.concurrent.Semaphore;

    public class Downloader implements Runnable {
        final private Uploader uploader;
        final private CountDownLatch cDl;
        final private Semaphore semaphore;
        final private int user;


        public Downloader(Uploader uploader, CountDownLatch cDl, Semaphore semaphore, int user) {
            this.semaphore = semaphore;
            this.cDl = cDl;
            this.uploader = uploader;
            this.user = user;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(4000);
                cDl.countDown();
                System.out.println(" Пользователь " + user + " ожидает загрузки");
                cDl.await();
                Thread.sleep(4000);
                semaphore.acquire();
                System.out.println(" Пользователь " + user + " начал скачивать" + uploader.getName());
                Thread.sleep(4000/*uploader.getFileSize()/(uploader.getLoadSpeed()*5)*/);
                System.out.println(" Пользователь " + user + " загрузка успешно завершена - скачан" + uploader.getName());
                semaphore.release();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}

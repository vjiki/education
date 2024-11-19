public class DeadLock {

    private static void deadLock() {
        Object o1 = new Object();
        Object o2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (o1) {
                System.out.println("locked o1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o2) {
                    System.out.println("locked o2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (o2) {
                System.out.println("locked o2");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o1) {
                    System.out.println("locked o1");
                }
            }
        });

//        thread1.setDaemon(true);
//        thread2.setDaemon(true);
        thread1.start();
        thread2.start();

        System.out.println("end");

    }

    public static void main(String[] args) {
        deadLock();
        System.out.println("end");
    }
}

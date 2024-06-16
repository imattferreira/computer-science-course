public class Main {
    public static void main(String[] args) {
//        var first = new MyThread("Cleitin", 101);
//        var second = new MyThread("Roberson", 100);
//
//        first.start();
//        second.start();
//
//        try {
//            first.join(); // block main thread waiting to children execution to be finished
//            second.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("Main thread is DEAD");

        var first = new MyRunnableThread("Cleitin", 101);
        var second = new MyRunnableThread("Roberson", 100);

        var t1 = new Thread(first);
        var t2 = new Thread(second);

        t1.start();
        t2.start();

        try {
            t1.join(); // block main thread waiting to children execution to be finished
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread is DEAD");
    }
}
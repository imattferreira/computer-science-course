public class MyRunnableThread implements Runnable {
    private final String name;
    private final int time;

    public MyRunnableThread(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(this.name + " -> " + i);
                Thread.sleep(this.time);
            }
        } catch (InterruptedException err) {
            err.printStackTrace();
        }

        System.out.println("Execution finished (DEAD)");
    }
}

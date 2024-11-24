package threads;

class lab10q1 extends Thread {
    public void run() {
        for (int i = 0; i <= 4; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        lab10q1 thread = new lab10q1();
        thread.start();
    }
}

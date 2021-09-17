package lecture2homework;

public class PrintPersonThread extends Thread {
    private ImmutablePerson immutablePerson;
    public PrintPersonThread(ImmutablePerson immutablePerson) {
        this.immutablePerson = immutablePerson;
    }
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " prints " + immutablePerson);
        }
    }
}


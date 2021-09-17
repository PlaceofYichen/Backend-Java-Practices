package lecture2homework;

public class Main {
    public static void main(String[] args) {
        ImmutablePerson alice = new ImmutablePerson("Alice", "Alaska");
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
    }
}

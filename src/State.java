import java.util.Scanner;

public abstract class State {
    protected AppData appData;

    public State(AppData appData) {
        this.appData = appData;
    }

    public abstract void display();
    public abstract State handleInput(Scanner scanner) throws Exception;
}

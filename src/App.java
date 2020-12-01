import java.util.Scanner;
import java.util.Stack;

public class App {

    private AppData appData;
    private Stack<State> stateStack;
    private void printHeader() {
        System.out.println("********************************************");
        System.out.println("           Student Management App           ");
        System.out.println("                     Author: Vlad Slivilescu");
    }

    public App() throws Exception {
        appData = new AppData();
        stateStack = new Stack<>();

        stateStack.push(new InitialState(appData));
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (!stateStack.empty()) {
            Utils.clearScreenWindows();
            this.printHeader();
            stateStack.peek().display();
            State nextState = stateStack.peek().handleInput(scanner);
            if (nextState == null)
                stateStack.pop();
            else
                stateStack.push(nextState);
        }
        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }
}
public class Utils {
    public static void clearScreenWindows() throws Exception{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}

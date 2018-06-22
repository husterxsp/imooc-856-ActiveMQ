import java.io.IOException;

public class Test {
    public static void start() throws IOException, RuntimeException {
        throw new RuntimeException("Not able to Start");
    }

    public static void main(String args[]) {
        try {
            start();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } catch (Exception re) {
            re.printStackTrace();
        }
    }
}

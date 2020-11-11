import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Semantics {

    private String text;

    public Semantics(String filePath) throws FileNotFoundException {
        readFromFile(filePath);
    }

    public void readFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()){
            text += scanner.nextLine();
        }
    }

    public String getText() {
        return text;
    }
}

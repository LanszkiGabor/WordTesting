import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Szemantikai elemzést vagy szöveg generálást akarsz? (0 = Szema. 1 = Bull.)");
        int userNum = scanner.nextInt();

        System.out.println("Adj meg egy szöveget! A szöveg végére írd be: /end");
        String emptyText = scanner.nextLine();
        String userText = scanner.nextLine();

        while (!userText.equals("/end")) {
            writeUserTextIntoFile(userText);
            userText = scanner.nextLine();
        }

        if (userNum == 0){
            Semantics semantics = new Semantics("Files/UserText.txt");
            semantics.printInfo();
            deleteUserTextFile();
        } else {
            BullShitGenerator bullShitGenerator = new BullShitGenerator("Files/UserText.txt");
        }

    }

    public static void writeUserTextIntoFile(String userText) throws IOException {
        FileWriter writer = new FileWriter("Files/UserText.txt", true);
        writer.write("\n" + userText);
        writer.close();
    }

    public static void deleteUserTextFile() throws IOException {
        FileWriter writer = new FileWriter("Files/UserText.txt");
        writer.write("");
        writer.close();
    }

}



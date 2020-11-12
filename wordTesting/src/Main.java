import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Szemantikai elemzést vagy szöveg generálást akarsz? (0 = Szema. 1 = Bull.)");
        int userNum = scanner.nextInt();
        if (userNum == 0){
            Semantics semantics = new Semantics("Files/Text1.txt");
            semantics.printInfo();
        } else {
            BullShitGenerator bullShitGenerator = new BullShitGenerator("Files/Text1.txt");
        }



    }





}



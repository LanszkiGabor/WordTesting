import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Semantics semantics = new Semantics("Files/Text1.txt");

        // System.out.println(semantics.getText());
        String[] textInArray = separateTheText(semantics.getText());
        System.out.println(textInArray[5]);
    }

    public static String[] separateTheText(String text) {
        String[] strings = text.split(" ");
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains(".")) {
                String[] strings2 = strings[i].split(".");
                if (strings2.length > 0) {
                    stringArrayList.add(strings2[0]);
                    stringArrayList.add(strings2[1]);
                } else {
                    stringArrayList.add(strings2[0]);
                }

            } else if (strings[i].contains(",")) {

            } else if (strings[i].contains("?")) {

            } else if (strings[i].contains("!")) {

            } else if (strings[i].contains(":")) {

            }
        }
        return strings;
    }


}

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Semantics semantics = new Semantics("Files/Text1.txt");

        //System.out.println(semantics.getText());
        ArrayList<String> textInArray = separateTheText(semantics.getText());
        System.out.println(textInArray.toString());
    }

    public static ArrayList<String> separateTheText(String text) {
        String[] strings = text.split(" ");
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains(".")) {
                String[] strings2 = strings[i].split(".");
                for (int j = 0; j < strings2.length; j++) {
                    stringArrayList.add(strings2[j]);
                }
            } else if (strings[i].contains(",")) {
                String[] strings2 = strings[i].split(",");
                for (int j = 0; j < strings2.length; j++) {
                    stringArrayList.add(strings2[j]);
                }
            } else if (strings[i].contains("?")) {
                String[] strings2 = strings[i].split("\\?");
                for (int j = 0; j < strings2.length; j++) {
                    stringArrayList.add(strings2[j]);
                }
            } else if (strings[i].contains("!")) {
                String[] strings2 = strings[i].split("!");
                for (int j = 0; j < strings2.length; j++) {
                    stringArrayList.add(strings2[j]);
                }
            } else if (strings[i].contains(":")) {
                String[] strings2 = strings[i].split(":");
                for (int j = 0; j < strings2.length; j++) {
                    stringArrayList.add(strings2[j]);
                }
            } else if (strings[i].contains("\"")) {
                String[] strings2 = strings[i].split("\"");
                for (int j = 0; j < strings2.length; j++) {
                    stringArrayList.add(strings2[j]);
                }
            }
            else{
                stringArrayList.add(strings[i]);
            }
        }
        return stringArrayList;
    }
}

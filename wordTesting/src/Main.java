import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Semantics semantics = new Semantics("Files/Text1.txt");

        //System.out.println(semantics.getText());
        ArrayList<String> textInArray = separateTheText(semantics.getText());
        getOutTheSpecialCharsFromTheText(textInArray);
        System.out.println(textInArray.toString());
        System.out.println(findThe10MostPopularWord(textInArray));

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
            } else {
                stringArrayList.add(strings[i]);
            }
        }
        return stringArrayList;
    }


    public static void getOutTheSpecialCharsFromTheText(ArrayList<String> stringArrayList) {
        for (int i = 0; i < stringArrayList.size(); i++) {
            if (stringArrayList.get(i).length() > 1) {
                if (stringArrayList.get(i).charAt(0) < 65) {
                    stringArrayList.set(i, stringArrayList.get(i).substring(1));
                }
                if (stringArrayList.get(i).charAt(stringArrayList.get(i).length() - 1) < 65) {
                    stringArrayList.set(i, stringArrayList.get(i).substring(0, stringArrayList.get(i).length() - 1));
                }
            }
        }
    }

    public static boolean isExceptionWord(String word) {
        String[] exceptionWords = {"a", "az", "és", "hogy", "s", "egy", "nem", "-", "is", "de", "meg", "csak", "sem", "azt",
        "mint", "ezt", "el", "ez", "ha", "ő", "van", "még", "volt", "A"};
        for (int i = 0; i < exceptionWords.length; i++) {
            if (exceptionWords[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> findThe10MostPopularWord(ArrayList<String> abc){
        HashMap<String, Integer> abcHashMap = new HashMap<>();
        for (int i = 0; i < abc.size(); i++) {
            abcHashMap.putIfAbsent(abc.get(i),0);
            int x = abcHashMap.get(abc.get(i)).intValue();
            abcHashMap.replace(abc.get(i), x+1);
        }

        ArrayList<String> top10WordsList = new ArrayList<>();

        int max = 0; // 68 -- 29
        int max2 = 0; // 29
        String maxStr = ""; // az
        for (Map.Entry<String, Integer> entry : abcHashMap.entrySet()) {
            if (entry.getValue() > max && !isExceptionWord(entry.getKey())) {
                max = entry.getValue();
                maxStr = entry.getKey();
            }
        }

        top10WordsList.add(maxStr);

        while (top10WordsList.size() < 10) {
            for (Map.Entry<String, Integer> entry : abcHashMap.entrySet()) {
                if (entry.getValue() > max2 && entry.getValue() < max && !isExceptionWord(entry.getKey())) {
                    max2 = entry.getValue();
                    maxStr = entry.getKey();
                }
            }
            top10WordsList.add(maxStr);
            max = max2;
            max2 = 0;
        }

        return top10WordsList;
    }
}

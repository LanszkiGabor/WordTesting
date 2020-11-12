import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Semantics semantics = new Semantics("Files/Text1.txt");

        System.out.println(semantics.getText());
        System.out.println(semantics.getTextInArray());
        System.out.println(semantics.findThe10MostPopularWord(semantics.getTextInArray()));
        /*
        getOutTheSpecialCharsFromTheText(textInArray);
        System.out.println(textInArray.toString());
        System.out.println(findThe10MostPopularWord(textInArray));
        System.out.println(top10PopularPhrases(textInArray, 2));
        System.out.println(findAllNames(textInArray));
         */
        System.out.println("mondatok összesen: " + countSentences(semantics.getText()));
        System.out.printf("átlagos szószám mondatonként: %.3f%n", countAvgWordsInSentences(semantics.getTextInArray(), semantics.getText()));

        System.out.println("a szövegben a magánhangzók száma: " + countLetters(semantics.getText(), "vowels"));
        System.out.println("a szövegben a mássalhangzók száma: " + countLetters(semantics.getText(), "consonants"));
        System.out.printf("a magánhangzók aránya: %.2f", rateOfWantedLetters(semantics.getText(), "vowels"));
        System.out.println("%");
        System.out.printf("a mássalhangzók aránya: %.2f", rateOfWantedLetters(semantics.getText(), "consonants"));
        System.out.println("%");

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

    // Gyűjtsd ki egy szöveg 10 leggyakoribb szavát!

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

    public static ArrayList<String> findThe10MostPopularWord(ArrayList<String> abc) {
        HashMap<String, Integer> abcHashMap = new HashMap<>();
        for (int i = 0; i < abc.size(); i++) {
            abcHashMap.putIfAbsent(abc.get(i), 0);
            int x = abcHashMap.get(abc.get(i)).intValue();
            abcHashMap.replace(abc.get(i), x + 1);
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

    // Gyűjtsd ki egy szöveg 10 leggyakoribb 2-3-4 szavas szófordulatát!


    public static ArrayList<String> top10PopularPhrases(ArrayList<String> abc, int number) {
        HashMap<String, Integer> abcHashMap = new HashMap<>();
        for (int i = 0; i < abc.size(); i++) {
            String toAdd = "";
            if (i == abc.size() - number - 1) {
                break;
            } else {
                for (int j = i; j < number + i; j++) {
                    toAdd += abc.get(j);
                    if (j < number + i - 1) {
                        toAdd += " ";
                    }
                }
                abcHashMap.putIfAbsent(toAdd, 0);
                int x = abcHashMap.get(toAdd).intValue();
                abcHashMap.replace(toAdd, x + 1);
            }
        }

        ArrayList<String> top10WordsList = new ArrayList<>();

        int max = 0;
        int max2 = 0;
        String maxStr = "";
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

    //Gyűjtsd ki egy szöveg 10 leggyakoribb nevét!

    public static HashSet<String> findAllNames(ArrayList<String> stringArrayList) throws FileNotFoundException {
        HashSet<String> namesHashSet = new HashSet<>();
        ArrayList<String> names = new ArrayList<>(readNamesFromFile());
        for (int i = 0; i < stringArrayList.size(); i++) {
            for (int j = 0; j < names.size(); j++) {
                if (stringArrayList.get(i).equals(names.get(j))) {
                    namesHashSet.add(stringArrayList.get(i));
                    break;
                }
            }
        }
        return namesHashSet;
    }

    public static ArrayList<String> readNamesFromFile() throws FileNotFoundException{
        Scanner fromFile = new Scanner(new File("Files/Names.txt"));
        ArrayList<String> names = new ArrayList<>();
        while (fromFile.hasNextLine()) {
            names.add(fromFile.nextLine().trim());
        }
        return names;
    }

    public static int countSentences(String text) {
        int sentencesNum = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '!' || text.charAt(i) == '?') {
                sentencesNum++;
            }
            if (text.charAt(i) == '.' && text.charAt(i - 1) != '.') {
                sentencesNum++;
            }
        }
        return sentencesNum;
    }

    public static float countAvgWordsInSentences(ArrayList<String> abc, String text) {
        int wordsNum = abc.size();
        int sentencesNum = countSentences(text);
        return (sentencesNum > 0 ? (float)wordsNum / sentencesNum : abc.size());
    }

    public static int countLetters(String text, String keyWord) {
        Character[] vowels = {'a', 'á', 'e', 'é', 'i', 'í', 'o', 'ó', 'ö', 'ő', 'u', 'ú', 'ü', 'ű'};
        int countVowels = 0;
        int countConsonants = 0;

        for (int i = 0; i < text.length(); i++) {
            boolean isConsonant = true;
            for (int j = 0; j < vowels.length; j++) {
                if (text.charAt(i) == vowels[j]) {
                    isConsonant = false;
                    break;
                }
            }
            if (isConsonant) {
                countConsonants++;
            } else {
                countVowels++;
            }
        }

        if (keyWord.equals("vowels")) {
            return countVowels;
        } else if (keyWord.equals("consonants")) {
            return countConsonants;
        } else {
            return countVowels + countConsonants;
        }

    }

    public static float rateOfWantedLetters(String text, String keyword) {
        int letters = countLetters(text, keyword);
        return (float)letters / text.length() * 100f;
    }

}



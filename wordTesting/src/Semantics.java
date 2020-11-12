import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Semantics {

    private String text = "";
    private ArrayList<String> textInArray = new ArrayList<>();

    public Semantics() {
    }

    public Semantics(String filePath) throws FileNotFoundException {
        text = readFromFile(filePath);
        separateTheText();
    }

    public String readFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        String string = "";
        while (scanner.hasNextLine()){
            string += scanner.nextLine();
        }
        return string;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getTextInArray() {
        return textInArray;
    }

    public void separateTheText() {
        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length; i++) {
            String text = strings[i];
            String specialChar = findSpecialChar(text); // . , ! : " ?
            if (specialChar != null) {
                switch (specialChar) { // számot, betűt, Stringet, enum
                    case ".": // abban az esetben, ha a specialChar == "."
                    case ",":
                    case "!":
                    case ":":
                    case "\"":
                        if (findPosOfSpecChar(text, specialChar) == 0) { // gyerek.Az
                            splitTextAndAddToList(text, specialChar);
                        } else {
                            addSubstringToList(text);
                        }
                        break;
                    case "?":
                        if (findPosOfSpecChar(text, specialChar) == 0) {
                            splitTextAndAddToList(text, "\\?");
                        } else {
                            addSubstringToList(text);
                        }
                        break;
                }
            } else {
                textInArray.add(text.toLowerCase());
            }
        }

        Iterator iterator = textInArray.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("-")) {
                iterator.remove();
            }
        }
    }

    private String findSpecialChar(String word) {
        if (word.contains(".")) {
            return ".";
        }
        if (word.contains(",")) {
            return ",";
        }
        if (word.contains("?")) {
            return "?";
        }
        if (word.contains("!")) {
            return "!";
        }
        if (word.contains(":")) {
            return ":";
        }
        if (word.contains("\"")) {
            return "\"";
        }
        return null;
    }

    private int findPosOfSpecChar(String word, String specialChar) {
        if (word.indexOf(specialChar) == word.length() - 1) {
            return 1;
        } else if (word.indexOf(specialChar) < word.length() - 1){
            return 0;
        }
        return -1;
    }

    private void splitTextAndAddToList(String text, String mark) {
        String[] splitText = text.split(mark);
        for (int i = 0; i < splitText.length; i++) {
            textInArray.add(splitText[i].toLowerCase());
        }
    }

    private void addSubstringToList(String text) {
        textInArray.add(text.substring(0, text.length() - 1).toLowerCase());
    }

    private boolean isExceptionWord(String word) {
        String[] exceptionWords = {"a", "az", "és", "hogy", "s", "egy", "nem", "-", "is", "de", "meg", "csak", "sem", "azt",
                "mint", "ezt", "el", "ez", "ha", "ő", "van", "még", "volt"};
        for (int i = 0; i < exceptionWords.length; i++) {
            if (exceptionWords[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    // 10 leggyakoribb szót

    public HashSet<String> findThe10MostPopularWord(ArrayList<String> abc, int number) {
        HashMap<String, Integer> abcHashMap = new HashMap<>();
        for (int i = 0; i < abc.size(); i++) {
            abcHashMap.putIfAbsent(abc.get(i), 0);
            int x = abcHashMap.get(abc.get(i)).intValue();
            abcHashMap.replace(abc.get(i), x + 1);
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

        while (top10WordsList.size() < number) {
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

        HashSet<String> topWordsHashSet = new HashSet<>(top10WordsList);
        return topWordsHashSet;
    }

    public void getOutTheSpecialCharsFromTheText(ArrayList<String> stringArrayList) {
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

    // Gyűjtsd ki egy szöveg 10 leggyakoribb 2-3-4 szavas szófordulatát!


    public HashSet<String> top10PopularPhrases(ArrayList<String> abc, int number) {
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
        HashSet<String> top10Phrases = new HashSet<>(top10WordsList);
        return top10Phrases;
    }

    //Gyűjtsd ki egy szöveg 10 leggyakoribb nevét!

    public HashSet<String> findAllNames(ArrayList<String> stringArrayList) throws FileNotFoundException {
        HashSet<String> namesHashSet = new HashSet<>();
        ArrayList<String> names = new ArrayList<>(readNamesFromFile());
        for (int i = 0; i < stringArrayList.size(); i++) {
            for (int j = 0; j < names.size(); j++) {
                if (stringArrayList.get(i).equals(names.get(j))) {
                    namesHashSet.add(stringArrayList.get(i).toUpperCase());
                    break;
                }
            }
        }
        return namesHashSet;
    }

    public ArrayList<String> readNamesFromFile() throws FileNotFoundException{
        Scanner fromFile = new Scanner(new File("Files/Names.txt"));
        ArrayList<String> names = new ArrayList<>();
        while (fromFile.hasNextLine()) {
            names.add(fromFile.nextLine().trim().toLowerCase());
        }
        return names;
    }

    public int countSentences(String text) {
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

    public float countAvgWordsInSentences(ArrayList<String> abc, String text) {
        int wordsNum = abc.size();
        int sentencesNum = countSentences(text);
        return (sentencesNum > 0 ? (float)wordsNum / sentencesNum : abc.size());
    }

    public int countLetters(String text, String keyWord) {
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

    public float rateOfWantedLetters(String text, String keyword) {
        int letters = countLetters(text, keyword);
        return (float)letters / text.length() * 100f;
    }

    public void printInfo (){
        System.out.println("A mondatok száma a szövegben: " + countSentences(text));
        System.out.println("A betűk száma a szövegben (speciális karakterek nélkül): " + countLetters(text,""));
        System.out.println("Átlagos szavak száma egy mondatban: " + countAvgWordsInSentences(textInArray,text));
        System.out.println("10 leggyakoribb szó a szövegben:  " + findThe10MostPopularWord(textInArray,10));
        System.out.println("10 leggyakoribb 3 szóból álló szóösszetétel a szövegben: " + top10PopularPhrases(textInArray,3));
        System.out.println("Mássalhangzók aránya a szövegben: " + rateOfWantedLetters(text, "consonants"));
        System.out.println("Magánhangzók a szövegben: " + rateOfWantedLetters(text, "vowels"));
    }

}

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class BullShitGenerator extends Semantics {

    private Semantics semantics;
    private String generatedText = "";


    public BullShitGenerator(String filePath) throws FileNotFoundException {
        semantics = new Semantics(filePath);
    }


    public BullShitGenerator() {
        super();
    }

    public void setGeneratedTextFromWords(){
       HashSet<String> abc = findThe10MostPopularWord(semantics.getTextInArray(),20);
       ArrayList<String> stringArrayList = new ArrayList<>(abc);

        for (int i = 0; i < 5; i++) {
            generatedText += stringArrayList.get((int)(Math.random()*abc.size())) + " ";
         }
    }

    public String getGeneratedText() {
        return generatedText;
    }

    public void setGeneratedTextFromPhrases(){
        generatedText = "";
        HashSet<String> abc = top10PopularPhrases(semantics.getTextInArray(),2);
        ArrayList<String> stringArrayList = new ArrayList<>(abc);

        for (int i = 0; i < 5; i++) {
            generatedText += stringArrayList.get((int)(Math.random()*abc.size())) + " ";
        }
    }
    @Override
    public void printInfo (){
        System.out.println("Generáljon egy random mondatot  a szöveg leggyakoribb szavaziból:");
        setGeneratedTextFromWords();
        System.out.println(generatedText);
        System.out.println("Generáljon a leggyakoribb szóösszetételekből egy mondaatot:" );
        setGeneratedTextFromPhrases();
        System.out.println(generatedText);
    }


}

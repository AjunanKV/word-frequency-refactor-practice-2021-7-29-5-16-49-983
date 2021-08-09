import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {
    private static final String BLANKSPACE = "\\s+";

    public String getResult(String sentence){


        if (sentence.split(BLANKSPACE).length==1) {
            return sentence + " 1";
        } else {

            try {

                List<WordInfo> inputList = calculateWordFrequencyTemporary(sentence);

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : inputList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> calculateWordFrequencyTemporary(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANKSPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();

        distinctWords.forEach(distinctWord -> {
            int count = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            WordInfo wordInfo = new WordInfo(distinctWord,count);
            wordInfos.add(wordInfo);
        });

        return wordInfos;
    }

}

import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {
    private static final String BLANKSPACE = "\\s+";
    private static final CharSequence ENDLINE = "\n";
    private static final String SPACE =" ";

    public String getResult(String sentence) {


        if (sentence.split(BLANKSPACE).length == 1) {
            return sentence + " 1";
        }

        try {

            List<WordInfo> wordInfoList = calculateWordFrequencyTemporary(sentence);
            sortByCount(wordInfoList);
            return joinString(wordInfoList);

        } catch (Exception e) {


            return "Calculate Error";
        }
    }

    private String joinString(List<WordInfo> wordInfoList) {
        return wordInfoList.stream().map(wordInput -> wordInput.getValue() +SPACE+ wordInput.getWordCount()).collect(Collectors.joining(ENDLINE));
    }

    private void sortByCount(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private List<WordInfo> calculateWordFrequencyTemporary(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANKSPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();

        distinctWords.forEach(distinctWord -> {
            int count = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            WordInfo wordInfo = new WordInfo(distinctWord, count);
            wordInfos.add(wordInfo);
        });

        return wordInfos;
    }

}

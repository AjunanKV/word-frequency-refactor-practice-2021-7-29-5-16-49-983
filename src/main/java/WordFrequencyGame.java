import java.lang.reflect.Array;
import java.util.*;


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
        //split the input string with 1 to n pieces of spaces
        List<String> words = Arrays.asList(sentence.split(BLANKSPACE));
        
        List<WordInfo> inputList = new ArrayList<>();
        for (String word : words) {
            WordInfo input = new WordInfo(word, 1);
            inputList.add(input);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> map =getListMap(inputList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
            WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        inputList = list;
        return inputList;
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}

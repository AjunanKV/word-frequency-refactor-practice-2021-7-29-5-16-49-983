public class WordInfo {
    private String value;
    private int wordCount;

    public WordInfo(String word, int Count) {
        this.value = word;
        this.wordCount = Count;
    }


    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.wordCount;
    }


}

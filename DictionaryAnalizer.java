import java.util.ArrayList;

/**
 * Created by marya on 14.7.17.
 */
public class DictionaryAnalizer {
    private String [] dictionary;
    private Graph graph;

    public DictionaryAnalizer (String [] dictionary){
        this.dictionary = dictionary;
        graph = new Graph();
    }
    public ArrayList <Character> getAlphabet(){
        if (dictionary == null || dictionary.length == 0)
            return null;
        String word = dictionary[0];
        for (int i = 0; i < word.length(); i++){
            graph.addLetter(word.charAt(i));
        }
        if (dictionary.length == 1){
            return graph.topologicalSortResult();
        }
        for (int i = 1; i < dictionary.length; i++){
            String previousWord = dictionary[i-1];
            String currentWord = dictionary[i];
            if (previousWord.length() != currentWord.length()){
                for (int j = 0; j < currentWord.length(); j++){
                    graph.addLetter(currentWord.charAt(j));
                }
            }
            else{
                int k=0;
                while (k < previousWord.length() && previousWord.charAt(k) == currentWord.charAt(k)){
                    k++;
                }
                if (k < currentWord.length()){
                    graph.addRelations(currentWord.charAt(k), previousWord.charAt(k));
                    k++;
                }
                while (k < currentWord.length()){
                    graph.addLetter(currentWord.charAt(k));
                    k++;
                }
            }
        }
        return graph.topologicalSortResult();
    }
}

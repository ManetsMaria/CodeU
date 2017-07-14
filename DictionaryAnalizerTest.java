import java.util.ArrayList;

/**
 * Created by marya on 14.7.17.
 */
public class DictionaryAnalizerTest {
    private final static String right = "Ok";
    private final static String wrong = "not Ok";

    private static DictionaryAnalizer dictionaryAnalizer;

    private static <T> String convertTestResultToString(T result, T expectedResult){
        if (result == null){
            if (expectedResult == null){
                return right;
            }
            else
                return wrong;
        }
        if (result.equals(expectedResult))
            return right;
        return wrong;
    }

    private static boolean test1(){
        String [] dictionary = {"PAPRICA", "RAPRICA", "RATRICA", "RARRICA", "AA", "ACA"};
        dictionaryAnalizer = new DictionaryAnalizer(dictionary);
        ArrayList <Character> result = dictionaryAnalizer.getAlphabet();
       // System.out.print(dictionaryAnalizer.getAlphabet());
        if (result == null || result.size() != 6)
            return false;
        if (!(result.contains('A') && result.contains('P') && result.contains('R') && result.contains('I') && result.contains('C') && result.contains('T')))
            return false;
        int indexA = result.indexOf('A');
        int indexP = result.indexOf('P');
        int indexR = result.indexOf('R');
        int indexT = result.indexOf('T');
        int indexC = result.indexOf('C');
        if (indexA < indexC && indexR < indexA && indexP < indexT && indexP < indexR && indexT < indexR)
            return true;
        return false;
    }
    private static boolean test2() {
        String [] dictionary = {"PAPRICA", "RAPRICA", "RATRICA", "RARRICA", "AA", "AP"};
        dictionaryAnalizer = new DictionaryAnalizer(dictionary);
        ArrayList <Character> result = dictionaryAnalizer.getAlphabet();
        if (result == null)
            return true;
        return false;
    }
    private static boolean test3() {
        String [] dictionary = {"ART", "RAT", "CAT", "CAR"};
        dictionaryAnalizer = new DictionaryAnalizer(dictionary);
        ArrayList <Character> result = dictionaryAnalizer.getAlphabet();
        if (result == null || result.size() != 4)
            return false;
        int indexA = result.indexOf('A');
        int indexC = result.indexOf('C');
        int indexR = result.indexOf('R');
        int indexT = result.indexOf('T');
        if (indexA < indexR && indexR < indexC && indexT < indexR)
            return true;
        return false;
    }
    private static  void test(){
        System.out.println (convertTestResultToString (test1(), true));
        System.out.println (convertTestResultToString (test2(), true));
        System.out.println (convertTestResultToString (test3(), true));
    }
    public static void main (String[] args){
        test();
    }
}

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by dane on 09/02/17.
 */
public class ReadHTMLText {

    private ReadHTMLText() {

        String formattedText = "";
        formattedText = readHTMLFromURL(formattedText);

//        printUniqueWordsSorted(formattedText);
//        printAllWordsUnsorted(formattedText);
//        totalNumberOfWords(formattedText);
//        printAllWordsTallied(formattedText);
//        printUniqueWordsUnsorted(formattedText);
//        printTenLastWords(formattedText);
//        printAllUniqueWords(formattedText);
        printWordsThatAppearMoreThanTenTimes(formattedText);
        topFiveMostCommonWords(formattedText);
    }

    //Get HTML from URL, and place Inputstream into String to be returned.
    private String readHTMLFromURL(String formattedText){
        try {
            URL url = new URL("http://self.gutenberg.org/articles/eng/The_Owl_and_the_Pussycat");
            Scanner scannedStremFromURL = new Scanner(url.openStream());

            while(scannedStremFromURL.hasNext()) {
                String htmlText = scannedStremFromURL.nextLine();
                if(htmlText.contains("<i>")) {
                    if(htmlText.length() < 200) {
                        if(!htmlText.contains("Free audio of the Owl")) {
                            htmlText = htmlText.replaceAll("<i>|,|\\.|!|;|\\?|\"|:", "").trim();
                            formattedText += htmlText.replaceAll("</i>", " ").toLowerCase();
                        }
                    }
                }
            }

        } catch( IOException ex) {
            ex.printStackTrace();
        }
        return formattedText;
    }

    //Treeset
    private void printUniqueWordsSorted(String words){
        Set<String> treeSet = new TreeSet<>();
        Collections.addAll(treeSet,words.split(" "));
        System.out.println("Words printed individually:");
        treeSet.forEach(System.out::println);
    }

    //Hashset
    private void printUniqueWordsUnsorted(String words){
        Set<String> set = new HashSet<>();
        Collections.addAll(set,words.split(" "));
        set.forEach(System.out::println);
    }

    //Arraylist
    private void printAllWordsUnsorted(String words){
        List<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,words.split(" "));
        System.out.println("Words printed individually:");
        arrayList.forEach(System.out::println);
    }

    //Arraylist
    private void totalNumberOfWords(String words){
        List<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, words.split(" "));
        System.out.println("ArrayList = "+arrayList.size());
        //Collections.sort(arrayList);
    }

    //Hashmap
    private void printAllWordsTallied(String words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words.split(" ")) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        map.forEach((key, value) -> System.out.println("Word:" + key + "    Value:" + value));
    }

    //Hashmap with Entryset
    private void printAllUniqueWords(String words){
        Map<String,Integer> map = new HashMap<>();
        for(String word:words.split(" ")){
            if(!map.containsKey(word)){
                map.put(word,1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        for(Map.Entry<String,Integer> occurrences:map.entrySet()){
            if(!(occurrences.getValue()>1)){
                System.out.println(occurrences.getKey() +" "+ occurrences.getValue());
            }
        }
    }

    //Hashmap with Entryset
    private void printWordsThatAppearMoreThanTenTimes(String words){
        Map<String,Integer> map = new HashMap<>();
        for(String word:words.split(" ")){
            if(!map.containsKey(word)){
                map.put(word,1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        for(Map.Entry<String,Integer> occurrences:map.entrySet()){
            if(occurrences.getValue()>8){
                System.out.println(occurrences.getKey() +" "+ occurrences.getValue());
            }
        }
    }

    //Hashmap using stream
    private void topFiveMostCommonWords(String words){

        Map<String,Integer> map = new HashMap<>();
        for(String word:words.split(" ")){
            if(!map.containsKey(word)){
                map.put(word,1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }
        map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(5).forEach(System.out::println);
//        List<Map.Entry<String,Integer>> list = new LinkedList<>(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> test1, Map.Entry<String, Integer> test2) {
//                return (test2.getValue()).compareTo(test1.getValue());
//            }
//        });
//
//        Map<String,Integer> result = new LinkedHashMap<>();
//        for(Map.Entry<String,Integer> entry:list) {
//            result.put(entry.getKey(), entry.getValue());
//        }
//
//        result.forEach((key, value) -> System.out.println("Word:" + key + "    Value:" + value));
//        for(Map.Entry<String,Integer> occurrences:map.entrySet()){
//            if(!(occurrences.getValue()>1)){
//                System.out.println(occurrences.getKey() +" "+ occurrences.getValue());
//            }
//        }
    }

    //Deque and Linkedlist
    private void printTenLastWords(String words){
        Deque<String> queueTest = new LinkedList<>();
        Collections.addAll(queueTest,words.split(" "));

        for(int i = 0;i<10;i++){
            System.out.println(queueTest.getLast());
            queueTest.removeLast();
        }
    }

    /**
     * Main method to access the ReadHTMLText class.
     * @param args Takes in the users input from command line.
     */
    public static void main(String[] args){
        new ReadHTMLText();
    }
}

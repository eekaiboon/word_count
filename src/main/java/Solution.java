import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * getTopNWords() method returns top N words from content. Here, we use a
 * simple content tokenizer to separate words of the content (please refer to
 * SimpleContentTokenizer class).
 * 
 * I apply the pigeon hole sort algorithm to sort words by their frequencies.
 * Here are the details:
 * (1) Tokenize content into words. Here, we use SimpleContentTokenizer. 
 *     (Time complexity is O(n) where n is number of characters of content)
 * (2) Iterate through all words and count their frequencies. This piece of
 *     information is stored in a map of word to frequency. We also will know
 *     the highest frequency from this iteration.
 * (3) Create i number of buckets where i is value of the highest frequency.
 *     For example, if the highest frequency is 10, we will create 10 buckets.
 *     The j-th bucket corresponds to words with (j + 1) frequency.
 *     The buckets are the pigeon holes.
 * (4) Iterate through all entries in the map of word to frequency and add word
 *     to its corresponding bucket based on its frequency.
 * (5) Iterate from the top bucket to the bottom bucket and returns the top N
 *     words.
 *     
 * The time complexity of pigeon hole sort algorithm is O(w + b) where w is
 * the number of distinct words in content and b is the number of buckets.
 * Since w + b < n where n is the number of character of content, the overall
 * time complexity is bounded by the runtime of content tokenizer which is O(n).
 * 
 * @author kaiboonee
 */
public class Solution {
    
    // We use a simple content tokenizer. It could be replaced by any more 
    // sophisticated tokenizer that implements ContentTokenizer interface.
    private static ContentTokenizer contentTokenizer = 
            new SimpleContentTokenizer();
    
    public static List<String> getTopNWords(String content, int n) {
        
        if (content == null || n < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        Map<String, Integer> wordToFrequency = new HashMap<>();
        
        List<String> tokens = contentTokenizer.tokenize(content);
        
        int highestFrequency = populateWordToFrequency(wordToFrequency, tokens);
        
        List<Set<String>> buckets = groupWordsByFrequency(wordToFrequency,
                highestFrequency);
        
        List<String> result = generateResult(n, buckets);
        
        return result;
    }

    /**
     * Return top n words from buckets. Nth bucket has words with frequency of
     * (n + 1). For example, the 0th bucket has words with frequency of 1.
     * 
     * @param n
     * @param buckets
     * @return top n words from buckets
     */
    private static List<String> generateResult(int n, List<Set<String>> buckets) {
        List<String> result = new ArrayList<>(n);
        
        for (int i = buckets.size() - 1; i >= 0; i--) {
            for (String word : buckets.get(i)) {
                if (result.size() < n) {
                    result.add(word);
                } else {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * Group words by their frequencies. Nth frequency will be added to
     * (n - 1)th bucket. For example, word with frequency of 1 will be added to
     * the 0th bucket.
     * 
     * @param wordToFrequency
     * @param numOfBuckets
     * @return list of words grouped by their frequency in ascending order
     */
    private static List<Set<String>> groupWordsByFrequency(
            Map<String, Integer> wordToFrequency, int numOfBuckets) {
        
        List<Set<String>> buckets = new ArrayList<>(numOfBuckets);
        
        for (int i = 0; i < numOfBuckets; i++) {
            Set<String> empty = new HashSet<>();
            buckets.add(empty);
        }
        
        for (Entry<String, Integer> entry : wordToFrequency.entrySet()) {
            String word = entry.getKey();
            Integer frequency = entry.getValue();
            
            buckets.get(frequency - 1).add(word);
        }
        
        return buckets;
    }

    /**
     * Count frequency of word and populate word to frequency map 
     * 
     * @param wordToFrequency
     * @param words
     * @return the highest frequency
     */
    private static int populateWordToFrequency(
            Map<String, Integer> wordToFrequency, List<String> words) {
        
        int highestFrequency = 0;
        
        for (String word : words) {
            if (wordToFrequency.containsKey(word)) {
                wordToFrequency.put(word, wordToFrequency.get(word) + 1);
            } else {
                wordToFrequency.put(word, 1);
            }
            
            if (wordToFrequency.get(word) > highestFrequency) {
                highestFrequency = wordToFrequency.get(word);
            }
        }
        
        return highestFrequency;
    }
}

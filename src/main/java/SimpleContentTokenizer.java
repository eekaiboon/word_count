import java.util.ArrayList;
import java.util.List;

/**
 * A simple content tokenizer which can be used for tokenizing a string.
 * 
 * @author kaiboonee
 *
 */
public final class SimpleContentTokenizer implements ContentTokenizer {
    
    /**
     * Tokenize a string based on the following rules:
     * (1) Tokens are made up of upper or lower case characters
     * 
     * Some examples are as followed:
     * (1) "This is AWESOME." -> ["This", "is", "AWESOME"]
     * (2) "F.D.R" -> ["F", "D", "R"]
     * (3) "Father's car" -> ["Father", "s", "car"]
     * 
     * This method has O(n) time complexity where n is the number of characters
     * of content string.
     * 
     * @param content
     * @return
     */
    public List<String> tokenize(String content) {
        
        if (content == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        List<String> tokens = new ArrayList<>();
        
        char[] charArr = content.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        for (char c : charArr) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    tokens.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        
        return tokens;
    }
}

package volkan.anagram.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class AnagramService {

  public boolean isValidArgument(String input) {
    return Objects.nonNull(input) && input.matches("^[a-zA-Z0-9]*$");
  }

  public List<String> anagramsOf(String s) {
    return new ArrayList<>(permutation(s));
  }

  public boolean areAnagrams(String string1, String string2) {
    string1 = this.cleanWhiteSpaces(string1).toLowerCase();
    string2 = this.cleanWhiteSpaces(string2).toLowerCase();
    int length1 = string1.length();
    StringBuilder sb2 = new StringBuilder(string2);
    int length2 = sb2.length();
    if (length1 == length2) {
      for (int i = 0; i < length1; i++) {
        char c = string1.charAt(i);
        boolean charFound = false;
        for (int y = 0; y < length2; y++) {
          if (c == sb2.charAt(y)) {
            sb2.deleteCharAt(y);
            length2--;
            charFound = true;
            break;
          }
        }
        if (!charFound) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  private Set<String> permutation(String s) {
    Set<String> result = new HashSet<>();
    if (s.length() == 0) {
      result.add("");
      return result;
    }

    char firstChar = s.charAt(0);
    String remaining = s.substring(1);
    Set<String> words = permutation(remaining);
    for (String word : words) {
      for (int i = 0; i <= word.length(); i++) {
        result.add(addChar(word, firstChar, i));
      }
    }
    return result;
  }

  private String addChar(String str, char c, int j) {
    String first = str.substring(0, j);
    String last = str.substring(j);
    return first + c + last;
  }

  private String cleanWhiteSpaces(String s) {
    return s.replaceAll("\\s", "");
  }
}

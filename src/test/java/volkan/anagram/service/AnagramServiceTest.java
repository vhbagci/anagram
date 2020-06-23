package volkan.anagram.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import volkan.anagram.UnitTest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Anagram Service Tests")
class AnagramServiceTest extends UnitTest {

  @Autowired
  AnagramService anagramService;

  @ParameterizedTest(name = "Run {index}: arg1={0}, arg2={1}")
  @MethodSource("testParametersForAreAnagrams")
  void areAnagrams(String arg1, String arg2) {
    assertTrue(this.anagramService.areAnagrams(arg1, arg2));
  }

  static Stream<Arguments> testParametersForAreAnagrams() {
    return Stream.of(
        Arguments.of("cinema", "iceman"),
        Arguments.of("abc", "cab"),
        Arguments.of("Listen", "Silent"),
        Arguments.of("Astronomer", "Moon starer"),
        Arguments.of("School master", "The classroom"),
        Arguments.of("A gentleman", "Elegant man"),
        Arguments.of("Conversation", "Voices rant on")
    );
  }

  @ParameterizedTest(name = "Run {index}: arg1={0}, listExpected={1}")
  @MethodSource("testParametersForAnagramsOf")
  void anagramsOf(String arg1, String[] listExpected) {
    List<String> list = this.anagramService.anagramsOf(arg1);
    list.sort(Comparator.comparing(s -> s));
    assertThat(list.toArray())
        .isEqualTo(listExpected);
  }

  static Stream<Arguments> testParametersForAnagramsOf() {
    return Stream.of(
        Arguments.of("a", new String[]{"a"}),
        Arguments.of("ab", new String[]{"ab", "ba"}),
        Arguments.of("abc", new String[]{"abc", "acb", "bac", "bca", "cab", "cba"})
    );
  }
}
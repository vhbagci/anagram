package volkan.anagram.web;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import volkan.anagram.IntegrationTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class AnagramControllerTest extends IntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void areAnagrams() throws Exception {
    this.mvc.perform(get("/anagrams/iceman/cinema")
        .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.areAnagrams", is(true)))
        .andReturn();
  }

  @Test
  void areNotAnagrams() throws Exception {
    this.mvc.perform(get("/anagrams/icemano/tcinema")
        .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.areAnagrams", is(false)))
        .andReturn();
  }

  @ParameterizedTest(name = "Run {index}: path={0}")
  @MethodSource("testParametersForAreAnagramsInvalidInputs")
  void areAnagramsInvalidInputs(String path) throws Exception {
    this.mvc.perform(get(path)
        .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  static Stream<Arguments> testParametersForAreAnagramsInvalidInputs() {
    return Stream.of(
        Arguments.of("/anagrams/iceman^/cinema"),
        Arguments.of("/anagrams/iceman&/cinema&"),
        Arguments.of("/anagrams/iceman/cinema&"),
        Arguments.of("/anagrams/123$/%^23")
    );
  }

  @Test
  void anagramsOf() throws Exception {
    this.mvc.perform(get("/anagrams/ba")
        .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.anagrams", hasItem("ba")))
        .andExpect(jsonPath("$.anagrams", hasItem("ab")))
        .andReturn();
  }

  @ParameterizedTest(name = "Run {index}: path={0}")
  @MethodSource("testParametersForAnagramsOfInvalidInput")
  void anagramsOfInvalidInput(String path) throws Exception {
    this.mvc.perform(get(path)
        .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andReturn();
  }

  static Stream<Arguments> testParametersForAnagramsOfInvalidInput() {
    return Stream.of(
        Arguments.of("/anagrams/iceman^"),
        Arguments.of("/anagrams/123$")
    );
  }
}
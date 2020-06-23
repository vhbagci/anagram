package volkan.anagram.web;

import volkan.anagram.domain.valueobject.AreAnagramsResponse;
import volkan.anagram.domain.valueobject.AnagramsResponse;
import volkan.anagram.service.AnagramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/anagrams")
@AllArgsConstructor
public class AnagramController {

  private final AnagramService anagramService;

  @GetMapping("/{string1}/{string2}")
  AreAnagramsResponse areAnagrams(@PathVariable("string1") String string1,
      @PathVariable("string2") String string2) {
    validateInputs(string1, string2);
    return AreAnagramsResponse.builder()
        .areAnagrams(this.anagramService.areAnagrams(string1, string2)).build();
  }

  @GetMapping("/{string1}")
  AnagramsResponse anagramsOf(@PathVariable("string1") String string1) {
    validateInputs(string1);
    return AnagramsResponse.builder()
        .anagrams(this.anagramService.anagramsOf(string1)).build();
  }

  private void validateInputs(String... inputs) {
    boolean valid = true;
    for (String input : inputs) {
      valid = valid && this.anagramService.isValidArgument(input);
    }
    if (!valid) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Inputs Provided");
    }
  }
}

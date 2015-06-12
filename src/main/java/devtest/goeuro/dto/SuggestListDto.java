package devtest.goeuro.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */
public class SuggestListDto {
  private List<SuggestDto> suggestions;

  public void setSuggestions(List<SuggestDto> suggestions) {
    this.suggestions = suggestions;
  }

  public List<SuggestDto> getSuggestions() {
    return suggestions;
  }
}

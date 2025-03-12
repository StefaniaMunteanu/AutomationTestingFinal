package demoqa.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
@Getter
public class GetAllBooksSuccess {
    @JsonProperty("books")
    private List<Book> books;
}

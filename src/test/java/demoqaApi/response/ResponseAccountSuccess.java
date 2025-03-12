package demoqaApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ResponseAccountSuccess {
    @JsonProperty("userID")
    private String userID;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private Book[] books;


}

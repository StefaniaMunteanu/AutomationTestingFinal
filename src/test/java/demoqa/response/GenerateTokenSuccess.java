package demoqa.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GenerateTokenSuccess {
    @JsonProperty("token")
    private String token;
    @JsonProperty("expires")
    private String expires;
    @JsonProperty("status")
    private String status;
    @JsonProperty("result")
    private String result;

}

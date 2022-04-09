package serialization.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class FilteredSecurityMessageResponse {

    private List<ForumMessage> security;

    @JsonProperty("Security")
    public List<ForumMessage> getSecurity() {
        return security;
    }

    public void setSecurity(List<ForumMessage> security) {
        this.security = security;
    }

}

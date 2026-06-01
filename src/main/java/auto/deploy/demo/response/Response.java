package auto.deploy.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String status;
    private String message;

    public Response() {
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }
}

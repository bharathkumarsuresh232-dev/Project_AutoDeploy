package auto.deploy.demo.controller;

import auto.deploy.demo.response.Response;
import auto.deploy.demo.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeployController {

    @Autowired
    private DeploymentService deploymentService;

    @PostMapping("/deploy")
    public Response deploy(@RequestBody Map<String, Object> payload) {
        try {
            // Extract branch
            String ref = (String) payload.get("ref"); // e.g. "refs/heads/beta"
            String branch = ref.replace("refs/heads/", "");

            // Extract user
            Map<String, Object> pusher = (Map<String, Object>) payload.get("pusher");
            String user = (String) pusher.get("name");

            // Extract commit info
            Map<String, Object> headCommit = (Map<String, Object>) payload.get("head_commit");
            String timestamp = (String) headCommit.get("timestamp");

            // Call deployment
            String result = deploymentService.deploy(branch, user, timestamp);
            return new Response("success", result);

        } catch (Exception e) {
            return new Response("error", "Error parsing payload: " + e.getMessage());
        }
    }
}

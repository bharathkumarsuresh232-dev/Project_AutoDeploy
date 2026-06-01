package auto.deploy.demo.service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.util.Date;

@Service
public class DeploymentService {

    public String deploy(String branch, String user, String timestamp) {
        try {
            ProcessBuilder processBuilder =
                new ProcessBuilder("/bin/bash", "/opt/sterling/scripts/deploy.sh", branch);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // Log deployment details
            String logEntry = "Branch: " + branch +
                              " | User: " + user +
                              " | Time: " + timestamp +
                              " | Logged at: " + new Date() + "\n";
            Files.write(Paths.get("/opt/sterling/logs/deploy.log"),
                logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            if (exitCode == 0) {
                return "Deployment Successful";
            }
            return "Deployment Failed";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

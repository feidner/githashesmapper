package mapper;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = { SecurityConfig.class })
public class GitLabHashMappingApplication {

    public static void main(String[] args) {
        LogManager.getLogger(GitLabHashMappingApplication.class.getSimpleName()).info("START APPLICATION");
        SpringApplication.run(GitLabHashMappingApplication.class);
    }
}

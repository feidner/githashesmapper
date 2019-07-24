package mapper;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = { SecurityConfig.class })
public class GitLabHashMappingApplication {

    public static void main(String[] args) {
        LogFactory.getLog(GitLabHashMappingApplication.class).info("START APPLICATION");
        SpringApplication.run(GitLabHashMappingApplication.class);
    }
}

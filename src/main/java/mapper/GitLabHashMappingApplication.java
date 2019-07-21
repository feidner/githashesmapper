package mapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = { SecurityConfig.class })
public class GitLabHashMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitLabHashMappingApplication.class);
    }
}

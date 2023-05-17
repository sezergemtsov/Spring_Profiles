package sezergemtsov.profiles.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("spring.datasource")
@Component
@Getter
@Setter
public class H2Config {

    private String url;
    private String driverClassName;
    private String username;
    private String password;

}

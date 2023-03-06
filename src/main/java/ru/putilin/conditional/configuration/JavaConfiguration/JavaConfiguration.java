package ru.putilin.conditional.configuration.JavaConfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.putilin.conditional.DevProfile;
import ru.putilin.conditional.ProductionProfile;
import ru.putilin.conditional.SystemProfile;


@Configuration
public class JavaConfiguration {
    @ConditionalOnProperty(prefix = "netology.profile", name= "dev", havingValue = "true")
    @Bean(name = "devProfile")
    public SystemProfile devProfile(){
        return new DevProfile();
    }

    @ConditionalOnProperty(prefix = "netology.profile", name="dev", havingValue = "false")
    @Bean(name = "productionProfile")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}

package dev.gagnon.Benue_Produce_Logistics_Api;

import dev.gagnon.Benue_Produce_Logistics_Api.security.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class Benue_Produce_Logistics_ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Benue_Produce_Logistics_ApiApplication.class, args);
    }

}

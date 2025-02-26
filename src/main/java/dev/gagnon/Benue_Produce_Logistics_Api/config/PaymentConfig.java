package dev.gagnon.Benue_Produce_Logistics_Api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PaymentConfig {
    @Value("${credo.api.base.url}")
    private String apiBaseUrl;
    @Value("${credo.api.secret.key}")
    private String secretKey;
    @Value("${credo.api.public.key}")
    private String publicKey;
}

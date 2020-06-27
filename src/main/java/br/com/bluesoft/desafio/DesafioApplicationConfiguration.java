package br.com.bluesoft.desafio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DesafioApplicationConfiguration {

    @Bean
    public RestTemplate restTemplate() {
	return new RestTemplate();
    }

}

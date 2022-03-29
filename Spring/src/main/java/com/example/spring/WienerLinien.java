package com.example.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class WienerLinien {

    private String output;

    public String output(){
        return this.output;
    }

    private static final Logger log = LoggerFactory.getLogger(WienerLinien.class);

    public static void main(String[] args){
        SpringApplication.run(WienerLinien.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Object object = restTemplate.getForObject(
                    "https://www.wienerlinien.at/ogd_realtime/monitor?rbl=147", Object.class);
            log.info(object.toString());
            output = object.toString();

        };
    }

    @GetMapping("/wienerlinien")
    public String wl(){
        return output;
    }
}

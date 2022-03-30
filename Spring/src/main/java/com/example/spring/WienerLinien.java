package com.example.spring;

import com.fasterxml.jackson.core.JsonParser;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
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
            String json = restTemplate.getForObject(
                    "https://www.wienerlinien.at/ogd_realtime/monitor?rbl=710", String.class);
            log.info(json);
            output = json;

            String jsonPathExpression = "$.data.monitors[0].locationStop.properties.title";
            String title = JsonPath.parse(json).read(jsonPathExpression, String.class); //filter Json for needed data

            String[] depTimes = new String[3];

            for(int i = 0; i < 3; i++){
                String jsonPathExpression2 = "$.data.monitors[0].lines[0].departures.departure["+i+"].departureTime.countdown"; //getting the nearest 3 departure.countdowns
                depTimes[i] = JsonPath.parse(json).read(jsonPathExpression2, String.class);
            }

            output = title + "   --->  Departure in " + depTimes[0] + ", " + depTimes[1] + ", " + depTimes[2];






        };
    }


    @GetMapping("/wienerlinien")
    public String wl(){
        return output;
    }
}

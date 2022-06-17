package com.Project_2_Location_Status_API;

import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import com.Project_2_Location_Status_API.Services.CovidApiService;
import com.Project_2_Location_Status_API.Services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class LocationStatusApiApplication {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    StatusService statusService;




    public static void main(String[] args) {
        SpringApplication.run(LocationStatusApiApplication.class, args);
    }

//----This is what August showed in class to make the rest template more usable ,but it didn't work for me
//    @Bean
//    public RestTemplate covidApiRestTemplate(RestTemplateBuilder builder){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//
//        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
//        return builder.build();
//    }

}
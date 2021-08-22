package com.polygor.contactlist;

import com.polygor.contactlist.service.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.polygor.contactlist.repository")
@EntityScan(basePackages = "com.polygor.contactlist.entity")
@RequiredArgsConstructor
public class ContactListApplication {

    private final CsvService csvService;

    public static void main(String[] args) {
        SpringApplication.run(ContactListApplication.class, args);
    }

    @PostConstruct
    private void init() {
        InputStream inputStream = ContactListApplication.class.getResourceAsStream("/data/people.csv");
        csvService.convertPeopleCsvFileToDatabaseEntity(inputStream);
    }
}

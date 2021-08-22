package com.polygor.contactlist.service.util;

import com.polygor.contactlist.entity.PeopleEntity;
import com.polygor.contactlist.exception.CsvParsingException;
import com.polygor.contactlist.repository.PeopleRepository;
import com.polygor.contactlist.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Service
@RequiredArgsConstructor
public class CsvUtil {

    private final PeopleRepository peopleRepository;

    public void csvToPeople(InputStream is) {
        if (is != null) {
            peopleRepository.deleteAll();
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                 CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
                List<PeopleEntity> peopleList = new ArrayList<>();
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    convertCsvRecordToEntity(peopleList, csvRecord);
                }
                peopleRepository.saveAll(peopleList);

            } catch (IOException e) {
                throw new CsvParsingException("fail to parse and save CSV file: " + e.getMessage());
            }
        }
    }

    private void convertCsvRecordToEntity(List<PeopleEntity> peopleList, CSVRecord csvRecord) {
        PeopleEntity people = new PeopleEntity();
        people.setName(csvRecord.get("name"));
        people.setImageUrl(csvRecord.get("url"));
        peopleList.add(people);
    }
}

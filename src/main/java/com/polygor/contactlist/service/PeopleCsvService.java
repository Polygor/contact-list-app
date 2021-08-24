package com.polygor.contactlist.service;

import com.polygor.contactlist.entity.PeopleEntity;
import com.polygor.contactlist.exception.CsvParsingException;
import com.polygor.contactlist.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
public class PeopleCsvService {

    private final PeopleRepository peopleRepository;

    public void convertPeopleCsvFileToDatabaseEntity(InputStream is) {
        if (is != null) {
            try (BufferedReader fileReader = new BufferedReader
                    (new InputStreamReader(is, StandardCharsets.UTF_8));

                 CSVParser csvParser = new CSVParser
                         (fileReader, CSVFormat.DEFAULT
                         .withFirstRecordAsHeader()
                         .withIgnoreHeaderCase()
                         .withTrim()))
            {
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();
                List<PeopleEntity> peopleList = createPeopleListFromCsvRecords(csvRecords);
                peopleRepository.saveAll(peopleList);

            } catch (IOException e) {
                throw new CsvParsingException("fail to parse and save CSV file: " + e.getMessage());
            }
        }
    }

    private List<PeopleEntity> createPeopleListFromCsvRecords(Iterable<CSVRecord> csvRecords) {
        List<PeopleEntity> peopleList = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {
            PeopleEntity peopleEntity = convertCsvRecordToEntity(csvRecord);
            peopleList.add(peopleEntity);
        }
        return peopleList;
    }

    private PeopleEntity convertCsvRecordToEntity(CSVRecord csvRecord) {
        PeopleEntity people = new PeopleEntity();
        people.setName(csvRecord.get("name"));
        people.setImageUrl(csvRecord.get("url"));
        return people;
    }
}

package com.polygor.contactlist.util;

import com.polygor.contactlist.entity.PeopleEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;

public class CsvUtil {
//
//    public static final String CHARSET_NAME = "UTF-8";
//    public static String TYPE = "text/csv";
//    static String[] HEADERs = { "name", "url"};
//
//    public static boolean hasCSVFormat(MultipartFile file) {
//        return TYPE.equals(file.getContentType());
//    }
//
//
//    public static List<PeopleEntity> csvToPeople(InputStream is) {
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, CHARSET_NAME));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//
//            List<PeopleEntity> peopleList = new ArrayList<>();
//
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//            for (CSVRecord csvRecord : csvRecords) {
//                PeopleEntity people = new PeopleEntity(
//                        csvRecord.get("name"),
//                        csvRecord.get("url")
//                );
//
//                peopleList.add(people);
//            }
//
//            return peopleList;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//    }

}

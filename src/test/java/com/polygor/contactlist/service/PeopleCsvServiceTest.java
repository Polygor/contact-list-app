package com.polygor.contactlist.service;

import com.polygor.contactlist.entity.PeopleEntity;
import com.polygor.contactlist.exception.CsvParsingException;
import com.polygor.contactlist.repository.PeopleRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class PeopleCsvServiceTest {

    private static final int EXPECTED_ENTITY_LIST_SIZE = 817;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleCsvService peopleCsvService;

    @Captor
    private ArgumentCaptor<ArrayList<PeopleEntity>> captor;


    @Test
    public void shouldConvertCsvFileToList() throws FileNotFoundException {
        //GIVEN
        File initialFile = new File("src/test/resources/data/people.csv");
        InputStream inputStream = new FileInputStream(initialFile);
        //WHEN
        peopleCsvService.convertPeopleCsvFileToDatabaseEntity(inputStream);
        //THEN
        verify(peopleRepository).saveAll(captor.capture());
        List<ArrayList<PeopleEntity>> peopleEntityList = captor.getAllValues();
        ArrayList<PeopleEntity> extractedPeopleList = peopleEntityList.get(0);
        assertEquals(EXPECTED_ENTITY_LIST_SIZE, extractedPeopleList.size());
        PeopleEntity extractedFirstElementOfList = extractedPeopleList.get(0);
        assertNull(extractedFirstElementOfList.getId());
        String extractedFirstEntityName = extractedFirstElementOfList.getName();
        assertTrue(StringUtils.isNotBlank(extractedFirstEntityName));
        String extractedFirstEntityUrl = extractedFirstElementOfList.getImageUrl();
        assertTrue(StringUtils.isNotBlank(extractedFirstEntityUrl));
    }

    @Test(expected = CsvParsingException.class)
    public void shouldNotConvertCsvFileToListAndThrowException() throws FileNotFoundException {
        //GIVEN
        File initialFile = new File("src/test/resources/data/people_with_error.csv");
        InputStream inputStream = new FileInputStream(initialFile);
        //WHEN
        peopleCsvService.convertPeopleCsvFileToDatabaseEntity(inputStream);
        //THEN
        verifyNoInteractions(peopleRepository);
    }
}

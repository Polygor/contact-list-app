package com.polygor.contactlist.service;

import com.polygor.contactlist.dto.PeopleDto;
import com.polygor.contactlist.entity.PeopleEntity;
import com.polygor.contactlist.mapper.PeopleMapper;
import com.polygor.contactlist.repository.PeopleRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)

public class PeopleServiceTest {

    private static final long EXPECTED_PEOPLE_ID = 1L;
    private static final String EXPECTED_PEOPLE_NAME = "name";
    private static final String EXPECTED_PEOPLE_URL = "url";
    @Mock
    private PeopleRepository peopleRepository;
    @Mock
    private PeopleMapper peopleMapper;
    @InjectMocks
    private PeopleService peopleService;

    @Test
    public void shouldGetAllPeople() {
        //GIVEN
        PeopleEntity peopleEntity = new PeopleEntity();
        peopleEntity.setId(EXPECTED_PEOPLE_ID);
        peopleEntity.setName(EXPECTED_PEOPLE_NAME);
        peopleEntity.setImageUrl(EXPECTED_PEOPLE_URL);
        Pageable pageable = PageRequest.of(1, 1);
        when(peopleRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(peopleEntity)));
        when(peopleMapper.mapToDto(any())).thenCallRealMethod();
        //WHEN
        Page<PeopleDto> peopleDtos = peopleService.findAll(pageable);
        //THEN
        assertThat(peopleDtos.getContent().size(), is(1));
    }

    @Test
    public void shouldSearchPeopleByName() {
        //GIVEN
        PeopleEntity peopleEntity = new PeopleEntity();
        peopleEntity.setId(EXPECTED_PEOPLE_ID);
        peopleEntity.setName(EXPECTED_PEOPLE_NAME);
        peopleEntity.setImageUrl(EXPECTED_PEOPLE_URL);
        Pageable pageable = PageRequest.of(1, 1);
        when(peopleRepository.findByName(EXPECTED_PEOPLE_NAME, pageable)).thenReturn(new PageImpl<>(Collections.singletonList(peopleEntity)));
        when(peopleMapper.mapToDto(any())).thenCallRealMethod();
        //WHEN
        Page<PeopleDto> peopleDtos = peopleService.findBy(EXPECTED_PEOPLE_NAME, pageable);
        //THEN
        assertThat(peopleDtos.getContent().size(), is(1));
    }
}

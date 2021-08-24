package com.polygor.contactlist.service;

import com.polygor.contactlist.dto.PeopleDto;
import com.polygor.contactlist.entity.PeopleEntity;
import com.polygor.contactlist.mapper.PeopleMapper;
import com.polygor.contactlist.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final PeopleMapper peopleMapper;

    public Page<PeopleDto> findAll(Pageable pageable) {
        Page<PeopleEntity> peopleEntities = peopleRepository.findAll(pageable);
        return this.peopleMapper.mapToDto(peopleEntities);
    }

    public Page<PeopleDto> findBy(String name, Pageable pageable) {
        Page<PeopleEntity> peopleEntities = peopleRepository.findByName(name, pageable);
        return this.peopleMapper.mapToDto(peopleEntities);
    }
}

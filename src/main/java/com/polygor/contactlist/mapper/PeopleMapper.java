package com.polygor.contactlist.mapper;

import com.polygor.contactlist.dto.PeopleDto;
import com.polygor.contactlist.entity.PeopleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PeopleMapper {

    public Page<PeopleDto> mapToDto(Page<PeopleEntity> peopleEntities) {
        return peopleEntities.map(this::mapToDto);
    }

    private PeopleDto mapToDto(PeopleEntity peopleEntity) {
        PeopleDto peopleDto = new PeopleDto();
        if (peopleEntity != null) {
            peopleDto.setId(peopleEntity.getId());
            peopleDto.setName(peopleEntity.getName());
            peopleDto.setImageUrl(peopleEntity.getImageUrl());
        }
        return peopleDto;
    }
}

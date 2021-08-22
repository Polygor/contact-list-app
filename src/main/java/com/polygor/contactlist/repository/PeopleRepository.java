package com.polygor.contactlist.repository;

import com.polygor.contactlist.entity.PeopleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends PagingAndSortingRepository<PeopleEntity, Long> {

    Page<PeopleEntity> findByName(String name, Pageable pageable);
}


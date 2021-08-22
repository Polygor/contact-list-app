package com.polygor.contactlist.rest;


import com.polygor.contactlist.dto.PeopleDto;
import com.polygor.contactlist.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PeopleRestController {

   private final PeopleService peopleService;

    @RequestMapping("/show")
    public Page<PeopleDto> showPeople(Pageable pageable) {
        return peopleService.findAll(pageable);
    }
}

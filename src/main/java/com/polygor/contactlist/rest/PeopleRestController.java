package com.polygor.contactlist.rest;


import com.polygor.contactlist.dto.PeopleDto;
import com.polygor.contactlist.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleRestController {

    @Autowired
   private PeopleService peopleService;


    @RequestMapping("/people")
    public Page<PeopleDto> showPeople(Pageable pageable) {
        return peopleService.findAll(pageable);
    }

}

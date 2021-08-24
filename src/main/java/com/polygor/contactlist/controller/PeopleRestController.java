package com.polygor.contactlist.controller;


import com.polygor.contactlist.dto.PeopleDto;
import com.polygor.contactlist.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/people/")
public class PeopleRestController {

    @CrossOrigin("*")
    @GetMapping("all")
    public ResponseEntity<List<PeopleDto>> getAllPeopleWithPagination(Pageable pageable) {
        return new ResponseEntity<>(peopleService.findAll(pageable).getContent(), HttpStatus.OK);
    }

   private final PeopleService peopleService;
    @CrossOrigin("*")
    @GetMapping("search")
    public ResponseEntity<List<PeopleDto>> getPeopleBy(@RequestParam() String name, Pageable pageable) {
        return new ResponseEntity<>(peopleService.findBy(name, pageable).getContent(), HttpStatus.OK);
    }
}

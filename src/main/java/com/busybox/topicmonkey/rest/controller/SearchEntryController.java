package com.busybox.topicmonkey.rest.controller;

import com.busybox.topicmonkey.domain.model.SearchEntry;
import com.busybox.topicmonkey.rest.assembler.SearchEntryResourceAssembler;
import com.busybox.topicmonkey.rest.utils.RestApiUrls;
import com.busybox.topicmonkey.service.SearchEntryService;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: move CRUD & shared ops to AbstractController

@RestController
@ExposesResourceFor(SearchEntry.class)
@RequestMapping(value = RestApiUrls.SEARCH_ENTRY_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchEntryController {

    @Resource
    private SearchEntryService searchEntryService;

    @Resource
    private SearchEntryResourceAssembler searchEntryResourceAssembler;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<SearchEntry> get(@PathVariable("id") String userId) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<SearchEntry> searchEntry = searchEntryService.findById(userId);
        if (searchEntry.isPresent()) {
            response = toResponse(searchEntry.get());
        }
        return response;
    }

    @RequestMapping(value = RestApiUrls.ALL, method = RequestMethod.GET)
    public HttpEntity<SearchEntry> getAll() {
        List<org.springframework.hateoas.Resource<SearchEntry>> searchEntries = searchEntryService
                .findAll()
                .stream()
                .map(searchEntry -> searchEntryResourceAssembler.toResource(searchEntry))
                .collect(Collectors.toList());

        return new ResponseEntity(searchEntries, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<SearchEntry> create(@RequestBody SearchEntry searchEntry) {
        return toResponse(searchEntryService.create(SearchEntry
                .builder()
                .withName(searchEntry.getName())
                .withContent(searchEntry.getContent())
                .build()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity<SearchEntry> delete(@PathVariable("id") String searchEntryId) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<SearchEntry> searchEntry = searchEntryService.delete(searchEntryId);
        if (searchEntry.isPresent()) {
            response = toResponse(searchEntry.get());
        }
        return response;
    }

    private ResponseEntity toResponse(SearchEntry searchEntry) {
        return new ResponseEntity(searchEntryResourceAssembler.toResource(searchEntry), HttpStatus.OK);
    }

}

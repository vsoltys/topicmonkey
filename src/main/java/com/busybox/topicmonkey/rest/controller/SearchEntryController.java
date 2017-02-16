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
@RequestMapping(value = RestApiUrls.SEARCH_ENTRIES_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchEntryController {

    @Resource
    private SearchEntryService searchEntryService;

    @Resource
    private SearchEntryResourceAssembler searchEntryResourceAssembler;

    @RequestMapping(value = "/{searchEntry}", method = RequestMethod.GET)
    public HttpEntity<SearchEntry> getSearchEntry(@PathVariable("searchEntry") String searchEntryId) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<SearchEntry> searchEntry = searchEntryService.findById(searchEntryId);
        if (searchEntry.isPresent()) {
            response = toResponse(searchEntry.get());
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<SearchEntry> getAllByUser(@PathVariable("user") String userId) {
        List<org.springframework.hateoas.Resource<SearchEntry>> searchEntries = searchEntryService
                .findAllByUserId(userId)
                .stream()
                .map(searchEntry -> searchEntryResourceAssembler.toResource(searchEntry))
                .collect(Collectors.toList());

        return new ResponseEntity(searchEntries, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<SearchEntry> addSearchEntryForUser(@PathVariable("user") String userId,
                                                         @RequestBody SearchEntry searchEntry) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        Optional<SearchEntry> result = searchEntryService.addSearchEntryForUser(userId, searchEntry);
        if (result.isPresent()) {
            response = toResponse(result.get());
        }
        return response;
    }

    @RequestMapping(value = "/{searchEntry}", method = RequestMethod.DELETE)
    public HttpEntity<SearchEntry> deleteSearchEntry(@PathVariable("searchEntry") String searchEntryId) {
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

package com.parrot.tvmetadata.controllers;

import com.parrot.tvmetadata.services.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1", produces = APPLICATION_JSON_VALUE)
public class MetaDataController {

   @Autowired
   MetaDataService metaDataService;

    @GetMapping("/metadata")
    public ResponseEntity metadataPaged(@RequestParam(required = false, name = "search") List<String> parrotIds,
                                        @RequestParam(required = false, name = "page", defaultValue = "0") int page,
                                        @RequestParam(required = false, name = "size", defaultValue = "10") int size) {
        if(parrotIds == null) {
            return ResponseEntity.ok(metaDataService.findAll(PageRequest.of(page, size)));
        } else {
            return ResponseEntity.ok(metaDataService.findByParrotIds(parrotIds));
        }
    }
}

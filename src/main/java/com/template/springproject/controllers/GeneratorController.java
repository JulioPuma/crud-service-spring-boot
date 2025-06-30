package com.template.springproject.controllers;

import com.template.springproject.model.ResponseStatus;
import com.template.springproject.services.GeneratorServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/generator")
@RequiredArgsConstructor
@Slf4j
public class GeneratorController {

    private final GeneratorServiceImpl generatorServiceImpl;

    @PostMapping("")
    public Mono<ResponseStatus> generateDocument(){
        log.info("Init generate document");
        return generatorServiceImpl.generateDocument();
    }
}

package com.template.springproject.services;

import com.template.springproject.config.ApplicationProperties;
import com.template.springproject.model.Document;
import com.template.springproject.model.ResponseStatus;
import com.template.springproject.model.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl {

    private final ProcessDocumentImpl processDocument;
    private final ApplicationProperties properties;
    private final UploadServiceImpl uploadService;

    
    public Mono<ResponseStatus> generateDocument(){
      
      ResponseStatus status =
          ResponseStatus.builder()
          .status("OK")
          .description("Description")
          .build();
        
        return Mono.fromRunnable(() ->
                Mono.just(selectTemplate())
                .flatMap(document1 -> Mono.fromCallable(() -> processDocument.loadDocument2(document1)))
                .flatMap(uploadService::uploadFile2)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe()
            ).then(Mono.just(status));
    }
    
    private Document selectTemplate(){
      
      Template template = new Template();
      Document document = new Document();
      document.setTemplate(template);
      document.setData(template.getData());
      document.setFileName("NombreDelArchivo");
      document.setExtension(".docx");
      document.setFilePath("espacio/");
      return document;
    }
}

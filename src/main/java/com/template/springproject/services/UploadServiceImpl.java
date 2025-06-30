package com.template.springproject.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobAsyncClient;
import com.template.springproject.config.AzureStorageProperties;
import com.template.springproject.model.FileInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadServiceImpl {
  
  private final AzureStorageProperties storageProperties;
  
  public Mono<Void> uploadFile(FileInformation fileInformation){
    
    return Mono.defer(() -> {
      
      String connectionString = storageProperties.getConnectionString();
      if (connectionString == null || connectionString.isEmpty()){
        log.error("Azure storage connection string is not configured");
        return
          Mono.error(new IllegalArgumentException("Azure storage connection string is not configured"));
      }
      
      BlobContainerClient blobContainerClient =
        new BlobContainerClientBuilder()
          .connectionString(connectionString)
          .containerName(storageProperties.getContainerName())
          .buildClient();
      
      if(!blobContainerClient.exists()){
        log.error("The specified container does not exist in azure storage");
        return Mono.error(new IllegalStateException("The specified container does not exist in azure storage"));
      }
      
      String fileFullPath = fileInformation.getFilePath()
        + fileInformation.getFileName()
        + fileInformation.getExtension();
      
      BlobClient blobClient = blobContainerClient.getBlobClient(fileFullPath);
      
      try(ByteArrayInputStream bais = new ByteArrayInputStream(fileInformation.getByts())){
        blobClient.upload(bais, Long.parseLong(fileInformation.getFileSize()), true);
        
        log.info("File Uploaded to storage successfully");
        return Mono.empty();
      }catch (IOException e){
        return Mono.error(new Exception("Error uploading file to storage: ", e));
      }
    });
    
  }
  
  public Mono<Void> uploadFile2(FileInformation fileInformation){
    String connectionString = storageProperties.getConnectionString();
    String containerName = storageProperties.getContainerName();
    
    if (connectionString == null || connectionString.isEmpty()) {
      log.error("Azure storage connection string is not configured");
      return Mono.error(new IllegalArgumentException("Azure storage connection string is not configured"));
    }
    
    // Crear cliente asíncrono
    BlobContainerAsyncClient containerAsyncClient = new BlobContainerClientBuilder()
      .connectionString(connectionString)
      .containerName(containerName)
      .buildAsyncClient();
    
    // Validar existencia del contenedor de forma reactiva
    return containerAsyncClient.exists()
      .flatMap(exists -> {
        if (!exists) {
          log.error("The specified container does not exist in Azure Storage");
          return Mono.error(new IllegalStateException("The specified container does not exist in Azure Storage"));
        }
        
        // Construir ruta del archivo
        String fileFullPath = fileInformation.getFilePath()
          + fileInformation.getFileName()
          + fileInformation.getExtension();
        
        BlockBlobAsyncClient blobClient = containerAsyncClient
          .getBlobAsyncClient(fileFullPath)
          .getBlockBlobAsyncClient();
        
        byte[] fileBytes = fileInformation.getByts();
        Flux<ByteBuffer> data = Flux.just(ByteBuffer.wrap(fileBytes));
        
        // Subida del archivo de forma reactiva
        return blobClient.upload(data, fileBytes.length, true)
          .doOnSuccess(response -> log.info("File uploaded to storage successfully"))
          .then();
      });
  }
  
  
}

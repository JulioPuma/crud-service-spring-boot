package com.template.springproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FileInformation {
  private byte[] byts;
  private String extension;
  private String filePath;
  private String fileName;
  private String fileSize;
}

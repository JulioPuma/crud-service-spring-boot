package com.template.springproject.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Document extends FileInformation{
  private Template template;
  private Map<String, String> data;
}

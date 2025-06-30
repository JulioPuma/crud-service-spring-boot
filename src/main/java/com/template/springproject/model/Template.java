package com.template.springproject.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Template {
  private String templatePath;
  
  public Template(){
    this.setTemplatePath("templates/T0001.docx");
  }
  
  public Map<String, String> getData(){
    Map<String,String> data = new HashMap<>();
    data.put("{{NOMBRE}}", null);
    data.put("{{NOMBRE-ARTISTICO}}", "William Shakespeare");
    data.put("{{PARRAFO-EXTRA-1}}", getParagrah1());
    data.put("{{PARRAFO-EXTRA-2}}", getParagrah2());
    data.put("{{PARRAFO-EXTRA-3}}", getParagrah3());
    data.put("{{ANO-DE-CASADO}}", "14/10/1980");
    data.put("{{ANO-DE-FALLECIDO}}", "14/10/1990");
    return data;
  }
  
  private String getParagrah1(){
    return null;
  }
  
  private String getParagrah2(){
    return "Este es un separador de texto o tambien un parrafo para describir texto. \n vamos a ver si funciona";
  }
  
  private String getParagrah3(){
    return "texto chico salto linea -> \n siguiente linea \n";
  }
}

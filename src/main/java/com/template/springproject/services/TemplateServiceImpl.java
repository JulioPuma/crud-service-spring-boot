package com.template.springproject.services;

import com.template.springproject.model.Document;
import com.template.springproject.model.FileInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TemplateServiceImpl {
  
  private final ResourceLoader resourceLoader;
  private final ProcessDocumentImpl processDocument;
  
  public List<String> listTemplates() {
    try {
      Resource resource = resourceLoader.getResource("classpath:templates");
      Path templatesPath = Paths.get(resource.getURI());
      
      log.debug("====Template's List=====");
      return Files.list(templatesPath)
        .map(Path::getFileName)
        .map(Path::toString)
        .peek(log::debug)
        .collect(Collectors.toList());
      
    } catch (IOException e) {
      throw new RuntimeException("No se pudieron listar las plantillas en templates", e);
    }
  }

  public void loadDocument() throws IOException {
    
    ClassPathResource resource = new ClassPathResource("templates/T0001.docx");
    InputStream inputStream = resource.getInputStream();
    XWPFDocument document = new XWPFDocument(inputStream);
    inputStream.close();

    // Definir los valores a reemplazar
    Map<String, String> variables = new HashMap<>();
    variables.put("{{NOMBRE}}", "Julio");
    
    // Reemplazar texto en párrafos
    for (XWPFParagraph paragraph : document.getParagraphs()) {
      for (Map.Entry<String, String> entry : variables.entrySet()) {
        if (paragraph.getText().contains(entry.getKey())) {
          String replacedText = paragraph.getText().replace(entry.getKey(), entry.getValue());
          paragraph.getRuns().forEach(run -> run.setText("", 0)); // Limpiar texto anterior
          paragraph.createRun().setText(replacedText); // Insertar nuevo texto
        }
      }
    }
    
    // Guardar el documento generado
    FileOutputStream fos = new FileOutputStream("resultado.docx");
    document.write(fos);
    fos.close();
    document.close();
    
    System.out.println("Documento generado exitosamente.");
  
  }
  
  public FileInformation loadDocument(Document document) {
    
    try{
      ClassPathResource resource = new ClassPathResource(document.getTemplate().getTemplatePath());
      InputStream inputStream = resource.getInputStream();
      XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
      inputStream.close();
      
      // Definir los valores a reemplazar
      Map<String, String> variables = document.getData();
      
      // Reemplazar texto en párrafos
      for (XWPFParagraph paragraph : xwpfDocument.getParagraphs()) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
          if (paragraph.getText().contains(entry.getKey())) {
            String replacedText = paragraph.getText().replace(entry.getKey(), entry.getValue());
            paragraph.getRuns().forEach(run -> run.setText("", 0)); // Limpiar texto anterior
            paragraph.createRun().setText(replacedText); // Insertar nuevo texto
          }
        }
      }
      
      // Guardar el documento generado
      //FileOutputStream fos = new FileOutputStream("documents/export/documento.docx");
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      
      //xwpfDocument.write(fos);
      xwpfDocument.write(baos);
      
      baos.close();
      //fos.close();
      
      xwpfDocument.close();
      
      System.out.println("Documento generado exitosamente.");
      
      document.setByts(baos.toByteArray());
      document.setFileSize(String.valueOf(baos.size()));
      return document;
    }catch (Exception e){
      log.error("Error al generar el documento en memoria");
      throw new RuntimeException();
    }

  }
  
  public FileInformation loadDocument2(Document document) {
    
    try{
    ClassPathResource resource = new ClassPathResource(document.getTemplate().getTemplatePath());
    InputStream inputStream = resource.getInputStream();
    XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
    inputStream.close();
    
    // Definir los valores a reemplazar
    Map<String, String> variables = document.getData();
      
      for (XWPFParagraph paragraph : xwpfDocument.getParagraphs()) {
        List<XWPFRun> runs = paragraph.getRuns();
        if (runs == null || runs.isEmpty()) continue;
        
        // Combinar todo el texto visible del párrafo
        StringBuilder fullTextBuilder = new StringBuilder();
        for (XWPFRun run : runs) {
          String text = run.getText(0);
          if (text != null) fullTextBuilder.append(text);
        }
        String fullText = fullTextBuilder.toString();
        
        // Verificar si hay alguna variable que reemplazar
        boolean hasReplacement = false;
        for (Map.Entry<String, String> entry : variables.entrySet()) {
          if (fullText.contains(entry.getKey())) {
            String replacement = entry.getValue() != null ? entry.getValue() : "";
            fullText = fullText.replace(entry.getKey(), replacement);
            hasReplacement = true;
          }
        }
        
        // Si hubo reemplazo, limpiar los runs y crear uno nuevo con formato copiado
        if (hasReplacement) {
          // Copiar el estilo del primer run ANTES de eliminar los runs
          CTRPr originalRPr = runs.get(0).getCTR().getRPr();
          CTRPr style = null;
          if (originalRPr != null) {
            style = (CTRPr) originalRPr.copy(); // Clonar el objeto XML para evitar desconexiones
          }
          // Eliminar los runs originales
          for (int i = runs.size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
          }
          
          // Crear nuevo run con el texto reemplazado
          XWPFRun newRun = paragraph.createRun();
          writeTextWithLineBreaks(newRun, fullText);
          
          // Aplicar el estilo copiado (si lo tenía)
          if (style != null) {
            newRun.getCTR().setRPr(style);
          }
        }

      }

      
      // Guardar el documento generado
      //FileOutputStream fos = new FileOutputStream("documents/export/documento.docx");
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      //xwpfDocument.write(fos);
      xwpfDocument.write(baos);
      baos.close();
      //fos.close();
      xwpfDocument.close();
      
      document.setByts(baos.toByteArray());
      document.setFileSize(String.valueOf(baos.size()));
      return document;
    }catch (Exception e){
      log.error("Error al generar el documento en memoria: ", e);
      throw new RuntimeException();
    }
  }
  
  private void writeTextWithLineBreaks(XWPFRun run, String text) {
    if (text == null) return;
    
    String[] lines = text.split("\n", -1);
    for (int i = 0; i < lines.length; i++) {
      run.setText(lines[i]);
      if (i < lines.length - 1) {
        run.addBreak();
      }
    }
  }

}

package com.template.springproject.services;

import com.template.springproject.model.Document;
import com.template.springproject.model.FileInformation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProcessDocumentImpl {
  
  public FileInformation loadDocument2(Document document) {
    try {
      ClassPathResource resource = new ClassPathResource(document.getTemplate().getTemplatePath());
      InputStream inputStream = resource.getInputStream();
      XWPFDocument doc = new XWPFDocument(inputStream);
      inputStream.close();
      
      Map<String, String> variables = document.getData();
      
      // Recorremos los elementos del cuerpo
      for (int i = 0; i < doc.getBodyElements().size(); i++) {
        if (doc.getBodyElements().get(i) instanceof XWPFParagraph) {
          XWPFParagraph paragraph = (XWPFParagraph) doc.getBodyElements().get(i);
          boolean shouldRemove = processParagraphAndCheckRemoval(paragraph, variables);
          if (shouldRemove) {
            doc.removeBodyElement(i);
            i--; // retroceder índice porque el tamaño disminuyó
          }
        }
      }
      
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      doc.write(baos);
      baos.close();
      doc.close();
      
      document.setByts(baos.toByteArray());
      document.setFileSize(String.valueOf(baos.size()));
      return document;
      
    } catch (Exception e) {
      log.error("Error al generar el documento en memoria: ", e);
      throw new RuntimeException("Error al generar el documento", e);
    }
  }
  
  private boolean processParagraphAndCheckRemoval(XWPFParagraph paragraph, Map<String, String> variables) {
    List<XWPFRun> runs = paragraph.getRuns();
    if (runs == null || runs.isEmpty()) return false;
    
    // 1. Unir el texto completo del párrafo
    StringBuilder combinedText = new StringBuilder();
    for (XWPFRun run : runs) {
      String text = run.getText(0);
      if (text != null) combinedText.append(text);
    }
    
    String fullText = combinedText.toString();
    if (fullText.trim().isEmpty()) return false;
    
    // 2. Reemplazar todas las variables
    boolean hadVariable = false;
    String processedText = fullText;
    
    for (Map.Entry<String, String> entry : variables.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue() != null ? entry.getValue() : "";

      if (processedText.contains(key)) {
        processedText = processedText.replace(key, value);
        hadVariable = true;
      }
    }
    
    // 3. Si luego del reemplazo todo quedó vacío, eliminar párrafo
    if (hadVariable && processedText.trim().isEmpty()) {
      return true;
    }
    
    // 4. Si hay contenido final, regenerar el párrafo correctamente
    //    - eliminar runs
    //    - crear run nuevo con el texto reemplazado
    //    - mantener estilo del primer run
    CTRPr style = null;
    if (!runs.isEmpty()) {
      CTRPr originalStyle = runs.get(0).getCTR().getRPr();
      if (originalStyle != null) {
        style = (CTRPr) originalStyle.copy();
      }
    }
    
    for (int i = runs.size() - 1; i >= 0; i--) {
      paragraph.removeRun(i);
    }
    
    XWPFRun newRun = paragraph.createRun();
    writeTextWithLineBreaks(newRun, processedText);
    
    if (style != null) {
      newRun.getCTR().setRPr(style);
    }
    
    return false; // párrafo no se elimina
  }
  
  private void writeTextWithLineBreaks(XWPFRun run, String text) {
    if (text == null || text.isEmpty()) {
      return;
    }
    
    // Si no contiene \n, escríbelo directamente
    if (!text.contains("\n")) {
      run.setText(text);
      return;
    }
    
    // Si contiene \n, dividimos por líneas y agregamos breaks
    String[] lines = text.split("\n", -1);
    for (int i = 0; i < lines.length; i++) {
      run.setText(lines[i]);
      if (i < lines.length - 1) {
        run.addCarriageReturn();
      }
    }
  }

}

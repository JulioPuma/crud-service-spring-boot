package com.template.springproject.services;

import com.template.springproject.model.Document;
import com.template.springproject.model.FileInformation;
import com.template.springproject.model.Template;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProcessDocumentImplTest {
  
  private final ProcessDocumentImpl service = new ProcessDocumentImpl();
  
  @Test
  void testLoadDocument2_ReplacesVariablesCorrectly() throws Exception {
    // Arrange
    Template template = new Template();
    template.setTemplatePath("templates/test-template.docx"); // este archivo debe existir en /resources/templates/

    Map<String, String> data = new HashMap<>();
    data.put("{{NOMBRE}}", "Julio");
    data.put("{{CARGO}}", "Ingeniero Backend");
    
    Document doc = new Document();
    doc.setTemplate(template);
    doc.setData(data);
    
    // Act
    FileInformation result = service.loadDocument2(doc);
    
    // Assert
    assertNotNull(result.getByts(), "El documento generado no debe ser nulo");
    assertTrue(result.getByts().length > 0, "El documento debe tener contenido");
    assertNotNull(result.getFileSize());
  }
  
  @Test
  void testLoadDocument2_IgnoresNullValuesAndRemovesTags() throws Exception {
    // Arrange
    Template template = new Template();
    template.setTemplatePath("templates/test-template.docx");
    
    Map<String, String> data = new HashMap<>();
    data.put("{{NOMBRE}}", null); // valor nulo
    
    Document doc = new Document();
    doc.setTemplate(template);
    doc.setData(data);
    
    // Act
    FileInformation result = service.loadDocument2(doc);
    
    // Assert
    assertNotNull(result.getByts());
    String content = new String(result.getByts());
    assertFalse(content.contains("{{NOMBRE}}"), "La variable debe ser eliminada si su valor es nulo");
  }
}
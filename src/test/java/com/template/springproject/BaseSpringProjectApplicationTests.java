package com.template.springproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest //Se activa el contexto de spring
@ActiveProfiles("test") //Indico el perfil al levantarse el contexto de spring
//@TestPropertySource(properties = {}) //Se usa en caso desea especificar propiedades adicionales.
class BaseSpringProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}

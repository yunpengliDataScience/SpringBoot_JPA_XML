package org.dragon.yunpeng.sandbox.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dragon.yunpeng.sandbox.entities.Form;
import org.dragon.yunpeng.sandbox.repositories.FormRepository;
import org.dragon.yunpeng.sandbox.services.FormServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
//Enable Spring DI and transaction, more light weight than @SpringTest, which is for integration test.
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
public class FormServiceTest {

	// Object annotated with @Mock will be injected to the one annotated with
	// @InjectMocks.
	@InjectMocks
	FormServiceImp formService = new FormServiceImp();

	@Mock
	FormRepository formRepository;

	@BeforeEach
	public void setup() {

	}

	@Test
	public void testExportDataToString() {
		List<Form> forms = new ArrayList<Form>();

		Form form1 = new Form();
		form1.setCode("Code1");
		form1.setField1("F1");
		form1.setField2("F2");
		form1.setName("Form_1");
		form1.setTextArea("Text 1");

		Form form2 = new Form();
		form2.setCode("Code2");
		form2.setField1("F1");
		form2.setField2("F2");
		form2.setName("Form_2");
		form2.setTextArea("Text 2");

		forms.add(form1);
		forms.add(form2);

		when(formRepository.findAll()).thenReturn(forms);

		String output = formService.exportDataToString();

		System.out.println(output);
		
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
				+ "<formList>\n"
				+ "    <form>\n"
				+ "        <id>0</id>\n"
				+ "        <name>Form_1</name>\n"
				+ "        <code>Code1</code>\n"
				+ "        <field1>F1</field1>\n"
				+ "        <field2>F2</field2>\n"
				+ "        <textArea>Text 1</textArea>\n"
				+ "    </form>\n"
				+ "    <form>\n"
				+ "        <id>0</id>\n"
				+ "        <name>Form_2</name>\n"
				+ "        <code>Code2</code>\n"
				+ "        <field1>F1</field1>\n"
				+ "        <field2>F2</field2>\n"
				+ "        <textArea>Text 2</textArea>\n"
				+ "    </form>\n"
				+ "</formList>";
		
		assertEquals(xml.trim(), output.trim());
	}
}

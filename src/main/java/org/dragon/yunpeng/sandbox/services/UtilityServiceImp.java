package org.dragon.yunpeng.sandbox.services;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class UtilityServiceImp {

	public static void main(String[] args) {
        //String xmlFilePath = "example.xml"; // Path to your XML file

        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // Enable namespace awareness
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Set an error handler to catch parsing errors
            ErrorHandler errorHandler = new SimpleErrorHandler();
            builder.setErrorHandler(errorHandler);

            
            String workingDirectory = System.getProperty("user.dir");
			// Convert the list to XML and write to a file
			File file = new File(workingDirectory + "\\invalid.xml");
			
            // Parse the XML file
            builder.parse(file);

            // If parsing completes without errors, XML is well-formed
            System.out.println("XML is well-formed.");
        } catch (ParserConfigurationException | SAXException e) {
            System.out.println("XML parsing error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading XML file: " + e.getMessage());
        }
    }

    // Simple error handler implementation
    private static class SimpleErrorHandler implements ErrorHandler {
        @Override
        public void warning(SAXParseException exception) throws SAXException {
            System.out.println("Warning: " + exception.getMessage());
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            System.out.println("Error: " + exception.getMessage());
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            System.out.println("Fatal Error: " + exception.getMessage());
        }
    }
}

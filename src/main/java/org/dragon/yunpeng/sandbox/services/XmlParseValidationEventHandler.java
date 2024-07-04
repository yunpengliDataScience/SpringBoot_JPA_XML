package org.dragon.yunpeng.sandbox.services;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlParseValidationEventHandler implements ValidationEventHandler {

	private Logger logger = LoggerFactory.getLogger(XmlParseValidationEventHandler.class);

	@Override
	public boolean handleEvent(ValidationEvent event) {

		if (ValidationEvent.ERROR == event.getSeverity() || ValidationEvent.FATAL_ERROR == event.getSeverity()) {

			ValidationEventLocator locator = event.getLocator();

			String message = event.getMessage();
			System.out.println(event.getSeverity() + ", " + message);
			System.out.println("Line number:" + locator.getLineNumber());
			System.out.println("Column number:" + locator.getColumnNumber());

			// foreign XML tag detected, stop processing any further.
			if (message.contains("unexpected element")) {

				logger.error(message);
				logger.error("Line number:" + locator.getLineNumber());
				logger.error("Column number:" + locator.getColumnNumber());

				return false;
			}

		}

		return true;
	}

}
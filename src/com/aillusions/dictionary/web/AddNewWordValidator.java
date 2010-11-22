package com.aillusions.dictionary.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aillusions.dictionary.core.Manager;

public class AddNewWordValidator implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	private Manager manager;

	public boolean supports(final Class clazz) {
		return AddNewWord.class.equals(clazz);
	}

	public void validate(final Object obj, final Errors errors) {
		AddNewWord pi = (AddNewWord) obj;
		if (pi == null) {
			errors.rejectValue("word", "error.not-specified", null, "Value required.");
		} else {
			this.logger.info("Validating with " + pi + ": " + pi.getWord());
			if (pi.getWord().equals(null) || pi.getWord().length() == 0) {
				errors.rejectValue("word", "error.empty-word");
			} else if (manager.getPairsManager().getPairByKey(pi.getWord()) != null) {
				errors.rejectValue("word", "error.word-already-exists", new Object[] { pi.getWord() }, "Value already exists.");
			}
		}
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
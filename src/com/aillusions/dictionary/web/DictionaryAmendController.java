package com.aillusions.dictionary.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.model.Pair;

public class DictionaryAmendController extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private Manager manager;

	@Override
	public ModelAndView onSubmit(final Object command) throws ServletException {

		AddNewWord addNewWord = (AddNewWord) command;
		Pair pair = new Pair();
		pair.setEnglish(addNewWord.getWord());
		pair.setTranscription(addNewWord.getTranscrition());
		pair.setRussian(addNewWord.getTranslate());
		manager.getPairsManager().addNew(pair);

		this.logger.info("returning from PriceIncreaseForm view to " + getSuccessView());
		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		AddNewWord addNewWord = new AddNewWord();
		return addNewWord;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
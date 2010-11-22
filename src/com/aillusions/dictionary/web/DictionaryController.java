package com.aillusions.dictionary.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.core.WorkspaceManager.DictionaryHasToBeCreated;

public class DictionaryController implements Controller {
	protected final Log logger = LogFactory.getLog(getClass());

	private Manager manager;
	private MessageSource messageSource;

	public DictionaryController() {
		logger.info("Constructor");
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.logger.info("handle Request");

		if (!manager.getWorkspaceManager().isLoaded()) {
			try {
				manager.getWorkspaceManager().load();
			} catch (DictionaryHasToBeCreated e) {
				return new ModelAndView("create_dict");
			}
		}

		String dictName = request.getParameter("dictName");
		if (dictName != null) {
			this.logger.info("change Dictionary");
			manager.getCurrentStateManager().setCurrentDict(manager.getWorkspaceManager().getDictionaryByName(dictName));
		}

		String selectWord = request.getParameter("selectWord");
		if (selectWord != null) {
			this.logger.info("select Word");
			manager.getCurrentStateManager().setCurrentPairByKey(selectWord);
		}

		String reloadDict = request.getParameter("reloadDict");
		if (reloadDict != null) {
			if (Boolean.parseBoolean(reloadDict)) {
				this.logger.info("reload Dictionary");
				String currDictName = manager.getCurrentStateManager().getCurrentDictionary().getDisplayName();
				try {
					manager.getWorkspaceManager().load();
				} catch (DictionaryHasToBeCreated e) {
					return new ModelAndView("create_dict");
				}
				manager.getWorkspaceManager().selectDictionary(currDictName);
			}
		}

		String removeWord = request.getParameter("removeWord");
		if (removeWord != null && removeWord.length() > 0) {
			String actionConfirmation = request.getParameter("actionConfirmation");
			if (actionConfirmation != null) {
				if (actionConfirmation.equals("Yes")) {
					this.logger.info("remove Word");
					manager.getCurrentStateManager().setCurrentPairByKey(removeWord);
					manager.getCurrentStateManager().removeCurrentPair();
				}
			} else {
				Map<String, String> model = new HashMap<String, String>();
				model.put("actionToBeConfirmed", "removeWord");
				model.put("valueToBeConfirmed", removeWord);

				model.put("confirmationTitle", messageSource.getMessage("confirmation.remove-word", new Object[] { removeWord }, Locale
						.getDefault()));
				return new ModelAndView("are_you_sure", model);
			}

		}

		response.setContentType("text/html; charset=utf-8");
		return new ModelAndView("home", "manager", this.manager);

	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(final Manager pManager) {
		this.manager = pManager;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
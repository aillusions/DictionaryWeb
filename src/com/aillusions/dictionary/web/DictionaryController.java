package com.aillusions.dictionary.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.aillusions.dictionary.core.Manager;

public class DictionaryController implements Controller {
	protected final Log logger = LogFactory.getLog(getClass());

	private Manager manager;

	public DictionaryController() {
		logger.info("Constructor");
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.logger.info("handle Request");

		String dictName = request.getParameter("dictName");
		if (dictName != null) {
			this.logger.info("change Dictionary");
			manager.getWorkspaceManager().selectDictionary(dictName);
		}

		String reloadDict = request.getParameter("reloadDict");
		if (reloadDict != null) {
			if (Boolean.parseBoolean(reloadDict)) {
				this.logger.info("reload Dictionary");
				String currDictName = manager.getCurrentStateManager().getCurrentDictionary().getDisplayName();
				manager.Load();
				manager.getWorkspaceManager().selectDictionary(currDictName);
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

}
package com.aillusions.dictionary.web;

public class AddNewWord {

	private String word;
	private String translate;
	private String transcrition;
	private String[] samples;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	public String getTranscrition() {
		return transcrition;
	}

	public void setTranscrition(String transcrition) {
		this.transcrition = transcrition;
	}

	public String[] getSamples() {
		return samples;
	}

	public void setSamples(String[] samples) {
		this.samples = samples;
	}

}

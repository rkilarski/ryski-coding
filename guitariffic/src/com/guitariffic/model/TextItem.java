package com.guitariffic.model;

/**
 * This is the object that will contain a single line and its type (chord, lyric, or label)
 * 
 * @author ryszardkilarski
 * 
 */
public class TextItem {
	private String text;
	private TextType type;

	public TextItem() {
		this("", TextType.LYRIC);
	}

	public TextItem(String text, TextType type) {
		this.text = text;
		this.type = type;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the type
	 */
	public TextType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TextType type) {
		this.type = type;
	}
}

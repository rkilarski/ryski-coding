package com.guitariffic.model;

import java.awt.Component;
import java.io.Serializable;

/**
 * The base class for the text area that appears on the left pane of the application. All impementors must be serializable and inherit from
 * <code>java.awt.Component</code>.
 * 
 * <p>
 * Each version of a text area must implemented these abstract methods which are visible to clients for manipulation and serialization. This class
 * makes no restriction on what class is to be used to implement these methods other than it must be able to be placed on a <code>Component</code> and
 * carry out these abstract methods.
 * 
 * @author Brian Collins
 * @version 1.0, Nov 2011
 */
public abstract class BaseTextArea implements Serializable
{
	private static final long serialVersionUID = 1L;

	public enum TextLineType
	{
		CHORD, LABEL, LYRIC
	}

	/**
	 * Returns the <code>Component</code> that appears in the left pane of the application.
	 * 
	 * @return component for the text pane.
	 */
	abstract public Component getTextAreaControl();

	/**
	 * Sets the implemented TextArea component for the current view.
	 * 
	 * @param textArea
	 *            swing component that holds the text area.
	 */
	abstract public void setTextAreaControl(Component textArea);

	/**
	 * Returns the entire text in the text area pane as a single string.
	 * 
	 * @return the entire text area in one string.
	 */
	abstract public String getTextAreaAsString();

	/**
	 * Returns the text in the text area pane as an array of strings representing the individual lines of the text area. Each of these lines are
	 * associated with an <code>
	 * 	EnumTextLineType</code>.
	 * 
	 * @return string array carrying each line in the text area display.
	 */
	abstract public String[] getTextAreaArray();

	/**
	 * Returns the <code>EnumTextLineType</code> for the line number given as the method parameter.
	 * 
	 * @param lineNumber
	 *            line number whose type is to be ascertained.
	 * 
	 * @return line type for the given line number.
	 */
	abstract public TextLineType getLineType(int lineNumber);
}

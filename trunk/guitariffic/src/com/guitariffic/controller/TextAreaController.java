package com.guitariffic.controller;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Document;

import com.guitariffic.model.BaseTextArea;
import com.guitariffic.model.enums.EnumTextAreaType;

/**
 *	The controller for the text area component of the application.
 *	This class mediates between the text area model and the text
 *	area view in the MVC paradigm.
 *
 *	<p>All manipulation of the text area component is handled by
 *	this class. The functions available to this class are spelled
 *	out in the <code>BaseTextArea</code> class. For example, this
 *	class can distinguish which type of text area is being used and
 *	apply the appropriate logic to manipulate it.
 *
 * 	@author  Brian Collins
 * 	@version 1.0, Nov 2011
 */
public class TextAreaController 
{
	private BaseTextArea textArea;

	/**
	 * 	Initializes the text area controller.
	 */
	public TextAreaController()
	{
		textArea = null;
	}

	/**
	 * 	Sets the text area model to the given textArea.
	 * 
	 * 	@param   textArea   text area component generated
	 * 	for use as the left pane in the application.
	 */
	public void setTextArea(BaseTextArea textArea)
	{
		this.textArea = textArea;
	}

	/**
	 * 	Given the input string, copies it to the text area's
	 *  internal model.
	 * 
	 * 	@param   string   text so initialize the text area 
	 * 	mode.
	 */
	public void setText(String string)
	{
		EnumTextAreaType type = textArea.getTextAreaType();

		switch (type)
		{
		case STYLED:
			Component[] components = ((JPanel) textArea.getTextAreaControl()).getComponents();
			Component component = null;
			for (int i = 0; i < components.length; i++) {
				component = components[i];
				if (component instanceof JTextPane)	{
			        Document doc = ((JTextPane) component).getDocument();

			        // Move the insertion point to the end
			        ((JTextPane) component).setCaretPosition(doc.getLength());

			        // Insert the text
			        ((JTextPane) component).replaceSelection(string);
					break;
				}
			}			
			break;
		case TEXT:
			((JTextArea) textArea.getTextAreaControl()).append(string);
			break;
		case TABLE:
		case HTML:
		default:
			try
			{
				throw new Exception("Unimplemented TextAreaType " + type);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 	Gets the entire text in the text area panel of the
	 * 	application.
	 * 
	 * 	@return  Single string containing the text in the
	 * 	text area panel. 
	 */
	public String getText()
	{
		String str = "";

		EnumTextAreaType type = textArea.getTextAreaType();
		switch (type)
		{
		case STYLED:
			Component[] components = ((JPanel) textArea.getTextAreaControl()).getComponents();
			Component component = null;
			for (int i = 0; i < components.length; i++) {
				component = components[i];
				if (component instanceof JTextPane)	{
					str = ((JTextPane) component).getText();
					break;
				}
			}			
			break;
		case TEXT:
			str = ((JTextArea)textArea.getTextAreaControl()).getText();
			break;
		case TABLE:
		case HTML:
		default:
			try
			{
				throw new Exception("Unimplemented TextAreaType " + type);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return str;
	}

	/**
	 * 	Gets the text area base component to be displayed in the
	 * 	text area panel of the application.
	 * 
	 * 	@return  swing component containing the text area. 
	 */
	public Component getTextArea()
	{
		Component textAreaComponent = null;

		EnumTextAreaType type = textArea.getTextAreaType();
		switch (type)
		{
		case STYLED:
			textAreaComponent = textArea.getTextAreaControl();
			break;
		case TEXT:
			textAreaComponent = textArea.getTextAreaControl();
			break;
		case TABLE:
		case HTML:
		default:
			try
			{
				throw new Exception("Unimplemented TextAreaType " + type);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return textAreaComponent;
	}
}

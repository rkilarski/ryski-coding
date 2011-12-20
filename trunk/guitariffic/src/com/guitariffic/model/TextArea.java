package com.guitariffic.model;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

import com.guitariffic.model.enums.EnumTextAreaType;
import com.guitariffic.model.enums.EnumTextLineType;

/**
 *	The base class for the text area that appears on the left pane of
 *	the application. All impementors must be serializable and inherit
 *	from <code>java.awt.Component</code>.
 *
 *	<p>Each version of a text area must implemented these abstract
 *	methods which are visible to clients for manipulation and
 *	serialization. This class makes no restriction on what class is
 *	to be used to implement these methods other than it must be able
 *	to be placed on a <code>Component</code> and carry out these
 *	abstract methods.
 *
 * 	@author  Brian Collins
 * 	@version 1.0, Nov 2011
 */
/**
 * 	Returns the <code>EnumTextLineType</code> for the line
 * 	number given as the method parameter.
 * 
 * 	@param   lineNumber   line number whose type is to be
 * 	ascertained.
 * 
 * 	@return  line type for the given line number. 
 */
public class TextArea extends BaseTextArea
{
	private static final long serialVersionUID = 1L;

	private JTextArea textArea;
	
	// Set up the tags for labels and chords. Element 0 marks the beginning of
	// that line type, element 1 tags any lines containing it anywhere on the line.
	private transient char[] labelLineMarkers = new char[]{'!','*'};
	private transient char[] chordLineMarkers = new char[]{'_','~'};

	/**
	 * 	Constructor for the plain text area that is initialized to
	 * 	the given text string. This class uses a JTextArea to model
	 * 	the text panel in the application. This text area uses a single
	 * 	font and marks lines for type by using specific characters in
	 * 	certain positions. 
	 * 
	 * 	@param   text  intializing text for the text area
	 */
	public TextArea(String text)
	{
		setUp();
		textArea.append(text);
	}

	/**
	 * 	Sets up an empty plain text area.
	 */
	public TextArea()
	{
		setUp();
	}

	private void setUp()
	{
		Font font = new Font("Serif", Font.PLAIN, 13);
		textArea = new JTextArea();
		textArea.setFont(font);
		textArea.setSize(317,510);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);		
	}

	/**
	 * 	Implements the required <code>BaseTextArea</code> method
	 * 	for determining the current text area type in the
	 * 	application.
	 * 
	 * 	@return  the enumerated type of the current text area. 
	 */
	@Override
	public EnumTextAreaType getTextAreaType()
	{
		return EnumTextAreaType.TEXT;
	}

	/**
	 * 	Gets the underlying <code>JTextArea</code> for the
	 * 	text area in the application.
	 * 
	 * 	@return  the plain text area to be displayed in the
	 * 	view. 
	 */
	public JTextArea getTextAreaControl()
	{
		return textArea;
	}

	@Override
	public String getTextAreaAsString()
	{
		return textArea.getText();
	}

	@Override
	public String[] getTextAreaArray() {
		
		// Set up position markers
	    int totLength = textArea.getDocument().getLength();	    
	    int position = 0, eol;
	    String line;
	    
	    // Add extra space for inserting carriage returns
	    StringBuffer strBuf = new StringBuffer((int)(totLength*1.10));
	    String[] retStrArray;
	    
	    // Do in try catch in case of badLocation
	    try {
	        while (position < totLength) {
	            eol = Utilities.getRowEnd(textArea, position);
	            if (eol < 0) 
	                break;

	            // Move one extra space over for newline
	            eol = Math.min(eol+1, totLength);

	            line = textArea.getDocument().getText(position, eol-position);
	            strBuf.append(line);

	            // Add a newline if s does not have one
	            if (!line.endsWith("\n")) 
	                strBuf.append('\n');

	            position = eol;
	        }
	    } catch (BadLocationException e) {
	    }
	    
	    // Split the string into lines
	    retStrArray = strBuf.toString().split("\n");
	    return retStrArray;
	}

	@Override
	public EnumTextLineType getLineType(int lineNumber) {
	    String[] lines = getTextAreaArray();
	    
	    // First priority, check for LABEL lines
		if( lines[lineNumber].indexOf(labelLineMarkers[0]) == 0 || 
				lines[lineNumber].indexOf(labelLineMarkers[1]) > -1)
		    			return EnumTextLineType.LABEL;
	    	
	    // Then check for CHORD lines
		if( lines[lineNumber].indexOf(chordLineMarkers[0]) == 0 || 
				lines[lineNumber].indexOf(chordLineMarkers[1]) > -1)
		    			return EnumTextLineType.CHORD;	    	

    	// Must be a LYRIC
		return EnumTextLineType.LYRIC;
	}

	@Override
	public void setTextAreaControl(Component textArea)
	{
		this.textArea=(JTextArea) textArea;
	};	
}

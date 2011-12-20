package com.guitariffic.model;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;

import com.guitariffic.model.enums.EnumTextAreaType;
import com.guitariffic.model.enums.EnumTextLineType;

/**
 *	This class implements a styled version for the text area
 *	panel. Individual lines in the text area panel can be marked
 *	with either bold or italic font style to distinguish its
 *	function in the application as either a label or chord note.
 *
 *	<p>A JTextPane is used as the underlying component in order
 *	to implement the styles. The styles are configurable and
 *	are presently used to effect bold and italic styling on the
 *	text in the text area but can also be used to change font
 *	color, font size and font type.
 *
 *	<p>This class uses a toolbar with buttons that are used to
 *	mark specific lines with the appropriate style. The toolbar
 *	can be dislodged from the application to suit the users 
 *	preference while maintaining its functionality.
 *
 * 	@author  Brian Collins
 * 	@version 1.0, Nov 2011
 */
public class StyledTextArea extends BaseTextArea
{
	private static final long serialVersionUID = 2L;

	private transient JTextPane textArea;
	private StyledDocument styledDocument;
	
	/**
	 * 	Initialized the styled text area and it's underlying
	 * 	styled document model.
	 */
	public StyledTextArea()
	{
		super();
		styledDocument = null;
	}

	@Override
	public EnumTextAreaType getTextAreaType()
	{
		return EnumTextAreaType.STYLED;
	}

	/**
	 * 	Gets the panel to be placed into the applications's view
	 * 	containing the styled text area. The panel also contains
	 * 	the toolbar with the buttons that mark lines with the
	 * 	requisite style information denoting line type.
	 * 
	 * 	@return  the JPanel with the text area and toolbar. 
	 */
	public JPanel getTextAreaControl()
	{
	    JPanel panel = new JPanel();
	    if (styledDocument==null){
	    	textArea = new JTextPane();
	    	styledDocument = textArea.getStyledDocument();
	    }else{
	    	textArea = new JTextPane(styledDocument);
	    }
		Font font = new Font("Serif", Font.PLAIN, 13);
		textArea.setFont(font);
		textArea.setSize(317,510);
	    
	    panel.setLayout(new BorderLayout());
	    panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	    
	    JToolBar toolbar = new JToolBar();
	    JButton lyric = new JButton("Lyric");
	    JButton label = new JButton("Label");
	    JButton chord = new JButton("Chord");

	    toolbar.add(lyric);
	    toolbar.add(label);
	    toolbar.add(chord);
		
	    //final StyledDocument styledDocument = textArea.getStyledDocument();

	    Style style = textArea.addStyle("Lyric", null);
	    StyleConstants.setBold(style, false);
	    StyleConstants.setItalic(style, false);

	    style = textArea.addStyle("Label", null);
	    StyleConstants.setBold(style, true);
	    StyleConstants.setItalic(style, false);

	    style = textArea.addStyle("Chord", null);
	    StyleConstants.setBold(style, false);
	    StyleConstants.setItalic(style, true);
	    
	    lyric.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		try {
	    			int start = Utilities.getRowStart(textArea, textArea.getCaretPosition());
	    			int end = Utilities.getRowEnd(textArea, textArea.getCaretPosition());
    				styledDocument.setCharacterAttributes(start, end - start, textArea.getStyle("Lyric"), true);
    				styledDocument.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()
    						- textArea.getSelectionStart(), textArea.getStyle("Lyric"), true);
	    		} catch (BadLocationException ble) {
	    		}
	    	}
	    });
	    
	    label.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		try {
	    			int start = Utilities.getRowStart(textArea, textArea.getCaretPosition());
	    			int end = Utilities.getRowEnd(textArea, textArea.getCaretPosition());
    				styledDocument.setCharacterAttributes(start, end - start, textArea.getStyle("Label"), true);
    				styledDocument.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()
    						- textArea.getSelectionStart(), textArea.getStyle("Label"), true);
	    		} catch (BadLocationException ble) {
	    		}
	    	}
	    });
	    
	    chord.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent ae) {
	    		try {
	    			int start = Utilities.getRowStart(textArea, textArea.getCaretPosition());
	    			int end = Utilities.getRowEnd(textArea, textArea.getCaretPosition());
    				styledDocument.setCharacterAttributes(start, end - start, textArea.getStyle("Chord"), true);
    				styledDocument.setCharacterAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd()
    						- textArea.getSelectionStart(), textArea.getStyle("Chord"), true);
	    		} catch (BadLocationException ble) {
	    		}
	    	}
	    });
	    
	    panel.add(toolbar, BorderLayout.NORTH);
	    panel.add(textArea);
	    
		return panel;
	}

	/**
	 * 	Gets the <code>JTextPane</code> associated with the	
	 *	text area. This method does not return the toolbar
	 *	associated with the left pane of the application.
	 * 
	 * 	@return  the text area represented by this text area. 
	 */
	public JTextPane getTextArea()
	{
		return textArea;
	}

	/**
	 * 	Sets the internal model for the text area to the given
	 * 	string. This method is used to populate the text area
	 * 	during serialization or initialization.
	 * 
	 * 	@param   textArea  the <code>JTextPane</code> created 
	 * 	externally that is used to populate the internal model.
	 */
	public void setTextArea(JTextPane textArea)
	{
		this.textArea = textArea;
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
	    int totLength = textArea.getDocument().getLength();	    
	    int position = 0, end, start, curLine = 0;
		AttributeSet current;
	    
	    // Do in try catch in case of badLocation
	    try {
	        while (position < totLength) {
	        	curLine++;
	        	
	            end = Utilities.getRowEnd(textArea, position);
	            if (end < 0 || curLine > lineNumber)
	            	// Couldn't find the line type, so return default
	            	return EnumTextLineType.LYRIC;
	            
	            // Continue unless we're at lineNumber
	            if (curLine < lineNumber) {
		            position = end;
	            	continue;
	            }
	            
	            // This is the line ..
    			start = Utilities.getRowStart(textArea, position);
    			current = textArea.getStyledDocument().getCharacterElement(start).getAttributes(); 
    			if (current==null)
	            	return EnumTextLineType.LYRIC;

				if (current.getAttribute(StyleConstants.Bold).toString()=="true")
					return EnumTextLineType.LABEL;
				else if (current.getAttribute(StyleConstants.Italic).toString()=="true")
					return EnumTextLineType.CHORD;
				else
					return EnumTextLineType.LYRIC;
	        }
	    } catch (BadLocationException e) { }
	    
		return EnumTextLineType.LYRIC;
	}

	@Override
	public void setTextAreaControl(Component ta)
	{
		Component[] components = ((JPanel) ta).getComponents();
		Component component = null;
		for (int i = 0; i < components.length; i++) {
			component = components[i];
			if (component instanceof JTextPane)	{
				this.textArea=(JTextPane) component;
				break;
			}
		}			
		
	}

	/**
	 * 	Returns the <code>StyledDocument</code> model used
	 * 	internally to serialize/deserialize the text pane
	 * 	content in the application.
	 * 
	 * 	@return  the <code>StyledDocument</code> used internally
	 * 	to model the text in the text area. 
	 */
	public StyledDocument getStyledDocument()
	{
		return styledDocument;
	}

	/**
	 * 	Sets the internal model to the given <code>StyledDocument</code>.
	 * 
	 * 	@param   styledDocument  the document model created for this class
	 * 	to maintain the text in the text area.
	 */
	public void setStyledDocument(StyledDocument styledDocument)
	{
		this.styledDocument = styledDocument;
	};	
}

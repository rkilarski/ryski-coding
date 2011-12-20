package com.guitariffic.model.enums;

/**
 *	The possible types of text areas envisioned for the left pane
 *	of the application.
 *
 *	<p>The <code>TEXT</code> type is a simple text area using a single
 *	font. The <code>TABLE</code> type would use some sort of table to
 *	display the text for the left side of the application. The 
 *	<code>STYLED</code> type enables multiple styles, sizes and colors
 *	to be applied to the text area. The <code>HTML</code> type should
 *	allow HTML code to be placed into the text pane in order to be
 *	exportable to any web browser.
 *
 * 	@author  Brian Collins
 * 	@version 1.0, Nov 2011
 */
public enum EnumTextAreaType
{
	TEXT, TABLE, STYLED, HTML
}

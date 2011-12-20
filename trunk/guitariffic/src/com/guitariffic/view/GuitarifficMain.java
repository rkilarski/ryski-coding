package com.guitariffic.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;

/**
 * Main entry point for the guitariffic application.  This class first calls a splash screen and then the main application.
 * 
 * @author ryszardkilarski
 * 
 */
public class GuitarifficMain
{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		GuitarifficSplash splash = null;
		splash = new GuitarifficSplash(new ImageIcon(GuitarifficAbout.class.getResource("/com/guitariffic/resources/CWguitariffic3.jpg")), null, 2000);

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GuitarifficApplication window = new GuitarifficApplication();
					window.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

}

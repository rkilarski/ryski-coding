package com.guitariffic.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 * Splash screen for the guitariffic application.
 * 
 * @author ryszardkilarski
 * 
 */
class GuitarifficSplash extends JWindow
{
	private static final long serialVersionUID = 1L;

	public GuitarifficSplash(ImageIcon image, Frame f, int waitTime)
	{
		super(f);
		JLabel l = new JLabel(image);
		l.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(l, BorderLayout.CENTER);
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = l.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				setVisible(false);
				dispose();
			}
		});
		final int pause = waitTime;
		final Runnable closerRunner = new Runnable()
		{
			public void run()
			{
				setVisible(false);
				dispose();
			}
		};
		Runnable waitRunner = new Runnable()
		{
			public void run()
			{
				try
				{
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closerRunner);
				} catch (Exception e)
				{
					e.printStackTrace();
					// can catch InvocationTargetException
					// can catch InterruptedException
				}
			}
		};
		setVisible(true);
		Thread splashThread = new Thread(waitRunner, "SplashThread");
		splashThread.start();
	}
}
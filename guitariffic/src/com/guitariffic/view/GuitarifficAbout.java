package com.guitariffic.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 * About form for the guitariffic application.
 * 
 * @author ryszardkilarski
 * 
 */
public class GuitarifficAbout extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public GuitarifficAbout(Frame frame)
	{
		super(frame);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 410, 361);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - (this.getWidth() / 2), screenSize.height / 2 - (this.getHeight() / 2));

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JLabel lblAbout = new JLabel("");
			lblAbout.setIcon(new ImageIcon(GuitarifficAbout.class.getResource("/resource/CWGuitariffic3.jpg")));
			contentPanel.add(lblAbout);
		}
		{
			JTextPane textPaneAbout = new JTextPane();
			textPaneAbout.setEditable(false);
			textPaneAbout.setFont(new Font("Helvetica", Font.PLAIN, 12));
			textPaneAbout.setText("guitariffic was designed and developed by:\n\nRyszard Kilarski (rkilarski@gmail.com)\nBob Murray\nBrian Collins\nHenry Lee\n\nwith the help and support of Professor Yuting Zhang at Boston University, 2011.");
			textPaneAbout.setEnabled(false);
			textPaneAbout.setVisible(false);
			contentPanel.add(textPaneAbout);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent arg0)
					{
						closeDialog();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private void closeDialog()
	{
		this.dispose();
	}
}

package org.twinbee.costbasis;

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Hello world!
 *
 */
public class App
{

	private static JFileChooser fc;

	private static JPanel mainPanel;

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	private static void createAndShowGUI()
	{
		mainPanel = new JPanel(new BorderLayout());

		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(mainPanel);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			// This is where a real application would open the file.
			System.out.println("Opening: " + file.getName() + ".");
			EtradeFileParser parser = new EtradeFileParser();
			List<TransactionRecord> transactions = parser.parse(file.getAbsolutePath());

			System.out.println(transactions);

			CostBasisCalculator cbc = new CostBasisCalculator();
			String report = cbc.getCostBasisReport(transactions);

			System.out.println(report);
		} else
		{
			System.out.println("Open command cancelled by user.");
		}

	}

	public static void main(String[] args)
	{
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}

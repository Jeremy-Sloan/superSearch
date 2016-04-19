package superSearch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;

@SuppressWarnings("serial")
public class superSearch extends JPanel {
	private JButton button11, button12, button21, button22;
	private JLabel sizeLabel11, sizeLabel12, sizeLabel21, sizeLabel22;
	private JTextArea textarea, textarea2;
	private int width, height;
	private SuperString[] array;
	private JTextField nField, n2Field;
	private String n1, n2;
	private JLabel fileLabel1, fileLabel2;
	private static JPanel masterPanel = new JPanel();
	private static JPanel subPanel1 = new JPanel();
	private static JPanel subPanel2 = new JPanel();

	public superSearch() {
		width = 400;
		height = 600;
		nField = new JTextField("2", 3);
		button11 = new JButton("Alphabetically");
		button12 = new JButton("Occurrence");
		sizeLabel11 = new JLabel("total list size : 0");
		sizeLabel12 = new JLabel("unique list size : 0");
		fileLabel1 = new JLabel("No File Selected.");
		textarea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textarea);

		subPanel1.setLayout(new BorderLayout());
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 3));

		infoPanel.add(button11);
		infoPanel.add(button12);
		infoPanel.add(nField);
		infoPanel.add(fileLabel1);
		infoPanel.add(sizeLabel11);
		infoPanel.add(sizeLabel12);

		subPanel1.add(infoPanel, BorderLayout.NORTH);
		subPanel1.add(scroll, BorderLayout.CENTER);
		subPanel1.setPreferredSize(new Dimension(width, height));

		button12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				if (!loadfile1())
					return;

				Arrays.sort(array, new SuperStringComparator());
				textarea.setText("");
				for (SuperString x : array) {
					textarea.append(x + "\n");
					count++;
				}
				sizeLabel12.setText("unique phrases: " + count);
			}
		});

		button11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderedList<String> tokens = new LinkedOrderedList<String>();
				Scanner scan = null;
				String token = null;
				String antitoken = null;
				int count = 0;
				int count2 = 0;
				JFileChooser chooser = new JFileChooser("../Text");
				int returnvalue = chooser.showOpenDialog(null);
				if (returnvalue != JFileChooser.APPROVE_OPTION) {
					System.err.println("no file selected");
					return;
				}
				File file = chooser.getSelectedFile();
				try {
					scan = new Scanner(file);
				} catch (FileNotFoundException err) {
					System.err.println("File not found");
					return;
				}
				while (scan.hasNext()) {
					token = scan.next().toLowerCase();
					token = token.replaceAll("\\p{P}", "");
					tokens.add(token);
				}
				scan.close();
				textarea.setText("");
				textarea.setCaretPosition(0);
				fileLabel1.setText(file.getName());

				for (String x : tokens) {
					count++;
					if (!x.equals(antitoken)) {
						textarea.append(" " + x + "\n");
						count2++;
					}
					antitoken = x;
				}
				sizeLabel11.setText("total size: " + count);
				sizeLabel12.setText("unique size: " + count2);
			}
		});

		/*
		 * -------------------------- LAB 8 --------------------------
		 */

		width = 400;
		height = 600;
		sizeLabel21 = new JLabel("total list size : 0");
		sizeLabel22 = new JLabel("unique list size : 0");
		button21 = new JButton("Alphabetically");
		button22 = new JButton("Occurrence");
		fileLabel2 = new JLabel("No File Selected.");
		n2Field = new JTextField("2", 3);
		textarea2 = new JTextArea("");
		JScrollPane scroll2 = new JScrollPane(textarea2);

		subPanel2.setLayout(new BorderLayout());
		JPanel upperPanel = new JPanel();

		upperPanel.add(button21);
		upperPanel.add(button22);
		upperPanel.add(n2Field);
		upperPanel.add(fileLabel2);

		upperPanel.add(sizeLabel21);
		upperPanel.add(sizeLabel22);
		subPanel2.add(upperPanel, BorderLayout.NORTH);
		subPanel2.add(scroll2, BorderLayout.CENTER);
		subPanel2.setPreferredSize(new Dimension(width, height));
		upperPanel.setLayout(new GridLayout(2, 3));

		button22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				if (!loadfile2())
					return;

				Arrays.sort(array, new SuperStringComparator());
				textarea2.setText("");
				for (SuperString x : array) {
					textarea2.append(x + "\n");
					count++;
				}
				sizeLabel22.setText("unique phrases: " + count);
			}
		});

		button21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				OrderedList<String> tokens = new LinkedOrderedList<String>();
				Scanner scan = null;
				String token = null;
				String antitoken = null;
				int count = 0;
				int count2 = 0;

				JFileChooser chooser = new JFileChooser("../Text");
				int returnvalue = chooser.showOpenDialog(null);
				if (returnvalue != JFileChooser.APPROVE_OPTION) {
					System.err.println("no file selected");
					return;
				}
				File file = chooser.getSelectedFile();

				try {
					scan = new Scanner(file);
				} catch (FileNotFoundException err) {
					System.err.println("File not found");
					return;
				}
				while (scan.hasNext()) {
					token = scan.next().toLowerCase();
					token = token.replaceAll("\\p{P}", "");
					tokens.add(token);
				}
				scan.close();

				textarea2.setText("");
				textarea2.setCaretPosition(0);
				fileLabel2.setText(file.getName());

				for (String x : tokens) {
					count++;
					if (!x.equals(antitoken)) {
						textarea2.append(" " + x + "\n");
						count2++;
					}
					antitoken = x;
				}
				sizeLabel21.setText("total size: " + count);
				sizeLabel22.setText("unique size: " + count2);
			}
		});

	}

	private boolean loadfile1() {
		CountList<SuperString> list = new LinkedCountList<SuperString>();
		JFileChooser chooser = new JFileChooser("../Text");
		int returnValue = chooser.showOpenDialog(null);
		if (returnValue != JFileChooser.APPROVE_OPTION)
			return false;

		try {
			int count = 0;
			File file = chooser.getSelectedFile();
			fileLabel1.setText(file.getName());
			n1 = nField.getText();
			Integer limit = Integer.parseInt(n1);
			Scanner scan = new Scanner(file);

			// ----------------------------------------------------

			StringBuilder sb = new StringBuilder();
			while (scan.hasNext()) {
				String gorgon = scan.next() + " ";
				gorgon = gorgon.replaceAll("\\p{P}", "").toLowerCase();
				sb.append(gorgon);
			}

			String[] superArray = sb.toString().split(" ");
			String input[] = new String[limit];

			for (int i = 0; i < superArray.length; i++) {
				if ((i + limit) <= superArray.length) {
					for (int j = 0; j < limit; j++) {
						input[j] = superArray[i + j];
					}
					list.add(new SuperString(input));
					count++;
				}
			}
			scan.close();

			// -----------------------------

			sizeLabel11.setText("total phrases: " + count);

		} catch (FileNotFoundException err) {
			System.err.println("File read error");
			return false;
		}

		array = new SuperString[list.size()];
		int i = 0;
		for (SuperString token : list) {
			array[i] = token;
			i++;
		}
		return true;
	}

	private boolean loadfile2() {
		CountList<SuperString> list = new LinkedCountList<SuperString>();
		JFileChooser chooser = new JFileChooser("../Text");
		int returnValue = chooser.showOpenDialog(null);
		if (returnValue != JFileChooser.APPROVE_OPTION)
			return false;

		try {
			int count = 0;
			File file = chooser.getSelectedFile();
			fileLabel2.setText(file.getName());
			n2 = n2Field.getText();
			Integer limit = Integer.parseInt(n2);
			Scanner scan = new Scanner(file);

			// ----------------------------------------------------

			StringBuilder sb = new StringBuilder();
			while (scan.hasNext()) {
				String gorgon = scan.next() + " ";
				gorgon = gorgon.replaceAll("\\p{P}", "").toLowerCase();
				sb.append(gorgon);
			}

			String[] superArray = sb.toString().split(" ");
			String input[] = new String[limit];

			for (int i = 0; i < superArray.length; i++) {
				if ((i + limit) <= superArray.length) {
					for (int j = 0; j < limit; j++) {
						input[j] = superArray[i + j];
					}
					list.add(new SuperString(input));
					count++;
				}
			}
			scan.close();

			// -----------------------------

			sizeLabel21.setText("total phrases: " + count);

		} catch (FileNotFoundException err) {
			System.err.println("File read error");
			return false;
		}

		array = new SuperString[list.size()];
		int i = 0;
		for (SuperString token : list) {
			array[i] = token;
			i++;
		}
		return true;
	}

	// --------------- end of lab 8------------------------------

	public static void main(String[] arg) {
		JFrame frame = new JFrame("Super List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		masterPanel.add(new superSearch());
		masterPanel.add(subPanel1);
		masterPanel.add(subPanel2);
		frame.add(masterPanel);
		frame.pack();
		frame.setVisible(true);
	}

}

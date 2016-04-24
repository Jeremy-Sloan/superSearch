package superSearch;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class superSearch extends JPanel implements ActionListener {
	private int width, height;
	private JButton open0, open1;
	private JRadioButton alpha;
	private TextArea textarea0, textarea1;
	private JLabel label0, label1, label2, label3;
	private SuperString[] array0, array1;
	private JTextField nField;
	private String n;
	private JButton match;

	public superSearch() {
		JPanel upperPanel = new JPanel();
		JPanel subpanel = new JPanel();

		width = 800;
		height = 600;
		nField = new JTextField("2", 3);
		alpha = new JRadioButton("Alphabetical");
		open0 = new JButton("Open FIle");
		open0.addActionListener(this);
		label0 = new JLabel("No file selected");
		open1 = new JButton("Open File");
		open1.addActionListener(this);
		label1 = new JLabel("No file selected");
		label2 = new JLabel("Size of N ");
		label3 = new JLabel("Match Score ");
		match = new JButton("Compute match");
		match.addActionListener(this);

		textarea0 = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		textarea1 = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);

		setLayout(new BorderLayout());
		subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));

		textarea0.setPreferredSize(new Dimension(width / 2, height * 3 / 5));
		textarea1.setPreferredSize(new Dimension(width / 2, height * 3 / 5));
		setPreferredSize(new Dimension(width, height));

		upperPanel.add(alpha);
		upperPanel.add(open0);
		upperPanel.add(label0);
		upperPanel.add(open1);
		upperPanel.add(label1);
		upperPanel.add(nField);
		upperPanel.add(label2);
		upperPanel.add(match);
		upperPanel.add(label3);
		subpanel.add(textarea0);
		subpanel.add(textarea1);

		add(subpanel, BorderLayout.CENTER);
		add(upperPanel, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == match) {
			double giga = (MatchTools.match(array0, array1));
			String gorgon = String.valueOf(giga);
			label3.setText(gorgon + " ");
			return;
		}

		JFileChooser chooser = new JFileChooser("../Text");
		int returnValue = chooser.showOpenDialog(null);
		if (returnValue != JFileChooser.APPROVE_OPTION)
			return;
		File file = chooser.getSelectedFile();
		String filename = file.getName();

		SuperString[] array = loadfile(file);

		if (!alpha.isSelected())
			Arrays.sort(array, new SuperStringComparator());
		if (event.getSource() == open0) {
			array0 = array;
			label0.setText(filename);
			textarea0.setText("");
			StringBuilder sb = new StringBuilder();
			for (SuperString token : array0)
				sb.append(token + "\n");
			textarea0.setText(sb.toString());
		}

		if (event.getSource() == open1) {
			array1 = array;
			label1.setText(filename);
			textarea1.setText("");
			StringBuilder sb = new StringBuilder();
			for (SuperString token : array1)
				sb.append(token + "\n");
			textarea1.setText(sb.toString());
		}

	}

	private SuperString[] loadfile(File file) {

		BinaryCountTree<SuperString> tree = new BinaryCountTree<SuperString>();
		try {
			Scanner scan = new Scanner(file);

			// --------------------------------------------------

			n = nField.getText();
			Integer limit = Integer.parseInt(n);

			StringBuilder sb = new StringBuilder();
			while (scan.hasNext()) {
				String gorgon = scan.next() + " ";
				gorgon = gorgon.replaceAll("\\p{P}", "").replaceAll("^[ \r\t\n]*$", "").toLowerCase();
				sb.append(gorgon);
			}

			String[] superArray = sb.toString().split(" ");
			String input[] = new String[limit];

			for (int i = 0; i < superArray.length; i++) {
				if ((i + limit) <= superArray.length) {
					for (int j = 0; j < limit; j++) {
						input[j] = superArray[i + j];
					}
					tree.add(new SuperString(input));
				}
			}

			// ---------------------------------------------
			scan.close();
		} catch (FileNotFoundException err) {
			System.err.println("File read error");
			return null;
		}

		SuperString[] array = new SuperString[tree.size()];
		int i = 0;
		for (SuperString token : tree) {
			array[i] = token;
			i++;
		}
		return array;
	}

	public static void main(String[] arg) {
		JFrame frame = new JFrame("superSearch");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new superSearch());
		frame.pack();
		frame.setVisible(true);
	}
}
package fractalIsland;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;

import java.util.Random;

@SuppressWarnings("serial")
public class FractalIsland extends JPanel {
	private Queue<Point2D.Double> points;
	private double alpha;
	private int width, height;
	private int N;
	private int depth;
	private Random rnd;

	public FractalIsland(int n, int d) {
		N = n;
		depth = d;
		width = 500;
		height = 500;
		points = new LinkedListQueue<Point2D.Double>();
		alpha = 0.24;
		rnd = new Random();
		double cx = width / 2.0;
		double cy = height / 2.0;
		double radius = 0.8 * cx;
		double theta;
		for (int i = 0; i < N; i++) {
			theta = (2 * Math.PI * i) / N;
			double x = cx + radius * Math.cos(theta);
			double y = cy - radius * Math.sin(theta);
			points.add(new Point2D.Double(x, y));
		}
		setPreferredSize(new Dimension(width, height));
		repaint();
		fractalize();
		setBackground(Color.white);

	}

	private void fractalize() {
		for (int i = 0; i < depth; i++) {
			int size = points.size();
			for (int j = 0; j < size; j++) {
				Point2D.Double A = points.remove();
				Point2D.Double B = points.element();
				points.add(A);
				points.add(midPoint(A, B));
			}
		}
		repaint();
	}

	private Point2D.Double midPoint(Point2D.Double A, Point2D.Double B) {
		double x0 = A.getX();
		double y0 = A.getY();
		double x1 = B.getX();
		double y1 = B.getY();
		double xmid = (x0 + x1) / 2;
		double ymid = (y0 + y1) / 2;

		int sign = rnd.nextBoolean() ? 1 : -1;

		double x = xmid + sign * alpha * (y0 - y1);
		double y = ymid + sign * alpha * (x1 - x0);
		return new Point2D.Double(x, y);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int size = points.size();
		for (int i = 0; i < size; i++) {
			Point2D.Double A = points.remove();
			Point2D.Double B = points.element();
			int x0 = (int) A.getX();
			int y0 = (int) A.getY();
			int x1 = (int) B.getX();
			int y1 = (int) B.getY();
			g.drawLine(x0, y0, x1, y1);
			points.add(A);
		}
	}

	public static void main(String[] arg) {
		JFrame frame = new JFrame("Fractal Island");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mundo = new JPanel();
		JPanel submundo1 = new JPanel();
		JPanel submundo2 = new JPanel();
		JTextField field1 = new JTextField("5");
		JTextField field2 = new JTextField("5");

		frame.add(mundo);
		mundo.add(submundo1);
		mundo.add(submundo2);

		submundo1.add(new FractalIsland(5, 5));

		JButton button1 = new JButton("INITIALIZE");
		JLabel question = new JLabel("Size of N");
		JLabel depth = new JLabel("Depth");

		submundo2.add(question);
		submundo2.add(field1);
		submundo2.add(depth);
		submundo2.add(field2);
		submundo2.add(button1);

		frame.pack();
		frame.setVisible(true);

		mundo.setLayout(new GridBagLayout());
		mundo.setBackground(Color.white);
		submundo2.setLayout(new BoxLayout(submundo2, BoxLayout.Y_AXIS));
		submundo1.setBackground(Color.white);
		submundo2.setBackground(Color.white);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submundo1.removeAll();
				submundo1.updateUI();
				String field1text = field1.getText();
				int d = Integer.parseInt(field1text);
				String field2text = field2.getText();
				int f = Integer.parseInt(field2text);
				submundo1.add(new FractalIsland(d, f));
			}
		});
	} // main
} // class
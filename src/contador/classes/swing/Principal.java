package contador.classes.swing;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TaskBar;
import org.eclipse.swt.widgets.TaskItem;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Display display;
	public static Shell shell;

	static TaskItem getTaskBarItem() {
		TaskBar bar = display.getSystemTaskBar();
		if (bar == null)
			return null;
		TaskItem item = bar.getItem(shell);
		if (item == null)
			item = bar.getItem(null);
		return item;
	}

	public static void main(String[] args) {

		display = new Display();
		shell = new Shell(display);
		Principal frame = new Principal();
		frame.setVisible(true);
		
		
		
	}

	public Principal() {

		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1150, 650, 289, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(new Cronometro());

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/clock.png"));
		



	}
	
	public static int getOne(){
		return 1;
	}
}

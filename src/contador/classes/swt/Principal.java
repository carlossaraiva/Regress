package contador.classes.swt;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TaskBar;
import org.eclipse.swt.widgets.TaskItem;


public class Principal {

	protected static Shell shlContador;
	public static Display display;

	static TaskItem getTaskBarItem () {
		TaskBar bar = display.getSystemTaskBar();
		
		if (bar == null) return null;
		TaskItem item = bar.getItem(shlContador);
		
		if (item == null) item = bar.getItem(null);
		return item;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Principal window = new Principal();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlContador.open();
		shlContador.layout();
		while (!shlContador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlContador = new Shell();
		shlContador.setSize(313, 199);
		shlContador.setText("Contador");
		shlContador.setLayout(null);
		Cronometro cronometro = new Cronometro(shlContador, SWT.NONE);
		cronometro.setBounds(0, 0, 303, 165);
	}
	

		


}


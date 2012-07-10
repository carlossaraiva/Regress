package contador.classes.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TaskItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import contador.classes.engine.contador;

public class Cronometro extends Composite {

	private Button btnStart;
	private TaskItem item;
	private Text text;
	private static contador contador;
	private Thread thread;
	private Runnable runnable;
	private int state;

	/**
	 * 
	 * @param parent
	 * @param style
	 */
	public Cronometro(Composite parent, int style) {
		super(parent, style);
		state = 0;
		text = new Text(this, SWT.RIGHT);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(state == 0 && (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR)){
					state = 1 ;
					startClock();
				}
			}
		});
		text.setToolTipText("Digite o valor a ser contado.");
		text.setFont(SWTResourceManager.getFont("Segoe UI", 62, SWT.NORMAL));
		text.setBounds(0, 0, 298, 120);
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		btnStart = new Button(this, SWT.NONE);
		btnStart.setBounds(10, 126, 75, 25);
		btnStart.setText("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if(state == 0){
					state = 1;
					startClock();					
				}
			}
		});

		item = Principal.getTaskBarItem();
		if (item != null) {
			item.setProgressState(SWT.INDETERMINATE);
		}		
		setRunnable();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	private void startClock(){
		
		item.setProgressState(SWT.NORMAL);
		contador = new contador(Integer.parseInt(text.getText()));
		thread = new Thread(runnable);
		thread.start();
	}

	private void setRunnable() {
		
		runnable = new Runnable() {
			@Override
			public void run() {
				try {
					while (contador.getTempo() >= 0) {
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if (!thread.isInterrupted()) {
									contador.setTempo();
									text.setText(String.valueOf(contador.getTempo()));
									item.setProgress(contador.getProgresso());
								}
								if (contador.getTempo() == 0) {
									item.setProgressState(SWT.INDETERMINATE);
									state = 0;
								}
							}
						});
						Thread.sleep(1000);
					}
					thread.interrupt();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
	}
}

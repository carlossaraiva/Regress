package contador.classes.swt;
import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TaskItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Cronometro extends Composite implements Runnable {

	private Button btnStart;
	private TaskItem item;
	private Timer timer;
	private Display display;
	private Text text;

	private static int i;
	private static int j;
	private static int tempo;
	private static double progresso;
	private static double razao;
	private Runnable run;

	private Thread thread;

	public Cronometro(Composite parent, int style) {
		super(parent, style);

		text = new Text(this, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Segoe UI", 62, SWT.NORMAL));
		text.setBounds(0, 0, 298, 120);

		btnStart = new Button(this, SWT.NONE);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				item.setProgressState(SWT.NORMAL);
				setClock();
			}
		});
		btnStart.setBounds(10, 126, 75, 25);
		btnStart.setText("Start");
		
		item = Principal.getTaskBarItem();
		if(item != null){
			item.setProgressState(SWT.INDETERMINATE);
		}
		j = 0;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void setClock() {

		try {
			tempo = Integer.parseInt(text.getText());
			i = tempo;
			progresso = 0;
			razao = 100/(double)tempo;
			System.out.println(razao);
		} catch (IllegalArgumentException e) {
			e.getStackTrace();
			System.err.println("Campo nulo!");

		}

		run = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				System.out.println("@!");
				try {
					while (true) {
						
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								text.setText(Integer.toString(i));
								
							  item.setProgress((int)(progresso+=razao)); 
							  //System.out.println(progresso+=razao/tempo);
						
								if(Integer.parseInt(text.getText()) == 0){
									
									thread.interrupt();
									item.setProgressState(SWT.INDETERMINATE);
								}
								i--;
								j++;
							}
						});
						Thread.sleep(1000);

					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		thread = new Thread(run);
		thread.start();
	}

}
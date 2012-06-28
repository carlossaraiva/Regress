package contador.classes.engine;

public class contador  {

	private static int i;
	private int tempo;
	private static double progresso;
	private double razao;

	public contador(int tempo){
		this.tempo = tempo;
		contador.progresso = 0;
		contador.i = tempo;
		this.razao = 100 / (double) tempo;		
	}

	public void setTempo() {
			contador.i--;
			progresso += razao;
	}
	
	public int getTempo(){
		return contador.i;
	}
	
	public int getProgresso(){
		
		return (int) progresso;
		
	}
	
}
/*
 * 
 * try { tempo = Integer.parseInt(text.getText()); i = tempo; progresso = 0;
 * razao = 100 / (double) tempo; } catch (IllegalArgumentException e) {
 * e.getStackTrace(); System.err.println("Campo nulo!"); }
 * 
 * run = new Runnable() {
 * 
 * @Override public void run() {
 * 
 * try { while (true) {
 * 
 * Display.getDefault().asyncExec(new Runnable() { public void run() {
 * text.setText(Integer.toString(i));
 * 
 * item.setProgress((int) (progresso += razao));
 * 
 * if (Integer.parseInt(text.getText()) == 0) {
 * 
 * thread.interrupt(); item.setProgressState(SWT.INDETERMINATE); } i--; } });
 * Thread.sleep(1000);
 * 
 * } } catch (InterruptedException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * } }; thread = new Thread(run); thread.start(); } }
 */
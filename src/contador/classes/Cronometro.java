package contador.classes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;


public class Cronometro extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 2409311176454918183L;
	private JTextField textCronometro;
	private int i;

	private javax.swing.Timer t;
	private JButton btnStart;
	
	public Cronometro() {
		setForeground(new Color(0, 0, 0));
		setLayout(null);
		
		textCronometro = new JTextField();
		textCronometro.setForeground(new Color(0, 0, 0));
		textCronometro.setHorizontalAlignment(SwingConstants.RIGHT);
		textCronometro.setFont(new Font("Arial", Font.PLAIN, 80));
		textCronometro.setBounds(10, 11, 260, 111);
		add(textCronometro);
		textCronometro.setColumns(10);
		
		btnStart = new JButton("Start!");
		btnStart.setBounds(10, 133, 89, 23);
		add(btnStart);
		btnStart.addActionListener(this);
		
		t = new javax.swing.Timer(1000, (this));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnStart){
			System.out.println("Botão Start pressionado.");
			i = Integer.parseInt(textCronometro.getText());
			System.out.println(i);
			t.start();
			
		}
						
		textCronometro.setText("" + i);
		
		if(Integer.parseInt(textCronometro.getText()) == 0){
			System.out.println("Chegou no zero!");
			t.stop();
		}
		i = i - 1;
		
		
	}
}

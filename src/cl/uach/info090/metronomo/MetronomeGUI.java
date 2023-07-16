package cl.uach.info090.metronomo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author Sebastian Montecinos B.
 * 
 */
public class MetronomeGUI extends javax.swing.JFrame implements java.awt.event.ActionListener{
	/**
	 * Clase principal
	 * Contiene el main y la instancia del metrónomo
	 */
	
	private static final long serialVersionUID = 1L;
	
	private static MetronomeGUI gui;
	private SimpleMetronomeDisplay panel;
	private JButton btn,btnStop;
	private JLabel lbl , lbl2;
	private JComboBox<String> combo;
	
	private Pulse pulse;
	
	/**
	 * Constructor de la clase singletone MetronomeGUI, aqui se crea la ventana y todos sus componentes
	 */
	private MetronomeGUI() {
		super("Metrónomo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color (211, 239, 245));
		setResizable(false);
		setBounds(100,100,1000,500); //Tamaño ventana
		setLayout(null);
		
		//Se crea el Panel que va dentro de la ventana
		panel = new SimpleMetronomeDisplay();
		panel.setBounds(100,100,800,300);
		add(panel);
		
		//Texto
		lbl = new JLabel("Selecciona los BPM y apreta Play :");
		lbl.setBounds(200,50,210,20);
	    add(lbl);

	    //Opciones de BPM disponibles
		combo = new JComboBox<String>();
		for(int f=40;f<=220;f+=5) combo.addItem(String.valueOf(f));
		((JLabel)combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);//Para centrar los numeros del comboBox
		combo.setBounds(430,50,100,20);
	    add(combo);
		
	    //Para ver BPM actual
 	    lbl2 = new JLabel("BPM actual : " + (String)combo.getItemAt(4));
	    lbl2.setBounds(650,50,100,20);
	    add(lbl2);
	    
	    //Boton Play para actualizar BPM
	    btn = new JButton("Play");
	    btn.setBounds(560,40,65,20);
	    btn.addActionListener(this);
	    add(btn);
	    
	    //Boton Stop para que deje de sonar
	    btnStop = new JButton("Stop");
	    btnStop.setBounds(560,60,65,20);
	    btnStop.addActionListener(this);
	    add(btnStop);
	    
	    //Se crea el pulso
	    pulse = new Pulse(panel);
	    pulse.start();
	    
	    this.setVisible(true);
	}
	
	/**
	 * Método sobreescrito de la libreria ActionListener para diferenciar las acciones que realiza cada boton
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn) {
			String txt = (String)combo.getSelectedItem();
			lbl2.setText("BPM actual : "+txt);
			pulse.setBpm(Integer.parseInt(txt));
			pulse.prender();
		}
		if(e.getSource() == btnStop) {
			pulse.apagar();
		}

	}
	
	
	/**
	 * Retorna la instancia la clase MetronomeGUI
	 * @return gui
	 */
	public static MetronomeGUI getInstance() {
		if(gui == null) gui = new MetronomeGUI();
		return gui;
	}
	

	public static void main(String[] args){
		MetronomeGUI.getInstance();

		
	}
}

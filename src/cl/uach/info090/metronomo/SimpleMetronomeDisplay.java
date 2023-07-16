package cl.uach.info090.metronomo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Sebastian Montecinos B.
 * 
 */
public class SimpleMetronomeDisplay extends javax.swing.JPanel implements MetronomeDisplay{

	/**
	 * Clase donde se crea el panel (JPanel), el cual contiene la representacion grafica y sonora del metronomo
	 */
	private static final long serialVersionUID = 1L;
	private int num;
	private SClip audio;

	
	/**
	 * Constructor, inicializa valores de los atributos
	 * num = 0 significa que todos los ciruclos parten sin pintar
	 * 
	 */
	public SimpleMetronomeDisplay() {
		//super();
		num = 0;
		audio = new SClip("data/sonido.wav");
	}
	
	/**
	 * Método sobreescrito de la libreria Graphics
	 * Pinta y dibuja los circulos correspondientes
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(new Color (188, 227, 235));
		g2d.fillRect(0,0,getWidth(),getHeight());
		g2d.setColor(new Color (38, 130, 148));
		
		if(num == 0) {
			//todos prendidos
			g2d.drawOval(40, 50, 200, 200);
			g2d.drawOval(300, 50, 200, 200);
			g2d.drawOval(560, 50, 200, 200);
		}
		if(num == 1) {
			//prendido el de la izquierda
			g2d.fillOval(40, 50, 200, 200);
			g2d.drawOval(300, 50, 200, 200);
			g2d.drawOval(560, 50, 200, 200);
		}
		if(num == 2) {
			//prendido el de al medio
			g2d.drawOval(40, 50, 200, 200);
			g2d.fillOval(300, 50, 200, 200);
			g2d.drawOval(560, 50, 200, 200);
		}
		if(num == 3) {
			//prendido el de la derecha
			g2d.drawOval(40, 50, 200, 200);
			g2d.drawOval(300, 50, 200, 200);
			g2d.fillOval(560, 50, 200, 200);
		}
	}
	
	/**
	 * Método que actualiza el panel
	 */
	public void redibujar() {
		repaint();
	}
	
	
	/**
	 * Método responsable de emitir el sonido e ir definiendo cuales circulos pintar
	 */
	@Override
	public void tick() {
		audio.play();
		if(num >= 3) num = 1;
		else num++;
	}
	
	/**
	 * Método para que todos los circulos vuelvan al estado original (sin pintar)
	 * 
	 */
	public void resetNum() {
		this.num = 0;
	}

}


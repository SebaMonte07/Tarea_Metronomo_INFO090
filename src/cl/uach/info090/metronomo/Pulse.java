package cl.uach.info090.metronomo;

/**
 * @author Sebastian Montecinos B.
 * 
 */

public class Pulse extends java.lang.Thread{
	
	/**
	 * Esta clase es un hilo, el cual estara en constante ejecución
	 * Aqui se define el pulso que llevara (los Beats Por Minuto)
	 * Cada 60/bpm segundos, se llama al metodo tick() y redibujar() para actualizar la visualizacion grafica del metronomo
	 */

	private int bpm;
	private boolean flag;
	private MetronomeDisplay metronomeDisplay;
	
	/**
	 * Constructor, fija los valores iniciales
	 * @param o de tipo MetronomeDisplay
	 */
	public Pulse(MetronomeDisplay o) {
		bpm = 60;
		flag = false;
		metronomeDisplay = o; //panel
		
	}
	
	/**
	 * Método sobreescrito de la libreria Thread
	 * Aqui se espera el tiempo fijado entre cada tick (el tiempo depende del atributo bpm)
	 */
	@Override
	public void run() {
		//Aqui llamar al tick cada X segundos
		while(true) {
			if(flag) {
				((SimpleMetronomeDisplay) metronomeDisplay).tick();
				((SimpleMetronomeDisplay) metronomeDisplay).redibujar();
				esperar((double)60/bpm);

			}
			else {
				((SimpleMetronomeDisplay) metronomeDisplay).resetNum();
				((SimpleMetronomeDisplay) metronomeDisplay).redibujar();
			}
		}
		
	}

	/**
	 * Método para que el metrónomo comience a sonar
	 */
	public void prender() {
		if(flag == false) flag = true;
	}	
	
	/**
	 * Método para que el metrónomo deje de sonar
	 */
	public void apagar() {
		if(flag == true) flag = false;
	}
	
	
	/**
	 * Metodo detiene el Thread por una cierta cantidad de segundos
	 * @param segs El parametro segs determina cuando segundos habrán de pausa
	 */
	public static void esperar(double segs){
        try {
            Thread.sleep((long) (segs * 1000));
         } catch (Exception e) {
            System.out.println(e);
         }
    } 

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

	public MetronomeDisplay getMetronomeDisplay() {
		return metronomeDisplay;
	}
	
	
	
}

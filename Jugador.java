package uf1290.serilizar.juegoDadosArray;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Jugador implements Serializable, Comparable<Jugador>{


	private static final long serialVersionUID = -8085008980064088765L;
	
	private String nombre;
	private static int contadorJugadores;
	private final int UID;
	private int partidasGanadas;
	
	
	public Jugador(String nombre) {
		UID = contadorJugadores++;
		partidasGanadas=0;
		this.nombre=nombre;
	}


	public int lanzar( List<Dado> dados) {
		int resultado=0;
		for (int i = 0; i < dados.size(); i++) {
			resultado += ( dados.get(i) ).lanzar();
		}
		return resultado;
	}
	

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the partidasGanadas
	 */
	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public int getUID() {
		return UID;
	}


	/**
	 * @param partidasGanadas the partidasGanadas to set
	 */
	public void anyadirPartidaGanada() {
		this.partidasGanadas++;
	}


	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", UID=" + UID + ", partidasGanadas=" + partidasGanadas + "]";
	}
	
	  /* 
     * Default serializable fields of a class are defined to be 
     * the non-transient and non-static fields. So, we have to 
     * write and read the static field separately.
     */
    private void writeObject(ObjectOutputStream oos)
        throws IOException 
    {
        oos.defaultWriteObject();
        oos.writeInt(contadorJugadores);
    }

    private void readObject(ObjectInputStream ois)
    throws ClassNotFoundException, IOException 
    {
        ois.defaultReadObject();
        contadorJugadores = ois.readInt();
        
    }


    
	@Override
	public int compareTo(Jugador otroJugador) {
		
		if( this.partidasGanadas > otroJugador.partidasGanadas ) {
			return 1;
		}
		else if (this.partidasGanadas < otroJugador.partidasGanadas) {
			return -1;
		}
		else {
			return 0;
		}
		
		//return this.partidasGanadas - otroJugador.partidasGanadas;
	}
	
	
}

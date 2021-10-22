package uf1290.serilizar.juegoDadosArray;

import java.util.List;

public class Partida {

	private int rondas;
	private List<Jugador> jugadores;
	private List<Dado> dados;
	private int [][] tTiradas;
	private int jugadorGanador;
	
	public Partida(List<Jugador> jugadores,  List<Dado> dados, int rondas) {
		this.jugadores=jugadores;
		this.dados=dados;
		this.rondas = rondas;
		tTiradas = new int[rondas][jugadores.size()];
	}

	public void jugar() {
//		Para cada ronda
		for (int ronda = 0; ronda < rondas; ronda++) {
//			Para cada jugador
			for (int jugador = 0; jugador < jugadores.size(); jugador++) {
//				Apuntamos su tirada
				tTiradas[ronda][jugador] = jugadores.get(jugador).lanzar(dados);
			}
		}
		//calculamos el ganador
		calcularGanador();
		//le apuntamos la partida ganada al jugador correspondiente
		jugadores.get(jugadorGanador).anyadirPartidaGanada();
	}
	
	public void mostrarTabla() {
//		Para cada ronda
		for (int ronda = 0; ronda < rondas; ronda++) {
//			Para cada jugador
			for (int jugador = 0; jugador < jugadores.size(); jugador++) {
//				mostramos su tirada
//				System.out.printf( "%4d" ,  "UID " + jugadores.get(jugador) );
				System.out.printf( "%4d" , tTiradas[ronda][jugador] );
			}
			System.out.println();
		}
	}
	
	private void calcularGanador() {
		
		jugadorGanador=0;
		int maxPuntos=0;
//		Para cada jugador
		for (int jugador = 0; jugador < jugadores.size(); jugador++) {
			int puntosJugador=0;
//			Para cada ronda
			for (int ronda = 0; ronda < rondas; ronda++) {
				//calculamos la suma de las rondas de cada jugador
				puntosJugador +=  tTiradas[ronda][jugador];
			}
			if (puntosJugador > maxPuntos) {
				jugadorGanador = jugador; // apuntamos el jugador momentáneamente con más puntos
				maxPuntos=puntosJugador; // actualizamos el nuevo máximo
			}
		}
	}


	/**
	 * @return the rondas
	 */
	public int getRondas() {
		return rondas;
	}

	/**
	 * @return the jugadorGanador
	 */
	public int getJugadorGanador() {
		return jugadorGanador;
	}
	
	
}

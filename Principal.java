package uf1290.serilizar.juegoDadosArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Principal {

	private static  void escribirObjeto(Object objeto, String nombreFichero) {

		try ( ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(nombreFichero) )	)
		{
			oos.writeObject(objeto);
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		} 
	}

	private static Object leerObjeto(String nombreFichero) {

		File f = new File(nombreFichero);
		Object objeto = null;

		if(f.exists()) {

			try ( ObjectInputStream ois = new ObjectInputStream( new FileInputStream(f) ) )
			{
				objeto = ois.readObject();

			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			} 
		}
		else {
			System.out.println("El archivo no existe");
		}

		return objeto;
	}


	public static void main(String[] args) {
		
		String nombreFichero = "jugadores.ser";
		
		int numDados = 1;
		int rondas = 2;
		int nuevosJugadores = 0;
		
		
//		Cargamos los datos de los jugadores desde el fichero
		List<Jugador> jugadoresFichero = ( ArrayList<Jugador> ) leerObjeto(nombreFichero);
		
		
//		Añadimos nuevos jugadores
		List<Jugador> jugadores = new ArrayList<>();
		for (int i = 0; i < nuevosJugadores; i++) {
			jugadores.add( new Jugador("Jugador"+i) );
		}
		
		List<Dado> dados = new ArrayList<Dado>();
		for (int i = 0; i < numDados; i++) {
			dados.add(new Dado());
		}
		
		
//		Si había jugadores en el fichero cargado
		if(jugadoresFichero != null) {
//			mostramos lo que se ha cargado
			jugadoresFichero.forEach(jugador -> System.out.println(jugador));
//			añadimos los jugadores cargados al arrayList de memoria transitoria "jugadores"
			jugadores.addAll(jugadoresFichero);
		}
		
		Partida partida = new Partida(jugadores, dados, rondas);
		
		partida.jugar();
		partida.mostrarTabla();
		System.out.println("El jugador ganador es: "+ jugadores.get( partida.getJugadorGanador() ));
		
//		Guardamos los datos de todos los jugadores a fichero
		escribirObjeto(jugadores,nombreFichero);
		
		Collections.sort(jugadores);
//		Collections.reverse(jugadores);
		
		for (Jugador jugador : jugadores) {
			System.out.println(jugador);
		}
		
		
		
	}

	

}

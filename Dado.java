package uf1290.serilizar.juegoDadosArray;

public class Dado {

	public int lanzar() {
		int max = 6;
		int min = 1;
		int range = max - min + 1;
		int random = (int)(Math.random() * range) + min;
		return random;
	}
	
	public Dado() {
		
	}

	
}

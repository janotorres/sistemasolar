package br.com.furb.sistemasolar.objetos;

import java.util.ArrayList;
import java.util.List;

import br.com.furb.sistemasolar.enumerations.Textura;

import com.sun.opengl.util.GLUT;

public class Astro {

	private Textura textura;

	private GLUT glut;
	
	private List<Astro> filhos;
	
	public Astro(Textura textura) {
		this.filhos = new ArrayList<Astro>();
		this.textura = textura;
		
	}

	public void desenha() {
		
	}
	
	public void addFilhos(Astro astro){
		this.filhos.add(astro);
	}
	
	public List<Astro> getFilhos(){
		return this.filhos;
	}
}

package br.com.furb.sistemasolar.enumerations;


public enum Textura {
	SOL(0),
	MERCURIO(1),
	VENUS(2),
	TERRA(3),
	MARTE(4), 
	LUA(5);
	
	private int astro;
	
	private Textura(int astro) {
		this.astro = astro;
	}

	public int getAstro() {
		return astro;
	}
	
}

package br.com.furb.editorgrafico.enumerations;

/** Enumerador utilizado para setar a cor do objeto gráfico.*/
public enum Cor {

	BRANCO(255,255,255),
	AZUL(0,0,255),
	VERMELHO(255,0,0),
	VERDE(0,255,0),
	AMARELO(255,255,0),
	MAGENTA(255,0,255),
	CIANO(0,255,255),
	PRETO(0,0,0);
	
	private float red;
	private float green;
	private float blue;

	private Cor(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public float getRed() {
		return red;
	}
}

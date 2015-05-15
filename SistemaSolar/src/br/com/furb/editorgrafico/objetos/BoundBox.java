package br.com.furb.editorgrafico.objetos;

import javax.media.opengl.GL;

import br.com.furb.editorgrafico.enumerations.Cor;

/** Classe utilizada para obter o BoundBox do objeto e desenhá-lo ao redor
 * do objeto quando este estiver selecionado.*/
public class BoundBox {

	private float xMin;
	
	private float xMax;
	
	private float yMin;
	
	private float yMax;

	public BoundBox(float xMin, float xMax, float yMin, float yMax) {
		super();
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	
	public float getxMin() {
		return xMin;
	}

	public void setxMin(float xMin) {
		this.xMin = xMin;
	}

	public float getxMax() {
		return xMax;
	}

	public void setxMax(float xMax) {
		this.xMax = xMax;
	}

	public float getyMin() {
		return yMin;
	}

	public void setyMin(float yMin) {
		this.yMin = yMin;
	}

	public float getyMax() {
		return yMax;
	}

	public void setyMax(float yMax) {
		this.yMax = yMax;
	}

	public boolean contain(Ponto ponto){
		return true;
	}
	
	public float getCentroX(){
		return ((xMax - xMin) /2) + xMin;
	}
	
	public float getCentroY(){
		return ((yMax - yMin) /2) + yMin;
	}


	public void desenha(GL gl) {
		gl.glColor3f(Cor.CIANO.getRed(), Cor.CIANO.getGreen(), Cor.CIANO.getBlue());
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2d(xMin, yMax);
		gl.glVertex2d(xMin, yMin);
		gl.glVertex2d(xMax, yMin);
		
		gl.glVertex2d(xMax, yMax);
		gl.glEnd();
	}
	
}

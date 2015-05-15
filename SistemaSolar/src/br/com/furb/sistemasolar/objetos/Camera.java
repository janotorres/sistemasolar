package br.com.furb.sistemasolar.objetos;

/**Câmera do Editor Gráfico, classe utilizada para setar os valores do
 * Ortho2D do OpenGL.*/
public class Camera  {

	private double ortho2D_minX;

	private double ortho2D_maxX;

	private double ortho2D_minY;

	private double ortho2D_maxY;
	
	public Camera(Mundo mundo) {
		this.ortho2D_minX = 0f;
		this.ortho2D_maxX = 400f;
		this.ortho2D_minY = 400f;
		this.ortho2D_maxY = 0f;
	}

	public double getOrtho2D_minX() {
		return ortho2D_minX;
	}

	public void setOrtho2D_minX(float ortho2d_minX) {
		ortho2D_minX = ortho2d_minX;
	}

	public double getOrtho2D_maxX() {
		return ortho2D_maxX;
	}

	public void setOrtho2D_maxX(float ortho2d_maxX) {
		ortho2D_maxX = ortho2d_maxX;
	}

	public double getOrtho2D_minY() {
		return ortho2D_minY;
	}

	public void setOrtho2D_minY(float ortho2d_minY) {
		ortho2D_minY = ortho2d_minY;
	}

	public double getOrtho2D_maxY() {
		return ortho2D_maxY;
	}

	public void setOrtho2D_maxY(float ortho2d_maxY) {
		ortho2D_maxY = ortho2d_maxY;
	}
}

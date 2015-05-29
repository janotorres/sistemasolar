package br.com.furb.sistemasolar.objetos;

/**Câmera do Sistema Solar.*/
public class Camera  {

	private double xEye, yEye, zEye;

	private double xCenter, yCenter, zCenter;
	
	private double fovy, near, far; 

	public double getyCenter() {
		return yCenter;
	}

	public void setyCenter(double yCenter) {
		this.yCenter = yCenter;
	}

	public double getyEye() {
		return yEye;
	}

	public void setyEye(double yEye) {
		this.yEye = yEye;
	}

	public double getxEye() {
		return xEye;
	}

	public void setxEye(double xEye) {
		this.xEye = xEye;
	}

	public double getxCenter() {
		return xCenter;
	}

	public void setxCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public double getzEye() {
		return zEye;
	}

	public void setzEye(double zEye) {
		this.zEye = zEye;
	}

	public double getzCenter() {
		return zCenter;
	}

	public void setzCenter(double zCenter) {
		this.zCenter = zCenter;
	}

	public double getFovy() {
		return fovy;
	}

	public void setFovy(double fovy) {
		this.fovy = fovy;
	}

	public double getFar() {
		return far;
	}

	public void setFar(double far) {
		this.far = far;
	}

	public double getNear() {
		return near;
	}

	public void setNear(double near) {
		this.near = near;
	}
	
	
}

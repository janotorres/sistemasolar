package br.com.furb.sistemasolar.objetos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import br.com.furb.sistemasolar.enumerations.Textura;
import br.com.furb.sistemasolar.texture.Texture;

public class Astro {

	private static final int SLICES = 40;

	private static final int STACKS = 40;

	private Textura textura;

	private List<Astro> filhos;

	private Double x;

	private double raio;

	private Double y;

	private Double z;

	private Texture[] texture;

	private double angulo = 0d;

	private boolean rotaciona;

	private Ponto position = new Ponto(0, 0, 0);

	public Astro(Textura textura) {
		this.filhos = new ArrayList<Astro>();
		this.textura = textura;
	}

	public Astro(Textura textura, float raio, double x, double y, double z) {
		this(textura, raio, x, y, z, true);
	}

	public Astro(Textura textura, float raio, double x, double y, double z,
			boolean rotaciona) {
		this.textura = textura;
		this.filhos = new ArrayList<Astro>();
		this.raio = raio;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotaciona = rotaciona;
	}

	public void desenha(GL gl, GLU glu, GLUquadric quadric)
			throws IOException {
		gl.glPushMatrix();
		gl.glBindTexture(GL.GL_TEXTURE_2D,
				getTexture()[getTextura().getAstro()].getTexID()[0]);
		Double x = this.x;
		Double z = this.z;
		if (rotaciona) {
			x= retornaX(angulo , this.x + 5);
			z= retornaY(angulo , this.x + 5);
			incrementaAngulo();
		}
		gl.glTranslatef(x.floatValue(), y.floatValue(), z.floatValue());
		position.setX(x.floatValue());
		position.setY(y.floatValue());
		position.setZ(z.floatValue());
	
		glu.gluSphere(quadric, raio, SLICES, STACKS);
		gl.glPopMatrix();
	}
	
	private void incrementaAngulo() {
		this.angulo += 10;
		if (angulo > 360){
			this.angulo = 0;
		}		
	}

	public double retornaX(double angulo, double raio) {
		return (raio * Math.cos(Math.PI * angulo / 180.0)) - 5;
	}
	
	public double retornaY(double angulo, double raio) {
		return (raio * Math.sin(Math.PI * angulo / 180.0)) - 5;
	}

	public void addFilhos(Astro astro) {
		this.filhos.add(astro);
	}

	public List<Astro> getFilhos() {
		return this.filhos;
	}

	public Texture[] getTexture() {
		return texture;
	}

	public void setTexture(Texture[] texture) {
		this.texture = texture;
	}

	public Textura getTextura() {
		return textura;
	}

	public void setTextura(Textura textura) {
		this.textura = textura;
	}

	public double conveterFloatToDouble(Float f) {
		return Double.parseDouble(Float.toString(f));
	}

	public Ponto getPosition() {
		return this.position;
	}

}

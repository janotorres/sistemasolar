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
	
	private int incrementoAngulo;
	
	private int distanciaParent;

	private Astro parent;
	
	private Ponto position = new Ponto(0, 0, 0);

	public Astro(Textura textura) {
		this.filhos = new ArrayList<Astro>();
		this.textura = textura;
	}

	public Astro(Textura textura, float raio, double x, double y, double z) {
		this(textura, raio, x, y, z, 0, null, false);
	}
	
	public Astro(Textura textura, float raio, double x, double y, double z, int incrementoAngulo, Astro parent) {
		this(textura, raio, x, y, z, incrementoAngulo, parent, true);
	}
	
	public Astro(Textura textura, float raio, double y, int incrementoAngulo, Astro parent, int distanciaParent) {
		this(textura, raio, 0, y, 0, incrementoAngulo, parent, true);
		this.distanciaParent = distanciaParent;
	}

	public Astro(Textura textura, float raio, double x, double y, double z, int incrementoAngulo, Astro parent, boolean rotaciona) {
		this.textura = textura;
		this.filhos = new ArrayList<Astro>();
		this.raio = raio;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotaciona = rotaciona;
		this.incrementoAngulo = incrementoAngulo;
		this.parent = parent;
		this.position.setX(Double.valueOf(x).floatValue());
		this.position.setY(Double.valueOf(y).floatValue());
		this.position.setZ(Double.valueOf(z).floatValue());
	}

	public void desenhaObjetoEstatico(GL gl, GLU glu, GLUquadric quadric){
		gl.glPushMatrix();
		gl.glBindTexture(GL.GL_TEXTURE_2D, getTexture()[getTextura().getAstro()].getTexID()[0]);
		gl.glTranslatef(x.floatValue(), y.floatValue(), z.floatValue());
		position.setX(x.floatValue());	
		position.setZ(z.floatValue());
		glu.gluSphere(quadric, raio, SLICES, STACKS);
		gl.glPopMatrix();
	}
	
	public void desenhaObjetoAnimado(GL gl, GLU glu, GLUquadric quadric){
		gl.glPushMatrix();
		gl.glBindTexture(GL.GL_TEXTURE_2D, getTexture()[getTextura().getAstro()].getTexID()[0]);
		Double x = retornaX(angulo , this.distanciaParent);
		Double z = retornaY(angulo , this.distanciaParent);
		gl.glTranslatef(x.floatValue(), y.floatValue(), z.floatValue());
		gl.glRotatef(36, x.floatValue(), y.floatValue(), z.floatValue());
		position.setX(x.floatValue());	
		position.setZ(z.floatValue());
		incrementaAngulo();
		glu.gluSphere(quadric, raio, SLICES, STACKS);
		gl.glPopMatrix();
	}
	
	public void desenha(GL gl, GLU glu, GLUquadric quadric) throws IOException {
		if (rotaciona){
			desenhaObjetoAnimado(gl, glu, quadric);
		} else {
			desenhaObjetoEstatico(gl, glu, quadric);
		}

	}
	
	private void incrementaAngulo() {
		this.angulo += this.incrementoAngulo;
		if (angulo > 360){
			this.angulo = 0;
		}		
	}

	public double retornaX(double angulo, double raio) {
		return (raio * Math.cos(Math.PI * angulo / 180.0)) + this.parent.getPosition().getX();
	}
	
	public double retornaY(double angulo, double raio) {
		return (raio * Math.sin(Math.PI * angulo / 180.0)) + this.parent.getPosition().getZ();
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

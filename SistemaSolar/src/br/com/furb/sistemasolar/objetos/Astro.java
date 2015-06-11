package br.com.furb.sistemasolar.objetos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import br.com.furb.sistemasolar.enumerations.Textura;
import br.com.furb.sistemasolar.texture.Texture;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.spi.TGAImage;

public class Astro {
	
	private static final int SLICES = 40;
	
	private static final int STACKS = 40;

	private Textura textura;

	private List<Astro> filhos;

	private float x;

	private float raio;

	private float y;

	private float z;
	
	private Texture[] texture;
	
	public Astro(Textura textura) {
		this.filhos = new ArrayList<Astro>();
		this.textura = textura;
	}
	
	public Astro(Textura textura,float raio, float x, float y, float z){
		this.textura = textura;
		this.filhos = new ArrayList<Astro>();
		this.raio = raio;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void desenha(GL gl, GLUT glut) throws IOException {
		gl.glPushMatrix();
		gl.glBindTexture(GL.GL_TEXTURE_2D, getTexture()[getTextura().getAstro()].getTexID()[0]);
		gl.glTranslatef(x, y, z);
		glut.glutSolidSphere(raio, SLICES, STACKS);
		gl.glPopMatrix();
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
	
	
}

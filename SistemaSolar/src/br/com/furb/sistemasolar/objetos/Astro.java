package br.com.furb.sistemasolar.objetos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import br.com.furb.sistemasolar.enumerations.Textura;

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
	
	public Astro(Textura textura) {
		this.filhos = new ArrayList<Astro>();
		this.textura = textura;
	}
	
	public Astro(float raio, float x, float y, float z){
		this.filhos = new ArrayList<Astro>();
		this.raio = raio;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void desenha(GL gl, GLUT glut) throws IOException {
		//TGAImage tgaImage = TGAImage.read("C:\\Users\\jgtorres\\Downloads\\SistemaSolar-Texturas\\SistemaSolar-Texturas\\Texturas\\sol.tga");
		gl.glPushMatrix();
		//gl.glGenTextures(1, tgaImage.getData().asIntBuffer());
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
}

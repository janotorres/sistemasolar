package br.com.furb.sistemasolar.objetos;

import java.io.IOException;
import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;

import com.sun.opengl.util.GLUT;

/**
 * Classe que implementa os métodos e eventos do OpenGL, é aonde os desenhos são
 * renderizados e manipulados .
 */
public class SistemaSolar extends GLCanvas implements GLEventListener {

	private static final long serialVersionUID = 1L;

	private GL gl;

	private GLUT glut;

	private GLAutoDrawable glDrawable;

	private Astro sol;

	private static GLCapabilities getGLCapabilities() {
		GLCapabilities glCaps = new GLCapabilities();
		glCaps.setRedBits(8);
		glCaps.setBlueBits(8);
		glCaps.setGreenBits(8);
		glCaps.setAlphaBits(8);
		return glCaps;

	}

	/**
	 * Construtor da classe Mundo. Seta os Listeners e as principais entidades:
	 * câmera e estado
	 */
	public SistemaSolar() {
		super(getGLCapabilities());
		this.sol = new Astro(3f, -3.0f, 0f,0f);
		this.sol.addFilhos(new Astro(0.35f, 0.5f, 0f, 1f));
		this.sol.addFilhos(new Astro(0.4f, 1.5f, 0f, 2f));
		Astro terra = new Astro(0.5f, 3f, 0f, 3f);
		//terra.addFilhos(new Astro(0.5f, 3f, 3f, 3f));
		this.sol.addFilhos(terra);
		this.sol.addFilhos(new Astro(0.6f, 5f, 0f, 4f));
		this.addGLEventListener(this);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		try {
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			desenhaObjetosGraficos();
			gl.glFlush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void desenhaObjetosGraficos() throws IOException {
		sol.desenha(gl, glut);
		for (Iterator<Astro> iterator = sol.getFilhos().iterator(); iterator.hasNext();) {
			Astro astro = iterator.next();
			astro.desenha(gl, glut);
			
		}
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		glut = new GLUT();
		glDrawable = drawable;
		gl = glDrawable.getGL();
		
		/*
		float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float position[] = { 0.0f, 3.0f, 2.0f, 0.0f };
		float lmodel_ambient[] = { 0.4f, 0.4f, 0.4f, 1.0f };
		float local_view[] = { 0.0f };
*/
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);
		/*gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view, 0);*/
		gl.glEnable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_LIGHT0);

		gl.glClearColor(0.0f, 0.1f, 0.1f, 0.0f);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		GL gl = drawable.getGL();
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		if (w <= (h * 2)) //
			gl.glOrtho(-6.0, 6.0, -3.0 * ((float) h * 2) / (float) w, 3.0 * ((float) h * 2) / (float) w, -10.0, 10.0);
		else
			gl.glOrtho(-6.0 * (float) w / ((float) h * 2), 6.0 * (float) w / ((float) h * 2), -3.0, 3.0, -10.0, 10.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

	}
}

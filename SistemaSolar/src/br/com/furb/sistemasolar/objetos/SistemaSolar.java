package br.com.furb.sistemasolar.objetos;

import java.io.IOException;
import java.util.Iterator;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import br.com.furb.sistemasolar.listener.ViewListener;

import com.sun.opengl.util.GLUT;

/**
 * Classe que implementa os métodos e eventos do OpenGL, é aonde os desenhos são
 * renderizados e manipulados .
 */
public class SistemaSolar extends GLCanvas implements GLEventListener {

	private static final long serialVersionUID = 1L;

	private GL gl;

	private GLU glu;

	private GLUT glut;

	private GLAutoDrawable glDrawable;

	public Camera camera;

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
		this.sol = new Astro(3f, -5f, 0f, 0f);
		this.sol.addFilhos(new Astro(0.35f, -1f, 0f, 0f));
		this.sol.addFilhos(new Astro(0.4f, 1f, 0f, 0f));
		Astro terra = new Astro(0.5f, 3f, 0f, 0f);
		//terra.addFilhos(new Astro(0.5f, 3f, 3f, 3f));
		this.sol.addFilhos(terra);
		this.sol.addFilhos(new Astro(0.6f, 6f, 0f, 0f));
		this.addGLEventListener(this);
		this.addKeyListener(new ViewListener(this));
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		try {
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();

			glu.gluLookAt(camera.getxEye(), camera.getyEye(), camera.getzEye(),
				camera.getxCenter(), camera.getyCenter(),
					camera.getzCenter(), 0.0f, 1.0f, 0.0f);
			
			desenhaObjetosGraficos();
			gl.glFlush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void desenhaObjetosGraficos() throws IOException {
		sol.desenha(gl, glut);
		for (Iterator<Astro> iterator = sol.getFilhos().iterator(); iterator
				.hasNext();) {
			Astro astro = iterator.next();
			astro.desenha(gl, glut);

		}
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = glDrawable.getGL();
		glu = new GLU();
		glut = new GLUT();	
		
		glDrawable.setGL(new DebugGL(gl));
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		/*
		 * float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f }; float diffuse[] = {
		 * 1.0f, 1.0f, 1.0f, 1.0f }; float position[] = { 0.0f, 3.0f, 2.0f, 0.0f
		 * }; float lmodel_ambient[] = { 0.4f, 0.4f, 0.4f, 1.0f }; float
		 * local_view[] = { 0.0f };
		 */
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);
		/*
		 * gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);
		 * gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse, 0);
		 * gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);
		 * gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
		 * gl.glLightModelfv(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view, 0);
		 */
		gl.glEnable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_LIGHT0);

		camera = new Camera();
		camera.setxEye(0f);
		camera.setyEye(0f);
		camera.setzEye(25f);
		camera.setxCenter(0f);
		camera.setyCenter(0f);
		camera.setzCenter(0f);
		camera.setFovy(45);
		camera.setNear(0.1);
		camera.setFar(100);

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, w, h);
				
		glu.gluPerspective(camera.getFovy(), w / h, camera.getNear(), camera.getFar());
	}
	
}

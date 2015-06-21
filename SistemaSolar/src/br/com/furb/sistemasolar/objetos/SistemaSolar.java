package br.com.furb.sistemasolar.objetos;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import br.com.furb.sistemasolar.enumerations.Textura;
import br.com.furb.sistemasolar.listener.ViewListener;
import br.com.furb.sistemasolar.texture.TGALoader;
import br.com.furb.sistemasolar.texture.Texture;

/**
 * Classe que implementa os métodos e eventos do OpenGL, é aonde os desenhos são
 * renderizados e manipulados .
 */
public class SistemaSolar extends GLCanvas implements GLEventListener {

	private static final long serialVersionUID = 1L;

	private GL gl;

	private GLU glu;

	private GLUquadric quadric;

	private GLAutoDrawable glDrawable;

	public Camera camera;

	public Astro sol;

	public Astro terra;

	private Texture[] texture = new Texture[6]; // Storage For 2 Textures ( NEW
												// )

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

		this.sol = new Astro(Textura.SOL, 3f, -5f, 0f, 0f, false);
		this.sol.addFilhos(new Astro(Textura.MERCURIO, 0.35f, 4, 0f, 5));
		this.sol.addFilhos(new Astro(Textura.VENUS, 0.4f, 3, 0f, 5));
		this.terra = new Astro(Textura.TERRA, 0.5f, 10, 0, 5);
		terra.addFilhos(new Astro(Textura.LUA, 0.1f, 11, 0f, 5));
		this.sol.addFilhos(terra);
		this.addGLEventListener(this);
		this.addKeyListener(new ViewListener(this));

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		try {
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();

			Ponto terraPosition = this.terra.getPosition();

			if (camera.getPosition() == 'S') {
				camera.setxCenter(terraPosition.getX());
				camera.setyCenter(0.0f);
				camera.setzCenter(terraPosition.getZ());

				//camera.setxEye(-5.0f);

			} else if (camera.getPosition() == 'T') {
				camera.setxEye(terraPosition.getX());
				camera.setyEye(0.0f);
				camera.setzEye(terraPosition.getZ() + 1f);
				
				//camera.setxCenter(-5.0f);
			}

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
		sol.setTexture(texture);
		sol.desenha(gl, glu, quadric);
		for (Iterator<Astro> iterator = sol.getFilhos().iterator(); iterator
				.hasNext();) {
			Astro astro = iterator.next();
			astro.setTexture(texture);
			astro.desenha(gl, glu, quadric);

			for (Iterator<Astro> iteratorTerra = astro.getFilhos().iterator(); iteratorTerra
					.hasNext();) {
				Astro lua = iteratorTerra.next();
				lua.setTexture(texture);
				lua.desenha(gl, glu, quadric);
			}
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

		glDrawable.setGL(new DebugGL(gl));
		quadric = glu.gluNewQuadric();

		try {
			loadGLTextures(gl);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float lmodel_ambient[] = { 0.4f, 0.4f, 0.4f, 1.0f };
		float local_view[] = { 0.0f };
		float position[] = { 0.0f, 3.0f, 5.0f, 0.0f };

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);

		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glShadeModel(GL.GL_SMOOTH);

		gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse, 0);

		gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view, 0);

		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);
		gl.glEnable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_LIGHT0);

		glu.gluQuadricNormals(quadric, GL.GL_SMOOTH);
		glu.gluQuadricTexture(quadric, true);
		gl.glTexGeni(GL.GL_S, GL.GL_TEXTURE_GEN_MODE, GL.GL_NORMAL_MAP);
		gl.glTexGeni(GL.GL_T, GL.GL_TEXTURE_GEN_MODE, GL.GL_NORMAL_MAP);

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
		gl.glViewport(0, 0, w, h);
		gl.glLoadIdentity();

		glu.gluPerspective(camera.getFovy(), w / h, camera.getNear(),
				camera.getFar());
	}

	private void loadGLTextures(GL gl) throws IOException {
		texture[0] = new Texture();
		texture[1] = new Texture();
		texture[2] = new Texture();
		texture[3] = new Texture();
		texture[4] = new Texture();
		texture[5] = new Texture();

		TGALoader.loadTGA(texture[0], "texturas/sol.tga");
		TGALoader.loadTGA(texture[1], "texturas/mercurio.tga");
		TGALoader.loadTGA(texture[2], "texturas/venus.tga");
		TGALoader.loadTGA(texture[3], "texturas/terra.tga");
		TGALoader.loadTGA(texture[4], "texturas/marte.tga");
		TGALoader.loadTGA(texture[5], "texturas/lua.tga");
		for (int loop = 0; loop < 6; loop++) {
			gl.glGenTextures(1, texture[loop].getTexID(), 0);
			gl.glBindTexture(GL.GL_TEXTURE_2D, texture[loop].getTexID()[0]);
			gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, texture[loop].getBpp() / 8,
					texture[loop].getWidth(), texture[loop].getHeight(), 0,
					texture[loop].getType(), GL.GL_UNSIGNED_BYTE,
					texture[loop].getImageData());

			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER,
					GL.GL_LINEAR);
			gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER,
					GL.GL_LINEAR);
		}

	}

}

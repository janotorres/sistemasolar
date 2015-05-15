package br.com.furb.sistemasolar.objetos;

import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import br.com.furb.sistemasolar.enumerations.Textura;

/** Classe que implementa os métodos e eventos do OpenGL, é aonde os desenhos 
 * são renderizados e manipulados . */
public class SistemaSolar extends GLCanvas implements GLEventListener{

	private static final long serialVersionUID = 1L;

	private GL gl;
	
	private GLU glu;
	
	private GLAutoDrawable glDrawable;
	
	private Astro sol;
	
	private Camera camera;	
	

	private static GLCapabilities getGLCapabilities() {
		GLCapabilities glCaps = new GLCapabilities();
		glCaps.setRedBits(8);
		glCaps.setBlueBits(8);
		glCaps.setGreenBits(8);
		glCaps.setAlphaBits(8); 
		return glCaps;

	}
	/** Construtor da classe Mundo. Seta os Listeners e as principais entidades:
	 * câmera e estado*/
	public SistemaSolar() {
		super(getGLCapabilities());
		this.sol = new Astro(Textura.SOL);
		this.sol.addFilhos(new Astro(Textura.MERCURIO));
		this.sol.addFilhos(new Astro(Textura.VENUS));
		Astro terra = new Astro(Textura.TERRA);
		terra.addFilhos(new Astro(Textura.LUA));
		this.sol.addFilhos(terra);
		this.sol.addFilhos(new Astro(Textura.MARTE));

		
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		try 
		{
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();
			//glu.gluOrtho2D(camera.getOrtho2D_minX(), camera.getOrtho2D_maxX(), camera.getOrtho2D_minY(), camera.getOrtho2D_maxY());
			desenhaObjetosGraficos();			
			gl.glFlush();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	private void desenhaObjetosGraficos() {
		for (int i = 0; i < sol.getFilhos().size(); i++) {
			Astro objetoGrafico = sol.getFilhos().get(i);
		//	objetoGrafico.setGl(gl);
			objetoGrafico.desenha();
		/*	if (objetoGrafico.isSelected())
				objetoGrafico.getBoundBox().desenha(gl);
			for (Astro filho : objetoGrafico.getFilhos()){
				filho.desenha();
				if (filho.isSelected())
					filho.getBoundBox().desenha(gl);*/
			//}
		}		
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
}

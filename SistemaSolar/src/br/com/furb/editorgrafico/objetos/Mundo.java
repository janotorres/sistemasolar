package br.com.furb.editorgrafico.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import br.com.furb.editorgrafico.enumerations.Cor;
import br.com.furb.editorgrafico.enumerations.Estado;
import br.com.furb.editorgrafico.listeners.MouseListener;
import br.com.furb.editorgrafico.listeners.ViewListener;

/** Classe que implementa os métodos e eventos do OpenGL, é aonde os desenhos 
 * são renderizados e manipulados . */
public class Mundo extends GLCanvas implements GLEventListener{

	private static final long serialVersionUID = 1L;

	private GL gl;
	
	private GLU glu;
	
	private GLAutoDrawable glDrawable;
	
	private List<ObjetoGrafico> objetos;
	
	private Camera camera;
	
	private Estado estado;

	private Ponto pontoEmEdicao;

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
	public Mundo() {
		super(getGLCapabilities());
		this.camera = new Camera(this);
		this.objetos = new ArrayList<ObjetoGrafico>();
		this.estado = Estado.DESENHO;
		MouseListener mouseListener = new MouseListener(this);
		
		/*Ponto ponto1 = new Ponto(100, 100, 0);
		Ponto ponto2 = new Ponto(100, 200, 0);
		Ponto ponto3 = new Ponto(200, 200, 0);
		Ponto ponto4 = new Ponto(200, 100, 0);*/
		
		this.addGLEventListener(this);        
		this.addKeyListener(new ViewListener(this));
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		this.requestFocus();
		
		/*criaObjeto(ponto1);
		this.objetos.get(0).getPontos().add(ponto2);
		this.objetos.get(0).getPontos().add(ponto3);
		this.objetos.get(0).getPontos().add(ponto4);*/
	}
	
	public List<ObjetoGrafico> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<ObjetoGrafico> objetos) {
		this.objetos = objetos;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	/** Método do OpenGL aonde é definido o Ortho2D (câmera), a matriz em foco, e aonde são desenhados os objetos gráficos.*/
	@Override
	public void display(GLAutoDrawable arg0) {
		try 
		{
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			gl.glMatrixMode(GL.GL_MODELVIEW);
			gl.glLoadIdentity();
			glu.gluOrtho2D(camera.getOrtho2D_minX(), camera.getOrtho2D_maxX(), camera.getOrtho2D_minY(), camera.getOrtho2D_maxY());
			desenhaObjetosGraficos();			
			gl.glFlush();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	/**Método que além de desenhar os objetos gráficos, desenha a BoundBox ao redor do objeto selecionado.*/
	private void desenhaObjetosGraficos() {
		for (int i = 0; i < objetos.size(); i++) {
			ObjetoGrafico objetoGrafico = objetos.get(i);
			objetoGrafico.setGl(gl);
			objetoGrafico.desenha();
			if (objetoGrafico.isSelected())
				objetoGrafico.getBoundBox().desenha(gl);
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()){
				filho.desenha();
				if (filho.isSelected())
					filho.getBoundBox().desenha(gl);
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
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		
	}
	
	public void desenha(){
		glDrawable.display();
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void criaObjeto(Ponto ponto) {
		ObjetoGrafico objetoGrafico = new ObjetoGrafico(ponto);
		objetoGrafico.setPrimitivaGrafica(GL.GL_LINE_STRIP);
		objetoGrafico.setCor(Cor.PRETO);
		this.objetos.add(objetoGrafico);
	}
	
	
	public ObjetoGrafico getObjetoSelecionado(){
		for(ObjetoGrafico objeto : this.objetos){
			if (objeto.isSelected())
				return objeto;
			for (ObjetoGrafico filho : objeto.getObjetoGraficos())
				if (filho.isSelected())
					return filho;
		}
		return null;
	}
	
	public void moverObjetosDireta(){
		ObjetoGrafico objetoGrafico = getObjetoSelecionado();
		if (objetoGrafico != null){
			objetoGrafico.moveDireta();
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()) {
				filho.moveDireta();	
			}
		}
	}
	
	public void moverObjetosEsquerda(){
		ObjetoGrafico objetoGrafico = getObjetoSelecionado();
		if (objetoGrafico != null){
			objetoGrafico.moveEsquerda();
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()) {
				filho.moveEsquerda();	
			}
		}
	}
	
	public void moverObjetosAbaixo(){
		ObjetoGrafico objetoGrafico = getObjetoSelecionado();
		if (objetoGrafico != null){
			objetoGrafico.moveAbaixo();
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()) {
				filho.moveAbaixo();	
			}
		}
	}
	
	public void moverObjetosAcima(){
		ObjetoGrafico objetoGrafico = getObjetoSelecionado();
		if (objetoGrafico != null){
			objetoGrafico.moveAcima();
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()) {
				filho.moveAcima();	
			}
		}
		
	}
	
	public void aumentarObjeto(){
		ObjetoGrafico objetoGrafico = getObjetoSelecionado();
		if (objetoGrafico != null){
			objetoGrafico.aumentaDesenho();
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()) {
				filho.aumentaDesenho();	
			}
		}
	}
	
	public void diminuirObjeto(){
		ObjetoGrafico objetoGrafico = getObjetoSelecionado();
		if (objetoGrafico != null){
			objetoGrafico.diminuiDesenho();
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()) {
				filho.diminuiDesenho();	
			}
		}
	}
	
	public void rotacaoObjeto(){
		ObjetoGrafico objetoGrafico = getObjetoSelecionado();
		if (objetoGrafico != null){
			objetoGrafico.rotacaoDesenho();
			for (ObjetoGrafico filho : objetoGrafico.getObjetoGraficos()) {
				filho.rotacaoDesenho();	
			}
		}
	}
	
	public void pintarObjeto(Cor cor){
		for (int i = 0; i < objetos.size(); i++) {
			objetos.get(i).setCor(cor);
		}
	}
	
	public ObjetoGrafico getUltimoObjeto(){
		int posicaoObjeto = getObjetos().size() - 1;
		return getObjetos().get(posicaoObjeto);
	}

	public void deletaPonto(Ponto p){
		for (ObjetoGrafico objeto : objetos) {
			for (Ponto ponto : objeto.getPontos()){
				if (ponto.ehProximo(p)){
					objeto.getPontos().remove(ponto);
					System.out.println("Ponto Removido");
					return;
				}
			}
		}
	}
	public Ponto getPontoEmEdicao() {
		return this.pontoEmEdicao;
	}
	
	/** Seta coordenadas do click do usuário para o ponto em edição.
	 * @param p Ponto - coordenadas do click do usuário. */
	public void editarPonto(Ponto p) {
		for (ObjetoGrafico objeto : objetos) {
			for (Ponto ponto : objeto.getPontos()){
				if (ponto.ehProximo(p)){
					System.out.println(ponto);
					System.out.println(p);
					this.pontoEmEdicao = ponto;
					System.out.println("SELECIONOU PONTO");
				}
			}
		}		
	}
	public void setPontoEmEdicao(Ponto ponto) {
		this.pontoEmEdicao = ponto;
		
	}
	
	public void selecionaObjeto(Ponto ponto) {
		limpaSelecao();
		for(ObjetoGrafico objeto : this.objetos){
			if (objeto.contains(ponto))
				return;
			for (ObjetoGrafico filho : objeto.getObjetoGraficos()){
				if (filho.contains(ponto))
					return;	
			}
		}
	}
	
	private void limpaSelecao(){
		for(ObjetoGrafico objeto : this.objetos){
			objeto.setSelected(false);
			for (ObjetoGrafico filho : objeto.getObjetoGraficos())
				filho.setSelected(false);
		}
	}
	public void removerObjetoSelecionado() {
		for(ObjetoGrafico objeto : this.objetos){
			if (objeto.isSelected()){
				this.objetos.remove(objeto);
				return;
			}
			for (ObjetoGrafico filho : objeto.getObjetoGraficos())
				if (filho.isSelected()){
					objeto.getObjetoGraficos().remove(filho);
					return;
				}
		}
	}
}

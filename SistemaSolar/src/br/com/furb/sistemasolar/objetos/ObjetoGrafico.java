package br.com.furb.sistemasolar.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import br.com.furb.sistemasolar.enumerations.Cor;

/**Classe que representa o objeto gráfico desenhado pelo usuário no editor gráfico. */
public class ObjetoGrafico {

	private Cor cor;

	private int primitivaGrafica;

	private List<Ponto> pontos;

	private Transformacao transformacao;

	private BoundBox boundBox;

	private List<ObjetoGrafico> objetoGraficos;

	private boolean selected;

	private GL gl;

	public ObjetoGrafico(Ponto ponto) {
		this.pontos = new ArrayList<Ponto>();
		this.objetoGraficos = new ArrayList<ObjetoGrafico>();
		this.transformacao = new Transformacao();
		this.pontos.add(ponto);
		this.pontos.add(ponto.copy());
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public int getPrimitivaGrafica() {
		return primitivaGrafica;
	}

	public void setPrimitivaGrafica(int primitivaGrafica) {
		this.primitivaGrafica = primitivaGrafica;
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	public Transformacao getTransformacao() {
		return transformacao;
	}

	public void setTransformacao(Transformacao transformacao) {
		this.transformacao = transformacao;
	}

	public BoundBox getBoundBox() {
		return boundBox;
	}

	public void setBoundBox(BoundBox boundBox) {
		this.boundBox = boundBox;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/** Método que desenha o objeto gráfico no editor gráfico. Utiliza os métodos do OpenGL.
	 * É setado a cor, a matriz de transformação, a primitiva gráfica e são desenhados
	 * os filhos do objeto, caso existirem.*/
	public void desenha() {
		getGl().glColor3f(getCor().getRed(), getCor().getGreen(), getCor().getBlue());

		getGl().glPushMatrix();
		getGl().glMultMatrixd(getTransformacao().GetDate(), 0);
		getGl().glBegin(getPrimitivaGrafica());
		
		for (Ponto ponto : pontos) {
			getGl().glVertex2d(ponto.getX(), ponto.getY());
		}
		
		getGl().glEnd();
		getGl().glPopMatrix();
	}

	public GL getGl() {
		return gl;
	}

	public void setGl(GL gl) {
		this.gl = gl;
	}
	
	public void moveDireta(){
		Ponto point = new Ponto(20, 0, 0);
		translacao(point);
	}
	
	public void moveEsquerda(){
		Ponto point = new Ponto(-20, 0, 0);
		translacao(point);
	}
	
	public void moveAcima(){
		Ponto point = new Ponto(0, -20, 0);
		translacao(point);
	}
	
	public void moveAbaixo(){
		Ponto point = new Ponto(0, 20, 0);
		translacao(point);
	}
	
	/** Método que aumenta a escala do desenho. Utiliza a matriz de transformação. */
	public void aumentaDesenho(){
		
		Ponto ponto = new Ponto(- this.boundBox.getCentroX(), - this.boundBox.getCentroY(), 0);
		Transformacao matrizTranslate = new Transformacao();
		matrizTranslate.MakeTranslation(ponto);
		
		Transformacao matrizScale = new Transformacao();		
		matrizScale.MakeScale(2.0f,2.0f,1.0f);
		
		Ponto ponto2 = new Ponto(this.boundBox.getCentroX(), this.boundBox.getCentroY(), 0);
		Transformacao matrizTranslacaoInversa = new Transformacao();		
		matrizTranslacaoInversa.MakeTranslation(ponto2);
		
		Transformacao matrizGlobal = new Transformacao();
		matrizGlobal = matrizTranslate.transformMatrix(matrizGlobal);
		matrizGlobal = matrizScale.transformMatrix(matrizGlobal);
		matrizGlobal = matrizTranslacaoInversa.transformMatrix(matrizGlobal);

		setTransformacao(getTransformacao().transformMatrix(matrizGlobal));
	}
	
	/** Método que diminui a escala do desenho. Utiliza a matriz de transformação. */
	public void diminuiDesenho(){
		Ponto ponto = new Ponto(- this.boundBox.getCentroX(), - this.boundBox.getCentroY(), 0);
		Transformacao matrizTranslate = new Transformacao();
		matrizTranslate.MakeTranslation(ponto);
		
		Transformacao matrizScale = new Transformacao();		
		matrizScale.MakeScale(0.5,0.5,1.0);
		
		Ponto ponto2 = new Ponto(this.boundBox.getCentroX(), this.boundBox.getCentroY(), 0);
		Transformacao matrizTranslacaoInversa = new Transformacao();		
		matrizTranslacaoInversa.MakeTranslation(ponto2);
		
		Transformacao matrizGlobal = new Transformacao();
		matrizGlobal = matrizTranslate.transformMatrix(matrizGlobal);
		matrizGlobal = matrizScale.transformMatrix(matrizGlobal);
		matrizGlobal = matrizTranslacaoInversa.transformMatrix(matrizGlobal);
		
		setTransformacao(getTransformacao().transformMatrix(matrizGlobal));
	}
	
	/** Método que aumenta rotaciona o desenho. Utiliza a matriz de transformação. */
	public void rotacaoDesenho(){
		Ponto ponto = new Ponto(- this.boundBox.getCentroX(), - this.boundBox.getCentroY(), 0);
		Transformacao matrizTranslate = new Transformacao();
		matrizTranslate.MakeTranslation(ponto);
		
		Transformacao matrizRotacao = new Transformacao();
		matrizRotacao.MakeZRotation(Transformacao.RAS_DEG_TO_RAD * 10);
		
		//Ponto ponto2 = new Ponto(200, 200, 0);
		ponto.inverterSinal();
		Transformacao matrizTranslacaoInversa = new Transformacao();		
		matrizTranslacaoInversa.MakeTranslation(ponto);
		
		Transformacao matrizGlobal = new Transformacao();
		matrizGlobal = matrizGlobal.transformMatrix(matrizTranslacaoInversa);
		matrizGlobal = matrizGlobal.transformMatrix(matrizRotacao);
		matrizGlobal = matrizGlobal.transformMatrix(matrizTranslate);

		setTransformacao(getTransformacao().transformMatrix(matrizGlobal));
	}
	
	/** Método responsável pela translação do objeto.
	 * @param ponto Ponto - coordenadas para transladar objeto. */
	public void translacao(Ponto ponto) {
		Transformacao matrizTranslate = new Transformacao();
		matrizTranslate.MakeTranslation(ponto);
		setTransformacao(getTransformacao().transformMatrix(matrizTranslate));		
	}	

	public void setUltimoPonto(Ponto ponto){
		int posicaoPonto  = this.getPontos().size() - 1;
		this.getPontos().set(posicaoPonto,ponto);
	}
	
	
	public void geraBBox(){
		float xMin = Float.MAX_VALUE;
		float xMax = Float.MIN_VALUE;
		float yMin = Float.MAX_VALUE;
		float yMax = Float.MIN_VALUE;
		for(Ponto ponto : this.pontos){
			
			System.out.println(ponto.toString());
			if (ponto.getX() > xMax){
				xMax = ponto.getX();
			}
			
			if (ponto.getX() < xMin){
				xMin = ponto.getX();
			}
			
			if (ponto.getY() > yMax){
				yMax = ponto.getY();
			}

			if (ponto.getY() < yMin){
				yMin = ponto.getY();
			}
		}

		this.boundBox = new BoundBox(xMin, xMax, yMin, yMax);
	}

	public boolean contains(Ponto ponto) {
		return this.selected = (this.getBoundBox().contain(ponto) && this.scanline(ponto));
	}

	private boolean scanline(Ponto p) {
		int paridade = 0;
		for (int i = 0; i < this.pontos.size(); i++) {
			Ponto p1 = this.pontos.get(i);
			Ponto p2 = this.pontos.get(i + 1 == this.pontos.size() ? 0 : i +1 );
			double Ti  = (p.getY()  - p1.getY()) / (p2.getY()  -  p1.getY());
			if (Ti > 0 && Ti < 1){
				double Xi =  p1.getX() + ((p2.getX() - p1.getX()) * Ti);
				if (Xi > p.getX()){
					paridade++;
				}
			}
			
			
		}
		return paridade % 2 == 1;
	}

	public void addFilho(ObjetoGrafico filho) {
		this.objetoGraficos.add(filho);
	}

	public List<ObjetoGrafico> getObjetoGraficos() {
		return this.objetoGraficos;
	}
}

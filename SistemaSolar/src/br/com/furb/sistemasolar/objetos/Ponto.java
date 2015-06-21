package br.com.furb.sistemasolar.objetos;


/** Classe que armazena as coordenadas do vetor do objeto gr�fico. */
public class Ponto implements Cloneable {

	public Ponto(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	private float x;
	
	private float y;
	
	private float z;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public Ponto copy() {
		try {
			return (Ponto) this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	@Override
	public String toString() {
		return "[X="+this.x+"],[Y="+this.y+"], [Z="+this.z+"]";
	}

	/** Verifica se a dist�ncia dos pontos (ponto em quest�o e ponto passado por par�metro) � pr�xima (5).
	 * @param ponto Ponto - ponto para ser comparada a dist�ncia com o ponto em quest�o.
	 * @return boolean - retorna true se os pontos forem pr�ximos e false se n�o forem. */
	public boolean ehProximo(Ponto ponto) {
		System.out.println(this);
		System.out.println(ponto);
		if ((this.getX() - 5 <= ponto.getX()) && (this.getX() + 5 >= ponto.getX()) 
		&& (this.getY() - 5 <= ponto.getY()) && (this.getY() + 5 >= ponto.getY())){
			return true;
		}
		return false;
	}

	public void setPonto(Ponto ponto) {
		this.x = ponto.getX();
		this.y = ponto.getY();
		
	}
	
	public void inverterSinal() {
		this.x = this.x * -1;
		this.y = this.y * -1;
		this.z = this.z * -1;
	}
}

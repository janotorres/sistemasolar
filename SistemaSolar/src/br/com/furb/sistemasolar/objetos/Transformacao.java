package br.com.furb.sistemasolar.objetos;

/** Classe responsável por rotacionar, alterar a escala e transladar o objeto. */
public class Transformacao {

	static final double RAS_DEG_TO_RAD = 0.017453292519943295769236907684886;

	private double[] matrix = { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };

	public Transformacao() {
	}

	/** Transforma a matriz em matriz identidade (diagonoal principal = 1 e o resto 0)*/
	public void MakeIdentity() {
		for (int i = 0; i < 16; ++i) {
			matrix[i] = 0.0;
		}
		matrix[0] = matrix[5] = matrix[10] = matrix[15] = 1.0;
	}

	/** Altera as posições 12, 13 e 14 da matriz, fazendo o objeto ser transladado
	 * de acordo com o parâmetro.
	 * @param translationVector Ponto - Coordenadas do vetor de translação */
	public void MakeTranslation(Ponto translationVector) {
		MakeIdentity();
		matrix[12] = translationVector.getX();
		matrix[13] = translationVector.getY();
		matrix[14] = translationVector.getZ();
	}

	/** Altera as posições 5, 9, 6 e 10 da matriz, fazendo o objeto ser rotacionado
	 * em X de acordo com o parâmetro.
	 * @param radians double - Radianos do X */
	public void MakeXRotation(double radians) {
		MakeIdentity();
		matrix[5] = Math.cos(radians);
		matrix[9] = -Math.sin(radians);
		matrix[6] = Math.sin(radians);
		matrix[10] = Math.cos(radians);
	}

	/** Altera as posições 0, 8, 2 e 10 da matriz, fazendo o objeto ser rotacionado
	 * em Y de acordo com o parâmetro.
	 * @param radians double - Radianos do Y */
	public void MakeYRotation(double radians) {
		MakeIdentity();
		matrix[0] = Math.cos(radians);
		matrix[8] = Math.sin(radians);
		matrix[2] = -Math.sin(radians);
		matrix[10] = Math.cos(radians);
	}

	/** Altera as posições 0, 4, 1 e 5 da matriz, fazendo o objeto ser rotacionado
	 * em Z de acordo com o parâmetro.
	 * @param radians double - Radianos do Z */
	public void MakeZRotation(double radians) {
		MakeIdentity();
		matrix[0] = Math.cos(radians);
		matrix[4] = -Math.sin(radians);
		matrix[1] = Math.sin(radians);
		matrix[5] = Math.cos(radians);
	}

	/** Altera as posições 0, 5, e 10 da matriz, alterando a escala
	 * do objeto de acordo com os parânetros
	 * @param sX double - Coordenadas de X 
	 * @param sY double - Coordenadas de Y
	 * @param sZ double - Coordenadas de Z*/
	public void MakeScale(double sX, double sY, double sZ) {
		MakeIdentity();
		matrix[0] = sX;
		matrix[5] = sY;
		matrix[10] = sZ;
	}

	/** Método utilizado para manter as transformações do objeto.
	 * @param t Transformacao - Transformação da matriz em questão.
	 * @return Transformacao - Retorna a multipliacação das matrizes.*/
	public Transformacao transformMatrix(Transformacao t) {
		Transformacao result = new Transformacao();
		for (int i = 0; i < 16; ++i)
			result.matrix[i] = matrix[i % 4] * t.matrix[i / 4 * 4]
					+ matrix[(i % 4) + 4] * t.matrix[i / 4 * 4 + 1]
					+ matrix[(i % 4) + 8] * t.matrix[i / 4 * 4 + 2]
					+ matrix[(i % 4) + 12] * t.matrix[i / 4 * 4 + 3];
		return result;
	}

	public double GetElement(int index) {
		return matrix[index];
	}

	public void SetElement(int index, double value) {
		matrix[index] = value;
	}

	public double[] GetDate() {
		return matrix;
	}

	public void SetData(double[] data) {
		int i;

		for (i = 0; i < 16; i++) {
			matrix[i] = (data[i]);
		}
	}

}

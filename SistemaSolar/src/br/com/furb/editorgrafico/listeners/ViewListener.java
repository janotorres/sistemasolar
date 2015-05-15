package br.com.furb.editorgrafico.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.com.furb.editorgrafico.enumerations.Cor;
import br.com.furb.editorgrafico.enumerations.Estado;
import br.com.furb.editorgrafico.objetos.Mundo;
import br.com.furb.editorgrafico.objetos.ObjetoGrafico;

/**Listener do teclado, utilizado para identificar quando o usu�rio que transformar
 * algum objeto, ou alterar o modo de edi��o do editor.*/
public class ViewListener implements KeyListener {

	private Mundo mundo;

	public ViewListener(Mundo mundo) {
		this.mundo = mundo;
	}

	/**M�todo que age de acordo com a tecla pressionado pelo usu�rio:
	 * Flecha da direita: Move o objeto selecionado para a esqueda;
	 * Flecha da esquerda: Move o objeto selecionado para a direita;
	 * Flecha para baixo: Move o objeto selecionado para baixo;
	 * Flecha para cima: Move o objeto selecionado para cima;
	 * 1: Aumenta escala do objeto selecionado;
	 * 2: Diminui escala do objeto selecionado;
	 * T: Termina o desenho do objeto gr�fico num pol�gono aberto;
	 * D: Deleta o ponto selecionado;
	 * E: Altera o modo de edi��o para "Edi��o";
	 * 3: Rotaciona objeto selecionado;
	 * R: Altera a cor do objeto selecionado para vermelho;
	 * G: Altera a cor do objeto selecionado para verde;
	 * A: Altera a cor do objeto selecionado para azul;
	 * S: Altera o estado do Editor para "Sele��o", permitindo selecionar objetos;
	 * I: Permite desenhar v�rtices quando estiver em modo "Sele��o";
	 * */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {			
		case KeyEvent.VK_RIGHT:
			mundo.moverObjetosDireta();
			mundo.desenha();
			break;
		case KeyEvent.VK_LEFT:
			mundo.moverObjetosEsquerda();
			mundo.desenha();
			break;
		case KeyEvent.VK_UP:
			mundo.moverObjetosAcima();
			mundo.desenha();
			break;
		case KeyEvent.VK_DOWN:
			mundo.moverObjetosAbaixo();
			mundo.desenha();
			break;
		case KeyEvent.VK_1:
			mundo.aumentarObjeto();
			mundo.desenha();
			break;	
		case KeyEvent.VK_2:
			mundo.diminuirObjeto();
			mundo.desenha();
			break;
		case KeyEvent.VK_T:
			ObjetoGrafico objeto = mundo.getUltimoObjeto();
			int qtdPontos = objeto.getPontos().size();
			if (mundo.getEstado().equals(Estado.DESENHANDO) && qtdPontos > 3){
				objeto.getPontos().remove(qtdPontos - 1);
				objeto.geraBBox();
				mundo.setEstado(Estado.DESENHO);
				
			}
				
			mundo.desenha();
			break;
		case KeyEvent.VK_D:
			mundo.setEstado(Estado.DELETAR_PONTO);
			mundo.desenha();
			break;
		case KeyEvent.VK_I:
			mundo.setEstado(Estado.DESENHO);
			mundo.desenha();
			break;
			
		case KeyEvent.VK_E:
			System.out.println("MODO EDI��O");
			mundo.setEstado(Estado.EDITAR_PONTO);
			mundo.desenha();
			break;
		case KeyEvent.VK_S:
			mundo.setEstado(Estado.SELECAO);
			mundo.desenha();
			break;
		case KeyEvent.VK_3:
			mundo.rotacaoObjeto();
			mundo.desenha();
			break;
		case KeyEvent.VK_R:
			mundo.pintarObjeto(Cor.VERMELHO);
			mundo.desenha();
			break;
		case KeyEvent.VK_G:
			mundo.pintarObjeto(Cor.VERDE);
			mundo.desenha();
			break;
		case KeyEvent.VK_A:
			mundo.pintarObjeto(Cor.AZUL);
			mundo.desenha();
			break;
		case KeyEvent.VK_M:
			mundo.removerObjetoSelecionado();
			mundo.desenha();
			break;
		}
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}

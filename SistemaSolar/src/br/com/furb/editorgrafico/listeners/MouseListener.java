package br.com.furb.editorgrafico.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL;

import br.com.furb.editorgrafico.enumerations.Estado;
import br.com.furb.editorgrafico.objetos.Mundo;
import br.com.furb.editorgrafico.objetos.ObjetoGrafico;
import br.com.furb.editorgrafico.objetos.Ponto;

/**Listener do Mouse, utilizado quando o usuário está iteragindo com o Editor (movendo e clicando o mouse) */
public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {

	private Mundo mundo;

	public MouseListener(Mundo mundo) {
		this.mundo = mundo;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Ponto ponto = new Ponto(e.getX(), e.getY(), 0);
		switch (mundo.getEstado()){
			case DESENHANDO:		
				
				ObjetoGrafico objeto = mundo.getUltimoObjeto();
				objeto.setUltimoPonto(ponto);
				mundo.desenha();
				break;
				
			case EDITAR_PONTO:
				if (mundo.getPontoEmEdicao() != null){
					System.out.println("MOVENDO");
					mundo.getPontoEmEdicao().setPonto(ponto);
					mundo.desenha();
				} 
					
			default: break;
		}
		
	}
	
	/** Listener do click do mouse. Modifica o objeto gráfico de acordo com o estado dele. */
	@Override
	public void mouseClicked(MouseEvent e) {
		Ponto ponto = new Ponto(e.getX() , e.getY(), 0);
		
		switch (mundo.getEstado()){
		
			
			case DESENHO:
				mundo.criaObjeto(ponto);
				mundo.setEstado(Estado.DESENHANDO);
				break;
			case DESENHANDO:
				
				ObjetoGrafico objeto = mundo.getUltimoObjeto();
				int qtdPontos = objeto.getPontos().size();
				if  (qtdPontos > 3){
					if (objeto.getPontos().get(0).ehProximo(ponto)){
						objeto.setPrimitivaGrafica(GL.GL_LINE_LOOP);
						objeto.geraBBox();
						mundo.setEstado(Estado.DESENHO);
						if (mundo.getObjetoSelecionado() != null){
							ObjetoGrafico filho = mundo.getUltimoObjeto();
							mundo.getObjetos().remove(filho);
							mundo.getObjetoSelecionado().addFilho(filho);
						}
						break;
					}
				}
				mundo.getUltimoObjeto().getPontos().add(ponto);
				break;
			
			case SELECAO:
				mundo.selecionaObjeto(ponto);
				break;
			case DELETAR_PONTO:
				mundo.deletaPonto(ponto);
				break;
			case EDITAR_PONTO:
				
				if (mundo.getPontoEmEdicao() == null){
					mundo.editarPonto(ponto);
				} else {
					mundo.setPontoEmEdicao(null);
					mundo.setEstado(Estado.DESENHO);
				}
				break;

		}
		mundo.desenha();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}

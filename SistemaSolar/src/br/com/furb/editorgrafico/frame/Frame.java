package br.com.furb.editorgrafico.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import br.com.furb.editorgrafico.objetos.Mundo;

/** Editor gráfico. No construtor é adicionado o Mundo (canvas) ao frame. */
public class Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int janelaLargura  = 400, janelaAltura = 400;
	
	public Frame() {		
		super("CG-N3");   
		Mundo mundo = new Mundo();
		setBounds(300,250,janelaLargura+15,janelaAltura+39); 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		add(mundo,BorderLayout.CENTER);
		mundo.requestFocus();
	}		
	

}

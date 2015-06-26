package br.com.furb.sistemasolar.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import br.com.furb.sistemasolar.objetos.SistemaSolar;

/** Editor gráfico. No construtor é adicionado o Mundo (canvas) ao frame. */
public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private int janelaLargura = 650, janelaAltura = 650;

	public Frame() {
		super("Sistema Solar(CG-N4)");
		SistemaSolar mundo = new SistemaSolar();
		setBounds(350, 50, janelaLargura, janelaAltura + 22);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mundo, BorderLayout.CENTER);
		mundo.requestFocusInWindow();
	}

}

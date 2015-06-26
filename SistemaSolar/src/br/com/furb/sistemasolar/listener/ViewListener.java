package br.com.furb.sistemasolar.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.com.furb.sistemasolar.objetos.SistemaSolar;

public class ViewListener implements KeyListener {

	private SistemaSolar sistemaSolar;

	public ViewListener(SistemaSolar sistemaSolar) {
		this.sistemaSolar = sistemaSolar;
	}

	public void keyPressed(KeyEvent e) {
		
		sistemaSolar.camera.setxEye(0.0f);
		sistemaSolar.camera.setyEye(0.0f);
		sistemaSolar.camera.setzEye(0.0f);
		
		sistemaSolar.camera.setxCenter(0.0f);
		sistemaSolar.camera.setyCenter(0.0f);
		sistemaSolar.camera.setzCenter(0.0f);

		sistemaSolar.camera.setPosition('F');
				
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
			break;

		case KeyEvent.VK_S:
			sistemaSolar.camera.setPosition('S');
			break;

		case KeyEvent.VK_T:
			sistemaSolar.camera.setPosition('T');			
			break;

		case KeyEvent.VK_R:
			sistemaSolar.camera.setxEye(-0.01f);
			sistemaSolar.camera.setyEye(30.0f);
			sistemaSolar.camera.setzEye(0.01f);
			break;

		case KeyEvent.VK_1:		
			sistemaSolar.camera.setzEye(25.0f);
			break;

		case KeyEvent.VK_2:
			sistemaSolar.camera.setzEye(-25.0f);
			break;

		case KeyEvent.VK_3:
			sistemaSolar.camera.setxEye(25.0f);
			sistemaSolar.camera.setyEye(10.0f);	
			break;

		case KeyEvent.VK_4:
			sistemaSolar.camera.setxEye(-15.0f);
			break;
		case KeyEvent.VK_P:
			sistemaSolar.setAnimated(false);;
			break;
		case KeyEvent.VK_C:
			sistemaSolar.setAnimated(true);;
			break;
		}



		sistemaSolar.display();
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}

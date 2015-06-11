package br.com.furb.sistemasolar.texture;

import java.nio.ByteBuffer;

public class Texture {
    ByteBuffer imageData;  // Image Data (Up To 32 Bits)
    int bpp;      // Image Color Depth In Bits Per Pixel
    int width;      // Image width
    int height;      // Image height
    int[] texID = new int[1];  // Texture ID Used To Select A Texture
    int type;      // Image Type (GL_RGB, GL_RGBA)
    
	public ByteBuffer getImageData() {
		return imageData;
	}
	
	public int getBpp() {
		return bpp;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getTexID() {
		return texID;
	}
	
	public int getType() {
		return type;
	}
    
    
    
}


package br.com.furb.sistemasolar.texture;

import com.sun.opengl.util.BufferUtil;

import java.nio.ByteBuffer;
 
class TGA {
    // First 6 Useful Bytes From The Header
    ByteBuffer header = BufferUtil.newByteBuffer(6);                
    // Holds Number Of Bytes Per Pixel Used In The TGA File
    int bytesPerPixel;              
    // Used To Store The Image Size When Setting Aside Ram
    int imageSize;                
    int temp;    // Temporary Variable
    int type;           
    int height;    // height of Image
    int width;    // width ofImage
    int bpp;    // Bits Per Pixel
}

package com.didig.drawing;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;


public class Quad 
{

   private FloatBuffer mVertexBuffer;
   private FloatBuffer mTextureBuffer;
   private ByteBuffer mIndexBuffer;
   
   private int[] textures = new int[1];

    private float vertices[] = 
    {
       -1.0f, -1.0f, 1.0f,
       1.0f, -1.0f, 1.0f,
       -1.0f, 1.0f, 1.0f,
       1.0f, 1.0f, 1.0f,  
    };
     
    private float texture[] = 
    {
       0.0f, 0.0f,
       0.0f, .25f,
       .25f, 0.0f,
       .25f, .25f, 
    };

    private byte indices[] = 
    {
       0,1,3, 0,3,2,
    };

   /**
    * Default CTOR
    */
   public Quad() 
   {

      ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
      byteBuf.order(ByteOrder.nativeOrder());
      mVertexBuffer = byteBuf.asFloatBuffer();
      mVertexBuffer.put(vertices);
      mVertexBuffer.position(0);

      byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
      byteBuf.order(ByteOrder.nativeOrder());
      mTextureBuffer = byteBuf.asFloatBuffer();
      mTextureBuffer.put(texture);
      mTextureBuffer.position(0);

      mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
      mIndexBuffer.put(indices);
      mIndexBuffer.position(0);
   }


   public void draw(GL10 gl) 
   {
      gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
      
      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
      gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

      gl.glFrontFace(GL10.GL_CCW);
      
      gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
      gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
      
      gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);      
      
      gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
      gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
   }
   

   public void loadGLTexture(GL10 gl, Context context) 
   {
      //Get the texture from the Android resource directory
      InputStream is = context.getResources().openRawResource(R.drawable.btn_default);
      Bitmap bitmap = null;
      try 
      {
         bitmap = BitmapFactory.decodeStream(is);
      } 
      finally 
      {
         try 
         {
            is.close();
            is = null;
         } 
         catch (IOException e) {}
      }

      //Generate one texture pointer...
      gl.glGenTextures(1, textures, 0);
      //...and bind it to our array
      gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
      
      //Create Nearest Filtered Texture
      gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
      gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

      //Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
      gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
      gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
      
      //Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
      GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

      bitmap.recycle();
   }
}


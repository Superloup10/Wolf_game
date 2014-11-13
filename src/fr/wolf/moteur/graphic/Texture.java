package fr.wolf.moteur.graphic;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture
{
    public int id;

    public int width;

    public int height;

    private Texture(int id, int width, int height)
    {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public static Texture loadTexture(String name)
    {
        // Load the image
        BufferedImage bimg = null;
        try
        {
            bimg = ImageIO.read(Texture.class.getResource(name));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Unable to load Texture: " + name);
        }

        // Gather all the pixels
        int[] pixels = new int[bimg.getWidth() * bimg.getHeight()];
        bimg.getRGB(0, 0, bimg.getWidth(), bimg.getHeight(), pixels, 0, bimg.getWidth());

        // Create a ByteBuffer
        ByteBuffer buffer = BufferUtils.createByteBuffer(bimg.getWidth() * bimg.getHeight() * 4);

        // Iterate through all the pixels and add them to the ByteBuffer
        for(int y = 0; y < bimg.getHeight(); y++)
        {
            for(int x = 0; x < bimg.getWidth(); x++)
            {
                // Select the pixel
                int pixel = pixels[y * bimg.getWidth() + x];
                // Add the RED component
                buffer.put((byte)((pixel >> 16) & 0xFF));
                // Add the GREEN component
                buffer.put((byte)((pixel >> 8) & 0xFF));
                // Add the BLUE component
                buffer.put((byte)(pixel & 0xFF));
                // Add the ALPHA component
                buffer.put((byte)((pixel >> 24) & 0xFF));
            }
        }

        // Reset the read location in the buffer so that GL can read from
        // beginning.
        buffer.flip();

        // Generate a texture ID
        int textureID = glGenTextures();
        // Bind the ID to the context
        glBindTexture(GL_TEXTURE_2D, textureID);

        // Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        // Send texture data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, bimg.getWidth(), bimg.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        // Return a new Texture.
        return new Texture(textureID, bimg.getWidth(), bimg.getHeight());
    }
}
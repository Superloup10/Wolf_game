package fr.wolf.moteur.graphic;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import fr.wolf.engine.Sprite;

public class Renderer
{
    public static void renderSquare(int x, int y)
    {
        glBegin(GL_QUADS);
        {
            glColor3f(1.0F, 1.0F, 1.0F);
            glVertex2f(x, y);
            glVertex2f(x + 32, y);
            glVertex2f(x + 32, y + 32);
            glVertex2f(x, y + 32);
        }
        glEnd();
    }

    public static void renderTexturedSquare(Sprite sprite, int x, int y)
    {
        /*
         * glBegin(GL_QUADS); { glColor3f(1.0f, 1.0f, 1.0f); glTexCoord2d((double)(sprite.x * 32) / sprite.sheet.texture.width, (double)(sprite.y * 32) / sprite.sheet.texture.height); glVertex2f(x, y); glTexCoord2d((double)(sprite.x * 32 + sprite.size) / sprite.sheet.texture.width, (double)(sprite.y * 32) / sprite.sheet.texture.height); glVertex2f(x + 32, y); glTexCoord2d((double)(sprite.x * 32 +
         * sprite.size) / sprite.sheet.texture.width, (double)(sprite.y * 32 + sprite.size) / sprite.sheet.texture.height); glVertex2f(x + 32, y + 32); glTexCoord2d((double)(sprite.x * 32) / sprite.sheet.texture.width, (double)(sprite.y * 32 + sprite.size) / sprite.sheet.texture.height); glVertex2f(x, y + 32); } glEnd();
         */
    }
}
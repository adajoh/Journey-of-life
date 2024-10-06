
/* Decompiler 5ms, total 167ms, lines 24 */
package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Util {
	public static final int WORLD_WIDTH = 20;
	public static final int WORLD_HEIGHT = 10;
	public static final int targetWidth = 800;
	public static final int targetHeight = 600;
	public static final int hudBottom = 100;
	public static float worldToScreenScaleX = 40.0F;
	public static float worldToScreenScaleY = 50.0F;

	public static void Draw(SpriteBatch batch, TextureRegion texture, float x, float y, float width, float height) {
		batch.draw(texture, x * worldToScreenScaleX, y * worldToScreenScaleY + 100.0F, width * worldToScreenScaleX,
				height * worldToScreenScaleY);
	}

	public static void log(String string) {
		Gdx.app.log("LD24", string);
	}
}

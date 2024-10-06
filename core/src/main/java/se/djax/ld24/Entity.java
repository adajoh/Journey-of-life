
/* Decompiler 5ms, total 177ms, lines 25 */
package se.djax.ld24;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	public float x = 1.0F;
	public float y = 1.0F;
	public float width = 1.0F;
	public float height = 1.0F;
	public Rectangle collisionRectangle = new Rectangle(1.0F, 1.0F, 1.0F, 1.0F);
	public TextureRegion texture;

	public Entity() {
		this.texture = new TextureRegion(Assets.wireframe);
	}

	public void updateCollisionRect() {
		this.collisionRectangle.x = this.x;
		this.collisionRectangle.y = this.y;
		this.collisionRectangle.height = this.height;
		this.collisionRectangle.width = this.width;
	}
}

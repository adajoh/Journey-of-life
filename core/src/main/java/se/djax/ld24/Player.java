package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	TextureRegion[] spritesheet;

	int currentFrame = 0;

	float timeInCurrentFrame;

	boolean goingRight;

	int level;

	float walkSoundTime;

	public Player(int level) {
		this.spritesheet = new TextureRegion[3];
		loadSprites(level);
		this.texture = this.spritesheet[0];
		this.level = level;
	}

	private void loadSprites(int level) {
		String path = "art/spritesheet_" + level + ".png";
		Texture sheet = new Texture(Gdx.files.internal(path));
		this.spritesheet[0] = new TextureRegion(sheet, 0, 0, 32, 32);
		this.spritesheet[1] = new TextureRegion(sheet, 33, 0, 32, 32);
		this.spritesheet[2] = new TextureRegion(sheet, 66, 0, 32, 32);
	}

	public void update(float rawDeltaTime) {
		handleInput();
		if (this.level != 3)
			clamp();
		if (this.level == 3) {
			if (this.y < 0.0F)
				this.y = 0.0F;
			if (this.x < 0.0F)
				this.x = 0.0F;
			if (this.y > 48.0F)
				this.y = 48.0F;
			if (this.x > 48.0F)
				this.x = 48.0F;
		}
		updateCollisionRect();
		handleAnimation(rawDeltaTime);
	}

	private void handleAnimation(float rawDeltaTime) {
		this.timeInCurrentFrame += rawDeltaTime;
		if (Gdx.input.isKeyPressed(22))
			this.goingRight = true;
		if (Gdx.input.isKeyPressed(21))
			this.goingRight = false;
		if (Gdx.input.isKeyPressed(22) || Gdx.input.isKeyPressed(21) || Gdx.input.isKeyPressed(19)
				|| Gdx.input.isKeyPressed(20)) {
			this.currentFrame = 1;
			if (this.timeInCurrentFrame > 0.3F)
				this.currentFrame = 2;
			if (this.timeInCurrentFrame > 0.6F)
				this.timeInCurrentFrame = 0.0F;
			this.walkSoundTime += rawDeltaTime;
			if (this.walkSoundTime > 0.5F) {
				Assets.walk.play(0.05F);
				this.walkSoundTime = 0.0F;
			}
		} else {
			this.currentFrame = 0;
			this.timeInCurrentFrame = 0.0F;
		}
		if (this.goingRight && isFlipped())
			this.spritesheet[this.currentFrame].flip(true, false);
		if (!this.goingRight && !isFlipped())
			this.spritesheet[this.currentFrame].flip(true, false);
		this.texture = this.spritesheet[this.currentFrame];
	}

	private boolean isFlipped() {
		if (this.spritesheet[this.currentFrame].getRegionWidth() < 0)
			return true;
		return false;
	}

	private void clamp() {
		if (this.y < 0.0F)
			this.y = 0.0F;
		if (this.y + this.height > 10.0F)
			this.y = 10.0F - this.height;
		if (this.x < 0.0F)
			this.x = 0.0F;
		if (this.x + this.width > 20.0F)
			this.x = 20.0F - this.width;
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(19))
			this.y = (float) (this.y + 0.1D);
		if (Gdx.input.isKeyPressed(20))
			this.y = (float) (this.y - 0.1D);
		if (Gdx.input.isKeyPressed(22))
			this.x = (float) (this.x + 0.1D);
		if (Gdx.input.isKeyPressed(21))
			this.x = (float) (this.x - 0.1D);
	}
}

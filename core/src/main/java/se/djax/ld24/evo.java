package se.djax.ld24;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class evo implements Screen {
	TextureRegion[] spritesheet;

	int currentSprite;

	float evoSpeed = 1.0F;

	float timeInCurrentFrame;

	int state;

	hud hud;

	int level;

	Random random;

	int size = 1;

	boolean every;

	public evo(int level) {
		this.random = new Random();
		this.spritesheet = new TextureRegion[2];
		Texture texture = new Texture(Gdx.files.internal("art/evo_" + level + ".png"));
		this.spritesheet[0] = new TextureRegion(texture, 0, 0, 32, 32);
		this.spritesheet[1] = new TextureRegion(texture, 33, 0, 32, 32);
		this.hud = new hud(0);
		this.hud.setText("Huh? Whats that?");
		this.level = level;
	}

	@Override
	public void render(SpriteBatch batch) {
		this.hud.render(batch);
		if (this.size == 1)
			batch.draw(this.spritesheet[this.currentSprite], 370.0F, 300.0F, 80.0F, 80.0F);
		if (this.size == 2)
			batch.draw(this.spritesheet[this.currentSprite], 330.0F, 260.0F, 160.0F, 160.0F);
	}

	@Override
	public void update(float rawDeltaTime) {
		if (this.state == 0) {
			this.hud.update(rawDeltaTime);
			if (this.hud.textHasBeenRead) {
				this.hud.setText(Texts.texts[10 * this.level]);
				this.state = 1;
			}
		}
		if (this.state == 1) {
			this.hud.update(rawDeltaTime);
			if (this.hud.textHasBeenRead) {
				this.hud.setText("");
				this.state = 2;
			}
		}
		if (this.state == 2) {
			this.timeInCurrentFrame += rawDeltaTime;
			if (this.timeInCurrentFrame > this.evoSpeed) {
				this.evoSpeed -= 0.07F;
				this.timeInCurrentFrame = 0.0F;
				switchFrame();
			}
			if (this.evoSpeed < -3.0F) {
				Assets.evo_1.stop();
				Assets.evo_2.stop();
				Assets.evo.play();
				this.size = 2;
				this.state = 3;
				this.currentSprite = 1;
				this.hud.setText(Texts.texts[1 + 10 * this.level]);
			}
		}
		if (this.state == 3) {
			this.hud.update(rawDeltaTime);
			if (this.hud.textHasBeenRead)
				LdJam.changeScreen(LdJam.NewGameScreenFactory(this.level + 1));
		}
	}

	private void switchFrame() {
		Util.log("speed:" + this.evoSpeed);
		if (this.currentSprite == 0) {
			this.currentSprite = 1;
			if (this.evoSpeed > 0.0F)
				Assets.evo_1.play();
		} else {
			this.currentSprite = 0;
			if (this.evoSpeed > 0.0F)
				Assets.evo_2.play();
		}
	}
}

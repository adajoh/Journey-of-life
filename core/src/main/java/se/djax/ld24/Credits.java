
/* Decompiler 45ms, total 261ms, lines 78 */
package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Credits implements Screen {
	float creditsY = -40.0F;
	float distance = 100.0F;
	float time;
	TextureRegion[] spriteSheet;
	float y2;
	float y3;
	float y4;
	float y5;
	float y6;
	float y7;
	float startDelay;
	boolean objectiveShowed = false;

	public Credits() {
		Assets.musics[6].play();
		this.time = LdJam.timePlaying;
		this.spriteSheet = new TextureRegion[5];
		this.spriteSheet[0] = new TextureRegion(new Texture(Gdx.files.internal("art/spritesheet_1.png")), 0, 0, 32, 32);
		this.spriteSheet[1] = new TextureRegion(new Texture(Gdx.files.internal("art/spritesheet_2.png")), 0, 0, 32, 32);
		this.spriteSheet[2] = new TextureRegion(new Texture(Gdx.files.internal("art/spritesheet_3.png")), 0, 0, 32, 32);
		this.spriteSheet[3] = new TextureRegion(new Texture(Gdx.files.internal("art/spritesheet_4.png")), 0, 0, 32, 32);
		this.spriteSheet[4] = new TextureRegion(new Texture(Gdx.files.internal("art/spritesheet_5.png")), 0, 0, 32, 32);
	}

	@Override
	public void render(SpriteBatch batch) {
		this.y2 = this.creditsY - this.distance;
		this.y3 = this.creditsY - this.distance * 2.0F;
		this.y4 = this.creditsY - this.distance * 2.0F - 20.0F;
		this.y5 = this.creditsY - this.distance * 4.0F;
		this.y6 = this.creditsY - this.distance * 4.0F - 20.0F;
		this.y7 = this.creditsY - this.distance * 6.0F;
		if (this.objectiveShowed) {
			Assets.game_font.draw(batch, Texts.texts[113], 50.0F, this.creditsY);
			Assets.game_font.draw(batch, Texts.texts[114], 50.0F, this.y2);
			Assets.game_font.draw(batch, Texts.texts[115], 50.0F, this.y3);
			Assets.game_font.draw(batch, Texts.texts[116], 50.0F, this.y4);
			Assets.game_font.draw(batch, Texts.texts[117], 50.0F, this.y5);
			Assets.game_font.draw(batch, Texts.texts[118], 50.0F, this.y6);
			Assets.game_font.draw(batch, Texts.texts[119], 50.0F, this.y7);
			batch.draw(this.spriteSheet[0], 400.0F, this.y2 - 20.0F, 32.0F, 32.0F);
			batch.draw(this.spriteSheet[1], 400.0F, this.y3 - 30.0F, 32.0F, 32.0F);
			batch.draw(this.spriteSheet[2], 430.0F, this.y3 - 30.0F, 32.0F, 32.0F);
			batch.draw(this.spriteSheet[3], 400.0F, this.y5 - 20.0F, 32.0F, 32.0F);
			batch.draw(this.spriteSheet[4], 400.0F, this.y7 - 20.0F, 32.0F, 32.0F);
		} else {
			batch.draw(Assets.credits, 0.0F, 0.0F, 800.0F, 600.0F);
		}

	}

	@Override
	public void update(float rawDeltaTime) {
		Texts.texts[114] = "TIME: " + (int) this.time / 60 + " MIN, SCORE: " + LdJam.score;
		if (this.objectiveShowed) {
			this.creditsY = (float) (this.creditsY + 0.5D);
		} else {
			this.startDelay += rawDeltaTime;
			if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F) {
				this.objectiveShowed = true;
				Assets.musics[6].play();
			}
		}

		if (this.creditsY > 1250.0F) {
			Assets.stopMusic();
			LdJam.changeScreen(new Intro());
		}

	}
}

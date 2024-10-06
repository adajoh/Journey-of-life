package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class game_5 implements Screen {
	float startDelay;

	boolean objectiveShowed = false;

	hud hud;

	float trend;

	float trend2 = -400.0F;

	int state = 1;

	Rectangle mouse;

	Player player;

	Rectangle invest_ea;

	Rectangle invest_greece;

	Rectangle invest_notch;

	Rectangle sell;

	float arrowY = 170.0F;

	int score;

	public game_5() {
		Assets.musics[4].play();
		this.hud = new hud(5);
		this.invest_ea = new Rectangle(50.0F, 410.0F, 360.0F, 65.0F);
		this.invest_greece = new Rectangle(50.0F, 300.0F, 360.0F, 65.0F);
		this.invest_notch = new Rectangle(50.0F, 190.0F, 360.0F, 65.0F);
		this.sell = new Rectangle(50.0F, 410.0F, 360.0F, 65.0F);
		this.mouse = new Rectangle(0.0F, 0.0F, 10.0F, 10.0F);
		this.player = new Player();
	}

	@Override
	public void render(SpriteBatch batch) {
		if (this.objectiveShowed) {
			batch.draw(Assets.trend_5, this.trend, 100.0F, 1600.0F, 500.0F);
			if (this.state == 1)
				batch.draw(Assets.backgound_5_1, 0.0F, 100.0F, 800.0F, 500.0F);
			if (this.state == 2) {
				batch.draw(Assets.backgound_5_2, 0.0F, 100.0F, 800.0F, 500.0F);
				batch.draw(Assets.bar_5, 80.0F, 180.0F, 32.0F, 200.0F);
				batch.draw(Assets.arrow_5, 105.0F, this.arrowY, 16.0F, 16.0F);
			}
			batch.draw(this.player.texture, 570.0F, 280.0F, 100.0F, 100.0F);
			Assets.game_font.draw(batch, Texts.texts[108], this.trend * 10.0F, 120.0F);
			Assets.game_font.draw(batch, Texts.texts[108], this.trend2 * 4.0F, 150.0F);
			this.hud.render(batch);
		} else {
			batch.draw(Assets.objective_5, 0.0F, 0.0F, 800.0F, 600.0F);
		}
	}

	@Override
	public void update(float rawDeltaTime) {
		if (this.objectiveShowed) {
			this.trend -= 0.1F;
			this.trend2 += 0.1F;
			if (this.trend < -400.0F)
				this.trend = 0.0F;
			if (this.trend2 > 0.0F)
				this.trend2 = -400.0F;
			if (Gdx.input.isTouched()) {
				this.mouse.x = Gdx.input.getX();
				this.mouse.y = (600 - Gdx.input.getY());
			} else {
				this.mouse.x = 0.0F;
				this.mouse.y = 0.0F;
			}
			if (this.state == 1) {
				if (this.invest_ea.overlaps(this.mouse)) {
					Assets.choice_5.play();
					Assets.stopMusic();
					LdJam.changeScreen(new lose(5, Texts.texts[109]));
				}
				if (this.invest_greece.overlaps(this.mouse)) {
					Assets.choice_5.play();
					Assets.stopMusic();
					LdJam.changeScreen(new lose(5, Texts.texts[110]));
				}
				if (this.invest_notch.overlaps(this.mouse)) {
					this.state = 2;
					this.player.texture = this.player.tileSheet[1];
					Assets.choice_5.play();
				}
			}
			if (this.state == 2) {
				this.arrowY += 7.0F;
				if (this.arrowY > 373.0F)
					this.arrowY = 170.0F;
				this.score = (int) (this.arrowY - 170.0F - 103.0F);
				if (this.sell.overlaps(this.mouse)) {
					Assets.choice_5.play();
					Assets.stopMusic();
					if (this.score > 0) {
						LdJam.score = this.score;
						LdJam.changeScreen(new win(5, Texts.texts[112]));
					} else {
						LdJam.changeScreen(new lose(5, Texts.texts[111]));
					}
				}
			}
		} else {
			this.startDelay += rawDeltaTime;
			if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F)
				this.objectiveShowed = true;
		}
	}

	private class Player extends Entity {
		TextureRegion[] tileSheet;

		public Player() {
			this.tileSheet = new TextureRegion[3];
			this.tileSheet[0] = new TextureRegion(Assets.spritesheet_5, 0, 0, 32, 32);
			this.tileSheet[1] = new TextureRegion(Assets.spritesheet_5, 33, 0, 32, 32);
			this.texture = this.tileSheet[0];
		}
	}
}

package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class hud {
	int level = 99;

	String text;

	int currentChar;

	float timeCurrentChar;

	public boolean textHasBeenRead = false;

	public hud(int i) {
		this.level = i;
		this.currentChar = 5;
	}

	public void render(SpriteBatch batch) {
		batch.draw(Assets.hud, 0.0F, 0.0F, 800.0F, 100.0F);
		if (this.level == 0) {
			Assets.game_font.draw(batch, this.text.subSequence(0, this.currentChar), 10.0F, 80.0F);
			if (this.currentChar == this.text.length() && this.text != "")
				batch.draw(Assets.hud_confirm, 750.0F, 40.0F, 30.0F, 30.0F);
		}
		if (this.level == 1) {
			Assets.game_font.draw(batch, "Year: 0", 15.0F, 70.0F);
			Assets.game_font.draw(batch, "Continues: " + LdJam.continues, 160.0F, 70.0F);
			Assets.game_font.draw(batch, "Time: " + (20 - (int) game_1.winningTime), 350.0F, 70.0F);
			batch.draw(Assets.keypad, 720.0F, 20.0F, 50.0F, 50.0F);
		}
		if (this.level == 2) {
			Assets.game_font.draw(batch, "Year: 548", 15.0F, 70.0F);
			Assets.game_font.draw(batch, "Continues: " + LdJam.continues, 160.0F, 70.0F);
			Assets.game_font.draw(batch, "Time: " + (20 - (int) game_2.winningTime), 350.0F, 70.0F);
			batch.draw(Assets.food_2, 20.0F, 15.0F, 30.0F, 30.0F);
			batch.draw(Assets.keypad, 720.0F, 20.0F, 50.0F, 50.0F);
		}
		if (this.level == 3) {
			Assets.game_font.draw(batch, "Year: 1420", 15.0F, 70.0F);
			Assets.game_font.draw(batch, "Continues: " + LdJam.continues, 160.0F, 70.0F);
			Assets.game_font.draw(batch, "Time: " + (40 - (int) game_3.winningTime), 350.0F, 70.0F);
			batch.draw(Assets.keypad, 720.0F, 20.0F, 50.0F, 50.0F);
		}
		if (this.level == 4) {
			Assets.game_font.draw(batch, "Year: 1875", 15.0F, 70.0F);
			Assets.game_font.draw(batch, "Continues: " + LdJam.continues, 160.0F, 70.0F);
			Assets.game_font.draw(batch, "Time: " + (30 - (int) game_4.winningTime), 350.0F, 70.0F);
			batch.draw(Assets.mouse, 720.0F, 20.0F, 50.0F, 50.0F);
		}
		if (this.level == 5) {
			Assets.game_font.draw(batch, "Year: Present day", 15.0F, 70.0F);
			Assets.game_font.draw(batch, "Continues: " + LdJam.continues, 250.0F, 70.0F);
			batch.draw(Assets.mouse, 720.0F, 20.0F, 50.0F, 50.0F);
		}
	}

	public void setText(String text) {
		this.text = text;
		this.textHasBeenRead = false;
		this.currentChar = 0;
	}

	public void update(float rawDeltaTime) {
		if (this.level == 0) {
			this.timeCurrentChar += rawDeltaTime;
			if (this.timeCurrentChar > 0.2D) {
				this.timeCurrentChar = 0.0F;
				this.currentChar++;
				if (this.currentChar > this.text.length()) {
					this.currentChar = this.text.length();
					if (Gdx.input.isKeyPressed(62))
						Util.log("next text");
				}
			}
		}
		if (this.currentChar == this.text.length() && Gdx.input.isKeyPressed(62))
			this.textHasBeenRead = true;
	}
}

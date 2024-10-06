
/* Decompiler 30ms, total 192ms, lines 43 */
package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class lose implements Screen {
	float startDelay;
	String text;
	int level;

	public lose(int level, String text) {
		this.level = level;
		this.text = text;
		--LdJam.continues;
		Assets.darwin_fail.play();
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.lose, 0.0F, 0.0F, 800.0F, 600.0F);
		Assets.game_font.draw(batch, this.text, 70.0F, 200.0F);
		Assets.game_font.draw(batch, "Continues: " + LdJam.continues, 330.0F, 90.0F);
		if (LdJam.continues == 0) {
			Assets.game_font.draw(batch, "PRESS SPACE TO RETURN TO BEGINNGING OF TIME", 230.0F, 50.0F);
		} else {
			Assets.game_font.draw(batch, "PRESS SPACE TO GIVE LIFE ANOTHER CHANCE", 180.0F, 50.0F);
		}

	}

	@Override
	public void update(float rawDeltaTime) {
		this.startDelay += rawDeltaTime;
		if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F) {
			Assets.stopMusic();
			if (LdJam.continues == 0) {
				LdJam.changeScreen(new Intro());
			} else {
				LdJam.changeScreen(LdJam.NewGameScreenFactory(this.level));
			}
		}

	}
}

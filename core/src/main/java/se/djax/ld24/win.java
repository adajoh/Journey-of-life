
/* Decompiler 14ms, total 158ms, lines 35 */
package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class win implements Screen {
	int level;
	float startDelay;
	String text;

	public win(int level, String text) {
		this.level = level;
		this.text = text;
		Assets.darwin_win.play();
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.win, 0.0F, 0.0F, 800.0F, 600.0F);
		Assets.game_font.draw(batch, this.text, 210.0F, 150.0F);
	}

	@Override
	public void update(float rawDeltaTime) {
		this.startDelay += rawDeltaTime;
		if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F) {
			Assets.stopMusic();
			if (this.level != 5) {
				LdJam.changeScreen(new evo(this.level));
			} else {
				LdJam.changeScreen(new Credits());
			}
		}

	}
}

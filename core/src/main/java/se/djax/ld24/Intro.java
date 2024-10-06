
/* Decompiler 44ms, total 221ms, lines 42 */
package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Intro implements Screen {
	float startDelay;
	int state = 1;

	public Intro() {
		LdJam.continues = 5;
		LdJam.timePlaying = 0.0F;
		LdJam.score = 0;
	}

	@Override
	public void render(SpriteBatch batch) {
		if (this.state == 1) {
			batch.draw(Assets.intro, 0.0F, 0.0F, 800.0F, 600.0F);
		}

		if (this.state == 2) {
			batch.draw(Assets.menu, 0.0F, 0.0F, 800.0F, 600.0F);
		}

	}

	@Override
	public void update(float rawDeltaTime) {
		this.startDelay += rawDeltaTime;
		if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F && this.state == 1) {
			this.state = 2;
			this.startDelay = 0.0F;
			Assets.musics[5].play();
		}

		if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F && this.state == 2) {
			Assets.stopMusic();
			LdJam.changeScreen(new game_1());
		}

	}
}

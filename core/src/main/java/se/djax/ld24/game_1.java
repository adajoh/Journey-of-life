package se.djax.ld24;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class game_1 implements Screen {
	Player player;

	Random random;

	ArrayList<enemy> enemies;

	public static float winningTime;

	hud hud;

	ParticleEffectManager particleEffectManager;

	float startDelay;

	boolean objectiveShowed = false;

	public game_1() {
		this.particleEffectManager = new ParticleEffectManager();
		this.random = new Random();
		this.player = new Player(1);
		this.enemies = new ArrayList<enemy>();
		this.enemies.add(new enemy());
		Assets.musics[0].play();
		this.hud = new hud(1);
		winningTime = 0.0F;
	}

	@Override
	public void render(SpriteBatch batch) {
		if (this.objectiveShowed) {
			batch.draw(Assets.backgound_1, 0.0F, 100.0F, 800.0F, 500.0F);
			Util.Draw(batch, this.player.texture, this.player.x, this.player.y, this.player.width, this.player.height);
			for (enemy enemy : this.enemies)
				Util.Draw(batch, enemy.texture, enemy.x, enemy.y, enemy.width, enemy.height);
			this.particleEffectManager.render(batch);
			this.hud.render(batch);
		} else {
			batch.draw(Assets.objective_1, 0.0F, 0.0F, 800.0F, 600.0F);
		}
	}

	@Override
	public void update(float rawDeltaTime) {
		if (this.objectiveShowed) {
			winningTime += rawDeltaTime;
			this.player.update(rawDeltaTime);
			this.particleEffectManager.update(rawDeltaTime);
			if (this.random.nextInt(200) == 1)
				this.enemies.add(new enemy());
			for (enemy enemy : this.enemies)
				enemy.update(rawDeltaTime);
			for (enemy enemy : this.enemies) {
				if (enemy.collisionRectangle.overlaps(this.player.collisionRectangle)) {
					Assets.stopMusic();
					Assets.enemy_hit.play();
					LdJam.changeScreen(new lose(1, Texts.texts[100]));
				}
			}
			if (winningTime > 20.0F) {
				Assets.stopMusic();
				LdJam.changeScreen(new win(1, Texts.texts[101]));
			}
		} else {
			this.startDelay += rawDeltaTime;
			if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F)
				this.objectiveShowed = true;
		}
	}

	private class enemy extends Entity {
		float speed = 0.03F;

		float randomTimer;

		int directionX = 1;

		int directionY = 1;

		boolean hunt = false;

		public enemy() {
			this.x = game_1.this.random.nextInt(20) + game_1.this.player.x;
			this.y = game_1.this.random.nextInt(10);
			this.texture = new TextureRegion(Assets.enemy_1);
			game_1.this.particleEffectManager.add(this, Boolean.valueOf(true), "monster_1");
		}

		public void update(float rawDeltaTime) {
			this.randomTimer += rawDeltaTime;
			if (this.randomTimer > 5.0F) {
				this.hunt = game_1.this.random.nextBoolean();
				this.randomTimer = 0.0F;
			}
			if (this.hunt) {
				moveTowardsPlayer();
			} else {
				goRandom();
			}
			clamp();
			updateCollisionRect();
		}

		private void goRandom() {
			this.x += this.speed * this.directionX;
			this.y += this.speed * -this.directionY;
		}

		private void clamp() {
			if (this.y < 0.0F) {
				this.y = 0.0F;
				this.directionY *= -1;
			}
			if (this.y + this.height > 10.0F) {
				this.y = 10.0F - this.height;
				this.directionY *= -1;
			}
			if (this.x < 0.0F) {
				this.x = 0.0F;
				this.directionX *= -1;
			}
			if (this.x + this.width > 20.0F) {
				this.x = 20.0F - this.width;
				this.directionX *= -1;
			}
		}

		private void moveTowardsPlayer() {
			if (game_1.this.player.x > this.x) {
				this.x += this.speed;
			} else {
				this.x -= this.speed;
			}
			if (game_1.this.player.y > this.y) {
				this.y += this.speed;
			} else {
				this.y -= this.speed;
			}
		}
	}
}

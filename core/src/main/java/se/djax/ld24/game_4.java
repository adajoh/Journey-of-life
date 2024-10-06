
/* Decompiler 166ms, total 367ms, lines 235 */
package se.djax.ld24;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class game_4 implements Screen {
	public static float winningTime;
	float startDelay;
	boolean objectiveShowed = false;
	game_4.Player player;
	Random random;
	ArrayList<game_4.Enemy> enemies;
	ArrayList<game_4.Projectile> projectiles;
	ParticleEffectManager particleEffectManager;
	float clouds;
	TextureRegion msPlayer;
	hud hud;
	float childJump1 = 0.2F;
	float childJump2 = 0.3F;

	public game_4() {
		Assets.musics[3].play();
		this.particleEffectManager = new ParticleEffectManager();
		winningTime = 0.0F;
		this.random = new Random();
		this.hud = new hud(4);
		this.enemies = new ArrayList<>();
		this.projectiles = new ArrayList<>();
		this.player = new game_4.Player();
		this.msPlayer = new TextureRegion(new Texture(Gdx.files.internal("art/wife_3.png")));
	}

	@Override
	public void render(SpriteBatch batch) {
		if (this.objectiveShowed) {
			batch.draw(Assets.backgound_4_1, 0.0F, 100.0F, 800.0F, 500.0F);
			Util.Draw(batch, this.player.texture, this.player.x, this.player.y, this.player.width, this.player.height);
			Util.Draw(batch, this.player.spriteSheet[0], 17.0F, 1.0F + this.childJump1, this.player.width / 2.0F,
					this.player.height / 2.0F);
			Util.Draw(batch, this.player.spriteSheet[0], 17.8F, 1.1F + this.childJump2, this.player.width / 2.0F,
					this.player.height / 2.0F);
			Util.Draw(batch, this.player.spriteSheet[0], 16.8F, 0.7F + this.childJump1, this.player.width / 2.0F,
					this.player.height / 2.0F);
			Util.Draw(batch, this.msPlayer, 17.2F, 0.5F, this.player.width, this.player.height);
			Util.Draw(batch, this.player.spriteSheet[0], 18.0F, 0.5F + this.childJump1, this.player.width / 2.0F,
					this.player.height / 2.0F);
			Util.Draw(batch, this.player.spriteSheet[0], 19.0F, 0.8F + this.childJump2, this.player.width / 2.0F,
					this.player.height / 2.0F);
			Util.Draw(batch, this.player.spriteSheet[0], 18.4F, 0.5F + this.childJump1, this.player.width / 2.0F,
					this.player.height / 2.0F);
			Iterator<?> var3 = this.enemies.iterator();

			while (var3.hasNext()) {
				game_4.Enemy enemy = (game_4.Enemy) var3.next();
				Util.Draw(batch, enemy.texture, enemy.x, enemy.y, enemy.width, enemy.height);
			}

			var3 = this.projectiles.iterator();

			while (var3.hasNext()) {
				game_4.Projectile projectile = (game_4.Projectile) var3.next();
				Util.Draw(batch, projectile.texture, projectile.x, projectile.y, projectile.width, projectile.height);
			}

			this.particleEffectManager.render(batch);
			batch.draw(Assets.backgound_4_2, 0.0F, 100.0F, 800.0F, 500.0F);
			batch.draw(Assets.backgound_4_3, this.clouds, this.clouds, 1600.0F, 1000.0F);
			this.hud.render(batch);
		} else {
			batch.draw(Assets.objective_4, 0.0F, 0.0F, 800.0F, 600.0F);
		}

	}

	@Override
	public void update(float rawDeltaTime) {
		if (this.objectiveShowed) {
			winningTime += rawDeltaTime;
			this.clouds -= 0.1F;
			this.player.update(rawDeltaTime);
			Iterator<?> var3 = this.enemies.iterator();

			while (var3.hasNext()) {
				game_4.Enemy enemy = (game_4.Enemy) var3.next();
				enemy.update(rawDeltaTime);
			}

			var3 = this.projectiles.iterator();

			while (var3.hasNext()) {
				game_4.Projectile projectile = (game_4.Projectile) var3.next();
				projectile.update(rawDeltaTime);
			}

			this.particleEffectManager.update(rawDeltaTime);
			if (this.random.nextInt((int) (50.0F - winningTime)) == 1) {
				this.enemies.add(new game_4.Enemy());
				if (this.childJump1 == 0.0F) {
					this.childJump1 = 0.2F;
				} else {
					this.childJump1 = 0.0F;
				}

				if (this.childJump2 == 0.0F) {
					this.childJump2 = 0.3F;
				} else {
					this.childJump2 = 0.0F;
				}
			}

			ArrayList<game_4.Enemy> enemiesToRemove = new ArrayList<Enemy>();
			ArrayList<game_4.Projectile> projectilesToRemove = new ArrayList<Projectile>();
			Iterator<?> var5 = this.enemies.iterator();

			game_4.Enemy enemy;
			while (var5.hasNext()) {
				enemy = (game_4.Enemy) var5.next();
				Iterator<?> var7 = this.projectiles.iterator();

				while (var7.hasNext()) {
					game_4.Projectile projectile = (game_4.Projectile) var7.next();
					if (enemy.collisionRectangle.overlaps(projectile.collisionRectangle)) {
						projectile.y = 100.0F;
						enemiesToRemove.add(enemy);
						projectilesToRemove.add(projectile);
						Assets.enemy_hit.play(0.1F);
					}
				}
			}

			this.enemies.removeAll(enemiesToRemove);
			this.projectiles.removeAll(projectilesToRemove);
			if (winningTime > 30.0F) {
				Assets.stopMusic();
				LdJam.changeScreen(new win(4, Texts.texts[107]));
			}

			var5 = this.enemies.iterator();

			while (var5.hasNext()) {
				enemy = (game_4.Enemy) var5.next();
				if (enemy.collisionRectangle.overlaps(this.player.collisionRectangle)) {
					Assets.stopMusic();
					LdJam.changeScreen(new lose(4, Texts.texts[106]));
				}
			}
		} else {
			this.startDelay += rawDeltaTime;
			if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F) {
				this.objectiveShowed = true;
			}
		}

	}

	private class Enemy extends Entity {
		float speedX = 0.05F;
		float speedY = 0.1F;

		public Enemy() {
			this.x = game_4.this.random.nextInt(10);
			this.y = game_4.this.random.nextInt(8) + 2;
			this.texture = new TextureRegion(Assets.enemy_4);
		}

		public void update(float rawDeltaTime) {
			this.moveTowardsPlayer();
			this.updateCollisionRect();
		}

		private void moveTowardsPlayer() {
			if (game_4.this.player.x > this.x) {
				this.x += this.speedX;
			}

			if (game_4.this.player.y < this.y) {
				this.y -= this.speedY;
			}

		}
	}

	private class Player extends Entity {
		float clickDelay;
		public TextureRegion[] spriteSheet = new TextureRegion[2];

		public Player() {
			this.spriteSheet[0] = new TextureRegion(new Texture(Gdx.files.internal("art/spritesheet_4.png")), 0, 0, 32,
					32);
			this.spriteSheet[1] = new TextureRegion(new Texture(Gdx.files.internal("art/spritesheet_4.png")), 33, 0, 32,
					32);
			this.texture = this.spriteSheet[0];
			this.x = 16.0F;
			this.y = 2.0F;
		}

		public void update(float rawDeltaTime) {
			this.updateCollisionRect();
			this.handleInput(rawDeltaTime);
		}

		private void handleInput(float delta) {
			this.clickDelay += delta;
			if (Gdx.input.isTouched() && this.clickDelay > 0.3F) {
				this.clickDelay = 0.0F;
				game_4.this.projectiles.add(game_4.this.new Projectile());
				this.texture = this.spriteSheet[1];
			} else {
				this.texture = this.spriteSheet[0];
			}

		}
	}

	private class Projectile extends Entity {
		float speed = 0.1F;
		float mX;
		float mY;

		public Projectile() {
			this.mX = Gdx.input.getX() / Util.worldToScreenScaleX;
			this.mY = (500 - Gdx.input.getY()) / Util.worldToScreenScaleY;
			Assets.projectile_4_sound.play(0.1F);
			this.texture = new TextureRegion(Assets.projectile_4);
			this.x = game_4.this.player.x;
			this.y = game_4.this.player.y + 0.5F;
			this.width = 0.5F;
			this.height = 0.5F;
			game_4.this.particleEffectManager.add(game_4.this.player, true, "projectile_4");
			game_4.this.particleEffectManager.add(this, true, "projectile_4_2");
		}

		public void update(float rawDeltaTime) {
			this.x += (this.mX - this.x) * this.speed;
			this.y += (this.mY - this.y) * this.speed;
			this.updateCollisionRect();
		}
	}
}

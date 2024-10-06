package se.djax.ld24;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class game_3 implements Screen {
	public static float winningTime;

	float startDelay;

	hud hud;

	TileMap tileMap;

	Camera camera;

	Player player;

	Random random;

	ArrayList<Npc> npcs;

	boolean isOnNpc;

	int npcTypeStandingOn;

	boolean zoomedOut = false;

	int state = 1;

	public game_3() {
		Assets.musics[2].play();
		winningTime = 0.0F;
		this.random = new Random();
		this.hud = new hud(3);
		this.tileMap = new TileMap();
		this.player = new Player(3);
		this.camera = new Camera();
		this.camera.entityToFollow = this.player;
		this.player.x = 1.0F;
		this.player.y = 0.0F;
		this.npcs = new ArrayList<Npc>();
		this.npcs.add(new Npc(0));
		this.npcs.add(new Npc(0));
		this.npcs.add(new Npc(0));
		this.npcs.add(new Npc(1));
	}

	@Override
	public void render(SpriteBatch batch) {
		if (this.state > 1) {
			this.tileMap.render(batch);
			for (Npc npc : this.npcs)
				renderEntity(batch, npc);
			renderEntity(batch, this.player);
			this.hud.render(batch);
			if (this.isOnNpc)
				Assets.game_font.draw(batch, "Press space to make love", 15.0F, 35.0F);
		}
		if (this.state == 1)
			batch.draw(Assets.objective_3, 0.0F, 0.0F, 800.0F, 600.0F);
	}

	void renderEntity(SpriteBatch batch, Entity entity) {
		batch.draw(entity.texture, (entity.x - this.camera.x) * this.camera.zoom * Util.worldToScreenScaleX,
				(entity.y - this.camera.y) * this.camera.zoom * Util.worldToScreenScaleY + 100.0F,
				entity.width * this.camera.zoom * Util.worldToScreenScaleX,
				entity.height * this.camera.zoom * Util.worldToScreenScaleY);
	}

	@Override
	public void update(float rawDeltaTime) {
		if (this.state == 3) {
			this.player.update(rawDeltaTime);
			this.camera.update();
			winningTime += rawDeltaTime;
			this.isOnNpc = false;
			for (Npc npc : this.npcs) {
				if (npc.collisionRectangle.overlaps(this.player.collisionRectangle)) {
					this.isOnNpc = true;
					this.npcTypeStandingOn = npc.type;
				}
			}
			if (Gdx.input.isKeyPressed(62) && this.isOnNpc && this.npcTypeStandingOn == 1) {
				Assets.stopMusic();
				LdJam.changeScreen(new win(3, Texts.texts[105]));
			}
			if (Gdx.input.isKeyPressed(62) && this.isOnNpc && this.npcTypeStandingOn == 0) {
				Assets.stopMusic();
				LdJam.changeScreen(new lose(3, Texts.texts[104]));
			}
			if (winningTime > 40.0F) {
				Assets.stopMusic();
				LdJam.changeScreen(new lose(3, Texts.texts[120]));
			}
		}
		if (this.state == 2) {
			this.camera.zoom -= 0.005F;
			if (this.camera.zoom < 3.0F) {
				this.camera.zoom = 3.0F;
				this.state = 3;
			}
		}
		if (this.state == 1) {
			this.startDelay += rawDeltaTime;
			if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F)
				this.state = 2;
		}
	}

	private class TileMap {
		game_3.Tile[][] tilemap2;

		public TileMap() {
			Texture tilesheet = new Texture(Gdx.files.internal("art/tile_3.png"));
			TextureRegion tile1 = new TextureRegion(tilesheet, 0, 0, 32, 32);
			TextureRegion tile2 = new TextureRegion(tilesheet, 33, 0, 32, 32);
			this.tilemap2 = new game_3.Tile[50][50];
			for (int x = 0; x < this.tilemap2.length; x++) {
				for (int y = 0; y < (this.tilemap2[x]).length; y++) {
					this.tilemap2[x][y] = new game_3.Tile();
					if (game_3.this.random.nextInt(5) == 1) {
						(this.tilemap2[x][y]).texture = tile2;
					} else {
						(this.tilemap2[x][y]).texture = tile1;
					}
				}
			}
		}

		public void render(SpriteBatch batch) {
			int firstTileX = (int) game_3.this.camera.x;
			int firstTileY = (int) game_3.this.camera.y;
			float offX = game_3.this.camera.x % 1.0F;
			float offY = game_3.this.camera.y % 1.0F;
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 5; y++) {
					try {
						game_3.Tile tile = this.tilemap2[firstTileX + x][firstTileY + y];
						batch.draw(tile.texture, (x - offX) * game_3.this.camera.zoom * Util.worldToScreenScaleX,
								(y - offY) * game_3.this.camera.zoom * Util.worldToScreenScaleY + 100.0F,
								tile.width * game_3.this.camera.zoom * Util.worldToScreenScaleX,
								tile.width * game_3.this.camera.zoom * Util.worldToScreenScaleY);
					} catch (Exception exception) {
					}
				}
			}
		}
	}

	private class Npc extends Entity {
		int type;

		Rectangle collisionRectangle;

		public Npc(int i) {
			this.type = i;
			this.x = game_3.this.random.nextInt(50);
			this.y = game_3.this.random.nextInt(50);
			this.collisionRectangle = new Rectangle(this.x, this.y, this.width, this.height);
			if (i == 1) {
				this.x = (game_3.this.random.nextInt(10) + 20);
				this.y = (game_3.this.random.nextInt(10) + 20);
				this.collisionRectangle = new Rectangle(this.x, this.y, this.width, this.height);
				this.texture = new TextureRegion(new Texture(Gdx.files.internal("art/wife_3.png")));
			} else {
				this.texture = game_3.this.player.spritesheet[0];
			}
		}
	}

	private class Tile extends Entity {
	}

	private class Camera {
		public float x;

		public float y;

		public Entity entityToFollow;

		public float zoom = 8.0F;

		public void update() {
			if (this.entityToFollow.x - this.x > 4.0F)
				this.x = this.entityToFollow.x - 4.0F;
			if (this.entityToFollow.y - this.y > 2.0F)
				this.y = this.entityToFollow.y - 2.0F;
			if (this.x - this.entityToFollow.x > -1.0F)
				this.x = this.entityToFollow.x - 1.0F;
			if (this.y - this.entityToFollow.y > -1.0F)
				this.y = this.entityToFollow.y - 1.0F;
			clamp();
		}

		private void clamp() {
			if (this.x < 0.0F)
				this.x = 0.0F;
			if (this.y < 0.0F)
				this.y = 0.0F;
			if (this.y > 46.0F)
				this.y = 46.0F;
			if (this.x > 43.0F)
				this.x = 43.0F;
		}

		private Camera() {
		}
	}
}

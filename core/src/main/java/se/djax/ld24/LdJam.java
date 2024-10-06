
/* Decompiler 60ms, total 219ms, lines 127 */
package se.djax.ld24;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LdJam implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	float screenWidth;
	float screenHeight;
	public static Screen currentScreen;
	public static int continues = 5;
	public static float timePlaying;
	public static int score;

	@Override
	public void create() {
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800.0F, 600.0F);
		this.camera.update();
		this.batch = new SpriteBatch();
		Assets.Load();
		changeScreen(new Intro());
	}

	@Override
	public void dispose() {
		this.batch.dispose();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render() {
		Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
		Gdx.gl.glClear(16384);
		if (Gdx.input.isKeyPressed(8)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new game_1());
		}

		if (Gdx.input.isKeyPressed(9)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new game_2());
		}

		if (Gdx.input.isKeyPressed(10)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new game_3());
		}

		if (Gdx.input.isKeyPressed(11)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new game_4());
		}

		if (Gdx.input.isKeyPressed(12)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new game_5());
		}

		if (Gdx.input.isKeyPressed(7)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new evo(2));
		}

		if (Gdx.input.isKeyPressed(16)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new win(4, Texts.texts[107]));
		}

		if (Gdx.input.isKeyPressed(15)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new lose(1, "test"));
		}

		if (Gdx.input.isKeyPressed(14)) {
			Assets.Load();
			Assets.stopMusic();
			changeScreen(new Credits());
		}

		timePlaying += Gdx.graphics.getRawDeltaTime();
		currentScreen.update(Gdx.graphics.getRawDeltaTime());
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		currentScreen.render(this.batch);
		this.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public static void changeScreen(Screen screen) {
		Util.log("Changing screen to:" + screen.toString());
		currentScreen = screen;
	}

	public static Screen NewGameScreenFactory(int i) {
		Util.log("Loading gamescreen: " + i);
		if (i == 1) {
			return new game_1();
		} else if (i == 2) {
			return new game_2();
		} else if (i == 3) {
			return new game_3();
		} else if (i == 4) {
			return new game_4();
		} else {
			return i == 5 ? new game_5() : null;
		}
	}
}

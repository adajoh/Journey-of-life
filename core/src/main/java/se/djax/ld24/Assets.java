package se.djax.ld24;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture wireframe;

	public static Texture hud;

	public static Texture hud_confirm;

	public static Texture objective_1;

	public static Texture objective_2;

	public static Texture objective_3;

	public static Texture objective_4;

	public static Texture objective_5;

	public static Texture backgound_1;

	public static Texture backgound_2_1;

	public static Texture backgound_2_2;

	public static Texture backgound_2_3;

	public static Texture backgound_3;

	public static Texture backgound_4_1;

	public static Texture backgound_4_2;

	public static Texture backgound_4_3;

	public static Texture backgound_5_1;

	public static Texture backgound_5_2;

	public static Texture lose;

	public static Texture menu;

	public static Texture intro;

	public static Texture win;

	public static Texture enemy_1;

	public static Texture food_2;

	public static Texture foodbar;

	public static Texture enemy_4;

	public static Texture projectile_4;

	public static Texture trend_5;

	public static Texture spritesheet_5;

	public static Texture bar_5;

	public static Texture arrow_5;

	public static Texture mouse;

	public static Texture keypad;

	public static Texture credits;

	public static BitmapFont game_font;

	public static Music[] musics;

	public static final int MUSIC_GAME_1 = 0;

	public static final int MUSIC_GAME_2 = 1;

	public static final int MUSIC_GAME_3 = 2;

	public static final int MUSIC_GAME_4 = 3;

	public static final int MUSIC_GAME_5 = 4;

	public static final int MUSIC_MENU = 5;

	public static final int MUSIC_CREDITS = 6;

	public static final int MUSIC_EVOLVE = 7;

	public static Sound walk;

	public static Sound enemy_hit;

	public static Sound projectile_4_sound;

	public static Sound darwin_win;

	public static Sound eat_2;

	public static Sound darwin_fail;

	public static Sound choice_5;

	public static Sound evo_1;

	public static Sound evo_2;

	public static Sound evo;

	public static void Load() {
		Texts.loadTexts();
		wireframe = new Texture(Gdx.files.internal("art/wireframe.png"));
		hud = new Texture(Gdx.files.internal("art/hud.png"));
		hud_confirm = new Texture(Gdx.files.internal("art/hud_confirm.png"));
		objective_1 = new Texture(Gdx.files.internal("art/objective_1.png"));
		objective_2 = new Texture(Gdx.files.internal("art/objective_2.png"));
		objective_3 = new Texture(Gdx.files.internal("art/objective_3.png"));
		objective_4 = new Texture(Gdx.files.internal("art/objective_4.png"));
		objective_5 = new Texture(Gdx.files.internal("art/objective_5.png"));
		backgound_1 = new Texture(Gdx.files.internal("art/background_1.png"));
		backgound_2_1 = new Texture(Gdx.files.internal("art/background_2_1.png"));
		backgound_2_2 = new Texture(Gdx.files.internal("art/background_2_2.png"));
		backgound_2_3 = new Texture(Gdx.files.internal("art/background_2_3.png"));
		backgound_4_1 = new Texture(Gdx.files.internal("art/background_4_1.png"));
		backgound_4_2 = new Texture(Gdx.files.internal("art/background_4_2.png"));
		backgound_4_3 = new Texture(Gdx.files.internal("art/background_4_3.png"));
		backgound_5_1 = new Texture(Gdx.files.internal("art/background_5_1.png"));
		backgound_5_2 = new Texture(Gdx.files.internal("art/background_5_2.png"));
		lose = new Texture(Gdx.files.internal("art/lose.png"));
		menu = new Texture(Gdx.files.internal("art/menu.png"));
		intro = new Texture(Gdx.files.internal("art/intro.png"));
		win = new Texture(Gdx.files.internal("art/win.png"));
		enemy_1 = new Texture(Gdx.files.internal("art/enemy_1.png"));
		food_2 = new Texture(Gdx.files.internal("art/food_2.png"));
		foodbar = new Texture(Gdx.files.internal("art/foodbar.png"));
		enemy_4 = new Texture(Gdx.files.internal("art/enemy_4.png"));
		projectile_4 = new Texture(Gdx.files.internal("art/projectile_4.png"));
		trend_5 = new Texture(Gdx.files.internal("art/trend_5.png"));
		spritesheet_5 = new Texture(Gdx.files.internal("art/spritesheet_5.png"));
		bar_5 = new Texture(Gdx.files.internal("art/bar_5.png"));
		arrow_5 = new Texture(Gdx.files.internal("art/arrow_5.png"));
		mouse = new Texture(Gdx.files.internal("art/mouse.png"));
		keypad = new Texture(Gdx.files.internal("art/keypad.png"));
		credits = new Texture(Gdx.files.internal("art/credits.png"));
		Texture texture = new Texture(Gdx.files.internal("fonts/game_font.png"));
		game_font = new BitmapFont(Gdx.files.internal("fonts/game_font.fnt"), new TextureRegion(texture), false);
		// game_font.setScale(0.5F); TODO
		musics = new Music[10];
		musics[0] = Gdx.audio.newMusic(Gdx.files.internal("sound/music_1.mp3"));
		musics[0].setLooping(true);
		musics[1] = Gdx.audio.newMusic(Gdx.files.internal("sound/music_2.mp3"));
		musics[1].setLooping(true);
		musics[1].setVolume(0.2F);
		musics[2] = Gdx.audio.newMusic(Gdx.files.internal("sound/music_3.mp3"));
		musics[2].setLooping(true);
		musics[2].setVolume(0.2F);
		musics[3] = Gdx.audio.newMusic(Gdx.files.internal("sound/music_4.mp3"));
		musics[3].setLooping(true);
		musics[4] = Gdx.audio.newMusic(Gdx.files.internal("sound/music_5.mp3"));
		musics[4].setLooping(true);
		musics[4].setVolume(0.6F);
		musics[5] = Gdx.audio.newMusic(Gdx.files.internal("sound/music_menu.mp3"));
		musics[5].setLooping(true);
		musics[6] = Gdx.audio.newMusic(Gdx.files.internal("sound/music_credits.mp3"));
		musics[6].setLooping(true);
		musics[4].setVolume(0.6F);
		walk = Gdx.audio.newSound(Gdx.files.internal("sound/walk.mp3"));
		enemy_hit = Gdx.audio.newSound(Gdx.files.internal("sound/enemy_hit.mp3"));
		projectile_4_sound = Gdx.audio.newSound(Gdx.files.internal("sound/projectile_4.mp3"));
		darwin_win = Gdx.audio.newSound(Gdx.files.internal("sound/darwin_win.mp3"));
		darwin_fail = Gdx.audio.newSound(Gdx.files.internal("sound/darwin_fail.mp3"));
		eat_2 = Gdx.audio.newSound(Gdx.files.internal("sound/eat_2.mp3"));
		choice_5 = Gdx.audio.newSound(Gdx.files.internal("sound/choice_5.mp3"));
		evo_1 = Gdx.audio.newSound(Gdx.files.internal("sound/evo_1.mp3"));
		evo_2 = Gdx.audio.newSound(Gdx.files.internal("sound/evo_2.mp3"));
		evo = Gdx.audio.newSound(Gdx.files.internal("sound/music_evolve.mp3"));
		Util.log("Loaded assets");
	}

	public static void stopMusic() {
		for (int i = 0; i < musics.length; i++) {
			if (musics[i] != null)
				musics[i].stop();
		}
	}
}

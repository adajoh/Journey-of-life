package se.djax.ld24;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class game_2 implements Screen {
	boolean objectiveShowed = false;

	float startDelay;

	Player player;

	hud hud;

	ArrayList<Food> foods;

	Random random;

	float waves;

	public static float winningTime;

	int hunger = 10;

	float hungerTime;

	public game_2() {
		Assets.musics[1].play();
		winningTime = 0.0F;
		this.random = new Random();
		this.hud = new hud(2);
		this.player = new Player(2);
		this.player.x = 10.0F;
		this.player.y = 5.0F;
		this.foods = new ArrayList<Food>();
		int foodAmount = 5;
		for (int i = 0; i < foodAmount; i++)
			this.foods.add(new Food());
	}

	@Override
	public void render(SpriteBatch batch) {
		if (this.objectiveShowed) {
			this.waves -= 0.1F;
			batch.draw(Assets.backgound_2_1, 0.0F, 100.0F, 800.0F, 500.0F);
			batch.draw(Assets.backgound_2_2, this.waves, this.waves, 1600.0F, 1000.0F);
			batch.draw(Assets.backgound_2_3, 0.0F, 100.0F, 800.0F, 500.0F);
			Util.Draw(batch, this.player.texture, this.player.x, this.player.y, this.player.width, this.player.height);
			for (Food food : this.foods)
				Util.Draw(batch, food.texture, food.x, food.y, food.width, food.height);
			this.hud.render(batch);
			batch.draw(Assets.foodbar, 70.0F, 15.0F, (this.hunger * 15), 30.0F);
		} else {
			batch.draw(Assets.objective_2, 0.0F, 0.0F, 800.0F, 600.0F);
		}
	}

	@Override
	public void update(float rawDeltaTime) {
		if (this.objectiveShowed) {
			winningTime += rawDeltaTime;
			this.player.update(rawDeltaTime);
			if (this.player.x > 16.0F)
				this.player.x = 16.0F;
			if (this.player.x < 3.0F)
				this.player.x = 3.0F;
			if (this.player.y > 8.4F)
				this.player.y = 8.4F;
			if (this.player.y < 1.5F)
				this.player.y = 1.5F;
			this.hungerTime += rawDeltaTime;
			if (this.hungerTime > 1.0F) {
				this.hunger--;
				this.hungerTime = 0.0F;
			}
			if (this.random.nextInt(200) == 1)
				this.foods.add(new Food());
			for (Iterator<Food> iterator = this.foods.iterator(); iterator.hasNext();) {
				Food food = iterator.next();
				if (food.collisionRectangle.overlaps(this.player.collisionRectangle)) {
					this.hunger++;
					Assets.eat_2.play();
					iterator.remove();
				}
			}
			if (this.hunger < 0) {
				Assets.stopMusic();
				LdJam.changeScreen(new lose(2, Texts.texts[102]));
			}
			if (winningTime > 20.0F) {
				Assets.stopMusic();
				LdJam.changeScreen(new win(2, Texts.texts[103]));
			}
		} else {
			this.startDelay += rawDeltaTime;
			if (Gdx.input.isKeyPressed(62) && this.startDelay > 1.0F)
				this.objectiveShowed = true;
		}
	}

	private class Food extends Entity {
		Rectangle collisionRectangle = new Rectangle(this.x, this.y, this.width, this.height);
	}
}

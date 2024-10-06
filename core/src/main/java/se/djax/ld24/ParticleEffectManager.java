package se.djax.ld24;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParticleEffectManager {
	ArrayList<Effect> effects = new ArrayList<Effect>();

	public void render(SpriteBatch batch) {
		for (Effect effect : this.effects)
			effect.draw(batch);
	}

	public void update(float delta) {
		for (Iterator<Effect> iterator = this.effects.iterator(); iterator.hasNext();) {
			Effect effect = iterator.next();
			effect.update(delta);
			if (effect.isComplete())
				iterator.remove();
		}
	}

	public void add(Entity entity, Boolean follow, String effect) {
		this.effects.add(new Effect(entity, follow, effect));
	}

	private class Effect extends ParticleEffect {
		Entity entity;

		public boolean shouldFollow;

		public Effect(Entity entity, Boolean follow, String effect) {
			this.entity = entity;
			this.shouldFollow = follow.booleanValue();
			load(Gdx.files.internal("effects/" + effect + ".p"), Gdx.files.internal("effects"));
			setPosition(entity.x * Util.worldToScreenScaleX, entity.y * Util.worldToScreenScaleY + 100.0F);
			start();
		}

		@Override
		public void update(float delta) {
			if (this.shouldFollow)
				setPosition((this.entity.x + this.entity.width / 2.0F) * Util.worldToScreenScaleX,
						(this.entity.y + this.entity.height / 2.0F) * Util.worldToScreenScaleY + 100.0F);
			super.update(delta);
		}
	}
}

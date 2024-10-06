
/* Decompiler 28ms, total 178ms, lines 60 */
package se.djax.ld24;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParticleEffectManager {
	ArrayList<ParticleEffectManager.Effect> effects = new ArrayList<Effect>();

	public void render(SpriteBatch batch) {
		Iterator<Effect> var3 = this.effects.iterator();

		while (var3.hasNext()) {
			ParticleEffectManager.Effect effect = var3.next();
			effect.draw(batch);
		}

	}

	public void update(float delta) {
		Iterator<Effect> iterator = this.effects.iterator();

		while (iterator.hasNext()) {
			ParticleEffectManager.Effect effect = iterator.next();
			effect.update(delta);
			if (effect.isComplete()) {
				iterator.remove();
			}
		}

	}

	public void add(Entity entity, Boolean follow, String effect) {
		this.effects.add(new ParticleEffectManager.Effect(entity, follow, effect));
	}

	private class Effect extends ParticleEffect {
		Entity entity;
		public boolean shouldFollow;

		public Effect(Entity entity, Boolean follow, String effect) {
			this.entity = entity;
			this.shouldFollow = follow;
			this.load(Gdx.files.internal("effects/" + effect + ".p"), Gdx.files.internal("effects"));
			this.setPosition(entity.x * Util.worldToScreenScaleX, entity.y * Util.worldToScreenScaleY + 100.0F);
			this.start();
		}

		@Override
		public void update(float delta) {
			if (this.shouldFollow) {
				this.setPosition((this.entity.x + this.entity.width / 2.0F) * Util.worldToScreenScaleX,
						(this.entity.y + this.entity.height / 2.0F) * Util.worldToScreenScaleY + 100.0F);
			}

			super.update(delta);
		}
	}
}

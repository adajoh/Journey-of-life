package se.djax.ld24.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import se.djax.ld24.LdJam;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig() {
		// Resizable application, uses available space in browser with no padding:
		// GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(true);
		// cfg.padVertical = 0;
		// cfg.padHorizontal = 0;
		// return cfg;
		// If you want a fixed size application, comment out the above resizable
		// section,
		// and uncomment below:
		return new GwtApplicationConfiguration(800, 600);
	}

	@Override
	public ApplicationListener createApplicationListener() {
		return new LdJam();
	}
}

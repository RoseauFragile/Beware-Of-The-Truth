package bewareofthetruth.map;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {


	public WorldContactListener() {
		super();
	}

	// Called when two fixtures start to collide
	@Override
	public void beginContact(Contact contact) {
		final Fixture fa = contact.getFixtureA();
		final Fixture fb = contact.getFixtureB();

		if(fa == null || fb == null) {
			return;
		}
		if(fa.getUserData() == null || fb.getUserData() == null) {
			return;
		}
	}

	@Override
	public void endContact(Contact contact) {
		final Fixture fa = contact.getFixtureA();
		final Fixture fb = contact.getFixtureB();

		if(fa == null || fb == null) {
			return;
		}
		if(fa.getUserData() == null || fb.getUserData() == null) {
			return;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
}
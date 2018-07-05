package bewareofthetruth.model.gameMechanics.entity.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import bewareofthetruth.model.gameMechanics.entity.mobile.Player;
import bewareofthetruth.model.gameMechanics.tiles.Teleporter;

public class MyContactListener implements ContactListener{

	//TODO ISSOU
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if( fa == null || fb ==null) return;
		if(fa.getUserData() == null || fb.getUserData() == null) return;
		System.out.println("Collision happened");
		if(isContactPlayerWithTeleporter(fa, fb)) {
			Player pa = (Player) fa.getUserData();
			pa.getBewareOfTruthModel().nextLevel();
		}
		
		
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if( fa == null || fb ==null) return;
		if(fa.getUserData() == null || fb.getUserData() == null) return;
		
		System.out.println("Collision stopped");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

	private boolean isContactPlayerWithTeleporter(Fixture a, Fixture b) {
		return (a.getUserData() instanceof Player && b.getUserData() instanceof Teleporter);
		
	}
}

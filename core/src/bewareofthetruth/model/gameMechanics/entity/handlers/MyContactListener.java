package bewareofthetruth.model.gameMechanics.entity.handlers;

import java.sql.SQLException;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import bewareofthetruth.model.gameMechanics.entity.mobile.Player;
import bewareofthetruth.model.gameMechanics.entity.mobile.Zombie;
import bewareofthetruth.model.gameMechanics.tiles.Teleporter;

public class MyContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		if( fa == null || fb ==null) return;
		if(fa.getUserData() == null || fb.getUserData() == null) return;
		System.out.println("Collision happened");
		try {
			this.isContactPlayerWithTeleporter(fa, fb);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.isContactPLayerWithEnnemies(fa, fb);
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

	private void isContactPlayerWithTeleporter(Fixture fa, Fixture fb) throws SQLException {

		if((fb.getUserData() instanceof Player && fa.getUserData() instanceof Teleporter)) {
			Player pa = (Player) fb.getUserData();
			Teleporter ta = (Teleporter) fa.getUserData();
			pa.getBewareOfTruthModel().goToLevel(ta.getIdNextLevel(), ta.getxSpawn(), ta.getySpawn());
			
		}else {
			System.out.println("FALSE");
		}
		
	}
	
	private void isContactPLayerWithEnnemies(Fixture fa, Fixture fb){
		
		if((fa.getUserData() instanceof Player && fb.getUserData() instanceof Zombie)) {
			Player pa = (Player) fa.getUserData();
			Zombie zb = (Zombie) fb.getUserData();
			pa.hit();
			System.out.println("Fa player");
		}else if((fa.getUserData() instanceof Zombie && fb.getUserData() instanceof Player)) {
			Zombie za = (Zombie) fa.getUserData();
			Player pb = (Player) fb.getUserData();
			pb.hit();
			System.out.println("Fb player");
		}else {
			System.out.println("No collision with a zombie");
		}
	}
}

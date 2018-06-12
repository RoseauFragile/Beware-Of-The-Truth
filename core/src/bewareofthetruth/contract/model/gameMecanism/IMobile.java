package bewareofthetruth.contract.model.gameMecanism;

import org.newdawn.slick.geom.Vector2f;

/* **** POUR ALEXIS ET REMI ***** INTERFACE PERMETTANT A UN OBJET DAVOIR UN VECTEUR POSITION ET EFFECTUE DES TRAITEMENTS DESSUS,
 * !!!!! REMARQUE : LORS D'UN DEPLACEMENT VECTORIELLE ON AJOUTE UN VECTEUR A CELUI DEJA EXISTANT !!!!!!
 */
public interface IMobile {
	public void addVector(Vector2f vectorToAdd);

	public Vector2f getPosition();

	public void move();

	public void setPosition(float x, float y);
}

package bewareofthetruth.contract.model.gameMecanism;
import com.badlogic.gdx.math.Vector2;

/* **** POUR ALEXIS ET REMI ***** INTERFACE PERMETTANT A UN OBJET DAVOIR UN VECTEUR POSITION ET EFFECTUE DES TRAITEMENTS DESSUS,
 * !!!!! REMARQUE : LORS D'UN DEPLACEMENT VECTORIELLE ON AJOUTE UN VECTEUR A CELUI DEJA EXISTANT !!!!!!
 */
public interface IMobile {
	public void addVector(Vector2 vectorToAdd);

	public Vector2 getPosition();

	public void move();

	public void setPosition(float x, float y);
}

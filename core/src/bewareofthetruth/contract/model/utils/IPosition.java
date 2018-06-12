package bewareofthetruth.contract.model.utils;

import com.badlogic.gdx.math.Vector2;

/* **** POUR LEA ***** INTERFACE UTILE POUR LES ITEMS JE SUPPOSE,
 * A TOI DE VOIR SI TU PENSES LES VECTEURS NECESSAIRE POUR UN AFFICHAGE STATIQUE
 * COMME DANS UN INVENTAIRE PAR EXEMPLE OU ALORS A LAIDE DE CET INTERFACE */

public interface IPosition {
	public float getMaxX();

	public float getMaxY();

	public float getX();

	public float getY();

	public void setMaxX(float maxX);

	public void setMaxY(float maxY);

	public void setX(float x);

	public void setY(float y);

	public Vector2 getPosition();

	void setVector();

	public void addVector(Vector2 vectorToAdd);
}

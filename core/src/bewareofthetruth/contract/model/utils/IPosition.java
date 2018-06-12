package bewareofthetruth.contract.model.utils;

import org.newdawn.slick.geom.Vector2f;

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

	public Vector2f getPosition();

	void setVector();

	public void addVector(Vector2f vectorToAdd);
}

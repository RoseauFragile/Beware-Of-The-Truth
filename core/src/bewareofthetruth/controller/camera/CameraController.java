package bewareofthetruth.controller.camera;

import com.badlogic.gdx.math.Vector2;

import bewareofthetruth.contract.model.gameMecanism.ICharacter;

public class CameraController {

	/*
	 * Cette classe doit pouvoir ajuster les coordonnées de la caméra par rapport
	 * au personnage joueur.
	 *
	 * ex : le personnage est dans une pièce fermée, et la caméra est centrée
	 * sur le joueur. dès que le joueur s'approche d'un mur, la caméra arrête
	 * d'avancer en direction de ce mur. le but est de ne pas afficher la zone
	 * sombre inutile au-delà du mur ; ne pas oublier de penser aux cas où le
	 * joueur s'avance dans un coin de la pièce.
	 *
	 * Pour obtenir les dimensions de la fenetre de la vue, placez juste des appels
	 * de méthodes lambda, comme getWindowHeight() et getWindowWidth() par exemple
	 * (ou jetez un coupd'oeil dans les classes de Julien pour en trouver)
	 *
	 * - ces méthodes demandent pas mal de réflexion, mais à deux ça devrait le
	 * faire -
	 */

	public CameraController() {
	}

	@SuppressWarnings("unused")
	private void calculateCameraPosition(final ICharacter player) {
	}

	public void setCameraPosition(final float x, final float y) {
	}

	public void setCameraPosition(final Vector2 position) {
	}

}

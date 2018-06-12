package bewareofthetruth.controller.main;

import bewareofthetruth.controller.camera.CameraController;
import bewareofthetruth.controller.character.CharacterController;
import bewareofthetruth.controller.game.GameController;
import bewareofthetruth.controller.interaction.InteractionController;
import bewareofthetruth.controller.inventory.InventoryController;
import bewareofthetruth.controller.item.ItemController;
import bewareofthetruth.controller.movement.MovementController;
import bewareofthetruth.controller.projectile.ProjectileController;
import bewareofthetruth.controller.sound.SoundController;
import bewareofthetruth.controller.status.StatusController;
import bewareofthetruth.controller.ui.UIController;

public class MainController {
	private UIController uiController;
	private CameraController cameraController;
	private CharacterController characterController;
	private GameController gameController;
	private InteractionController interactionController;
	private InventoryController inventoryController;
	private ItemController itemController;
	private MovementController movementController;
	private ProjectileController projectileController;
	private SoundController soundController;
	private StatusController statusController;

	public MainController() {
		this.setUiController(new UIController());
		this.setCameraController(new CameraController());
		this.setCharacterController(new CharacterController());
		this.setGameController(new GameController());
		this.setInteractionController(new InteractionController());
		this.setInventoryController(new InventoryController());
		this.setItemController(new ItemController());
		this.setMovementController(new MovementController());
		this.setProjectileController(new ProjectileController());
		this.setSoundController(new SoundController());
		this.setStatusController(new StatusController());
	}

	/**
	 * @return the uicontroller
	 */
	public UIController getUicontroller() {
		return this.uiController;
	}

	/**
	 * @return the cameraController
	 */
	public CameraController getCameraController() {
		return this.cameraController;
	}

	/**
	 * @return the characterController
	 */
	public CharacterController getCharacterController() {
		return this.characterController;
	}

	/**
	 * @return the gameController
	 */
	public GameController getGameController() {
		return this.gameController;
	}

	/**
	 * @return the interactionController
	 */
	public InteractionController getInteractionController() {
		return this.interactionController;
	}

	/**
	 * @return the inventoryController
	 */
	public InventoryController getInventoryController() {
		return this.inventoryController;
	}

	/**
	 * @return the itemController
	 */
	public ItemController getItemController() {
		return this.itemController;
	}

	/**
	 * @return the movementController
	 */
	public MovementController getMovementController() {
		return this.movementController;
	}

	/**
	 * @return the projectileController
	 */
	public ProjectileController getProjectileController() {
		return this.projectileController;
	}

	/**
	 * @return the soundController
	 */
	public SoundController getSoundController() {
		return this.soundController;
	}

	/**
	 * @return the statusController
	 */
	public StatusController getStatusController() {
		return this.statusController;
	}

	/**
	 * @param uicontroller
	 *            the uicontroller to set
	 */
	private void setUiController(final UIController uiController) {
		this.uiController = uiController;
	}

	/**
	 * @param cameraController
	 *            the cameraController to set
	 */
	private void setCameraController(final CameraController cameraController) {
		this.cameraController = cameraController;
	}

	/**
	 * @param characterController
	 *            the characterController to set
	 */
	private void setCharacterController(final CharacterController characterController) {
		this.characterController = characterController;
	}

	/**
	 * @param gameController
	 *            the gameController to set
	 */
	private void setGameController(final GameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * @param interactionController
	 *            the interactionController to set
	 */
	private void setInteractionController(final InteractionController interactionController) {
		this.interactionController = interactionController;
	}

	/**
	 * @param inventoryController
	 *            the inventoryController to set
	 */
	private void setInventoryController(final InventoryController inventoryController) {
		this.inventoryController = inventoryController;
	}

	/**
	 * @param itemController
	 *            the itemController to set
	 */
	private void setItemController(final ItemController itemController) {
		this.itemController = itemController;
	}

	/**
	 * @param movementController
	 *            the movementController to set
	 */
	private void setMovementController(final MovementController movementController) {
		this.movementController = movementController;
	}

	/**
	 * @param projectileController
	 *            the projectileController to set
	 */
	private void setProjectileController(final ProjectileController projectileController) {
		this.projectileController = projectileController;
	}

	/**
	 * @param soundController
	 *            the soundController to set
	 */
	private void setSoundController(final SoundController soundController) {
		this.soundController = soundController;
	}

	/**
	 * @param statusController
	 *            the statusController to set
	 */
	private void setStatusController(final StatusController statusController) {
		this.statusController = statusController;
	}

}

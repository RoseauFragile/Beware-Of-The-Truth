package bewareofthetruth.controller.status;

import bewareofthetruth.contract.model.gameMecanism.ICharacter;

/*
 *  	***** ILIAS *****
 *  	Cette classe doit permettre de modifier les différents chiffres liés au joueur.
 *  	chaque méthode va changer l'attribut du personnage en question (joueur ou ennemi voire pnj)
 *
 *  	la méthode changeStatus contiendra un switch/case basé sur l'énumération Status
 *
 *  	PS : l'ajout de méthodes supplémentaires est possible, si c'est pour rendre le code + modulable
 *
 */

public class StatusController {

	public StatusController() {
		
	}

	private void changeAmmo(final int amount, final ICharacter character) {
		character.setAmmo(character.getAmmo() + amount);
	}

	private void changeEnergy(final int amount, final ICharacter character) {
		character.setEnergy(character.getEnergy() + amount);
	}

	private void changeHP(final int amount, final ICharacter character) {
		character.setHealth(character.getHealth() + amount);
	}

	public void changeStatus(final Status status, final int amount, final ICharacter character) {
		switch (status) {
		case HP:
			this.changeHP(amount, character);
			break;
		case WATER:
			this.changeWater(amount, character);
			break;
		case AMMO:
			this.changeAmmo(amount, character);
			break;
		case ENERGY:
			this.changeEnergy(amount, character);
			break;
		default:
			System.out.println("ERROR : no status");
		}
	}

	private void changeWater(final int amount, final ICharacter character) {
		character.setWater(character.getWater() + amount);
	}

}

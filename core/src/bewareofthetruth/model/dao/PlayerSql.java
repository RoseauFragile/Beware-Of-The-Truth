package bewareofthetruth.model.dao;

public class PlayerSql {

	private String nom;
	private int idLevel;
	private int idInventaire;
	private int idPlayer;
	private int idChapter;
	
	public PlayerSql(int idPlayer,String nom, int idLevel, int idInventaire,  int idChapter) {
		super();
		this.setIdPlayer(idPlayer);
		this.setNom(nom);
		this.setIdInventaire(idInventaire);
		this.setIdLevel(idLevel);
		this.setIdChapter(idChapter);
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getIdLevel() {
		return this.idLevel;
	}
	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}
	public int getIdInventaire() {
		return this.idInventaire;
	}
	public void setIdInventaire(int idInventaire) {
		this.idInventaire = idInventaire;
	}
	public int getIdPlayer() {
		return this.idPlayer;
	}
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	public int getIdChapter() {
		return idChapter;
	}
	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
	}
	
	
	
	
}

package bewareofthetruth.contract.model.data;

import java.sql.SQLException;

import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.model.dao.BewareOfTheTruthDAO;

public interface IBewareOfTruthModel {

	IChapter getChapter();

	IPlayer getPlayer();

	IOptions getOptions();

	void setOptions(IOptions options);

	IMainMenu getMainMenu();

	void setMainMenu(IMainMenu mainMenu);

	IHud getHud();

	void setHud(IHud hud);

	IGameMenu getGameMenu();

	void setGameMenu(IGameMenu gameMenu);

	public IModelFacade getModelFacade();

	public void setModelFacade(IModelFacade modelFacade);

	public void setChapter(IChapter chapter);

	void setIdChapter(int idChapter);

	int getIdChapter();

	public void setDao(BewareOfTheTruthDAO dao);

	public BewareOfTheTruthDAO getDao();

	void setPlayer(int idLevel) throws SQLException;

}

package bewareofthetruth.contract.model.data;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IChapter {

	ArrayList<ILevel> getLevels();

	IBewareOfTruthModel getBewareOfTruthModel();

	void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel);

	public int getIdChapter();

	public void setIdChapter(int idChapter);

	void setLevel() throws SQLException;

}
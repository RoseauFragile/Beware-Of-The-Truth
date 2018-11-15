package bewareofthetruth.dao;

import java.util.ArrayList;

import bewareofthetruth.entity.EntityFactory;

public class MapDAO extends AbstractDAO{
	
	private int _idChapter;
	private String _MapPath;
	private ArrayList<EntityFactory.EntityName> _entities;
	
	public MapDAO() {
	}

	
	public int get_idChapter() {
		return _idChapter;
	}

	public void set_idChapter(int idChapter) {
		this._idChapter = idChapter;
	}

	public String get_MapPath() {
		return _MapPath;
	}

	public void set_MapPath(String MapPath) {
		this._MapPath = MapPath;
	}

	public ArrayList<EntityFactory.EntityName> get_entities() {
		return _entities;
	}

	public void set_entities(ArrayList<EntityFactory.EntityName> entities) {
		this._entities = entities;
	}

}

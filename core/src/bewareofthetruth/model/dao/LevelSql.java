package bewareofthetruth.model.dao;

public class LevelSql {

	private float	id;
	private float	height;
	private float	width;
	private String	levelName;
	private String  sourceMap;

	public LevelSql(final float id, final String levelName, final float height, final float width, final String sourceMap) {

		super();
		this.setId(id);
		this.setLevelName(levelName);
		this.setHeight(height);
		this.setWidth(width);
		this.setSourceMap(sourceMap);
	}

	public float getWidth() {

		return this.width;
	}

	public void setWidth(final float width) {

		this.width = width;
	}

	@Override
	public String toString() {

		return this.getId() + " : " + this.getLevelName() + " : " + this.getHeight() + " : " + this.getWidth();
	}

	public float getId() {

		return this.id;
	}

	public void setId(final float id) {

		this.id = id;
	}

	public float getHeight() {

		return this.height;
	}

	public void setHeight(final float height) {

		this.height = height;
	}

	public String getLevelName() {

		return this.levelName;
	}

	public void setLevelName(final String levelName) {

		this.levelName = levelName;
	}

	public String getSourceMap() {
		return sourceMap;
	}

	public void setSourceMap(String sourceMap) {
		this.sourceMap = sourceMap;
	}
}
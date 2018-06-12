package bewareofthetruth.contract.model.utils;

import org.newdawn.slick.Image;

public interface ISprite {

	public Image getImage();

	public void setImage(String imageSource);

	public String getSpriteSheet();

	public void setSpriteSheet(String spriteSheetSource);
}

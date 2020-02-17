package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.PieceSquareColor;

public class PieceGui extends ImageView implements ChessPieceGui {

	private PieceSquareColor color;
	public PieceGui(PieceSquareColor pieceSquareColor, Image image) {
		super();
		this.color=pieceSquareColor;
		this.setImage(image);
	}

	@Override
	public PieceSquareColor getCouleur() {
		
		return this.color;
	}

}

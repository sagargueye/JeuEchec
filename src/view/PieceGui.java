package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.PieceSquareColor;

public class PieceGui extends ImageView implements ChessPieceGui {

	private PieceSquareColor color;
	public String type;
	public String nomPiece;
	public Integer pouvoir;
	public PieceGui(PieceSquareColor pieceSquareColor, Image image, String typeval) {
		super();
		this.color=pieceSquareColor;
		this.setImage(image);
		this.type=typeval;

	}
	public PieceGui(PieceSquareColor pieceSquareColor, Image image) {
		super();
		this.color=pieceSquareColor;
		this.setImage(image);

	}
	@Override
	public PieceSquareColor getCouleur() {
		return this.color;
	}
	@Override
	public String getType() {
		return this.type;
	}
}

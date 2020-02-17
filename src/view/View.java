package view;

import java.util.List;

import controller.ChessController;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shared.GUICoord;

public class View extends GridPane implements ChessView {

	Board board;
	
	public View(ChessController chessController) {
		// TODO Auto-generated constructor stub
		super();

		// le damier composé de carrés noirs et blancs
		// sur lesquels sont positionnés des pièces noires ou blanches
		Pane board = new Board(chessController);
			this.board = (Board) board;
		// gestion de la taille du damier
		double height = 800.0;			// TODO - à  remplacer (atelier 4) : bad practice
		board.setPrefSize( height, height);			// TODO - à  remplacer (atelier 4) : bad practice

		// création d'un fond d'écran qui contiendra le damier + les axes (atelier 2)
		BorderPane checkersBoard = new BorderPane();	
		
		// ajout du damier au centre du fond d'écran
		checkersBoard.setCenter(board);
		
		// ajout du fond d'écran à  la vue
		this.add(checkersBoard, 0, 1);
	}

	@Override
	public void setPieceToMoveVisible(GUICoord gUICoord, boolean visible) {
		this.board.setPieceToMoveVisible(gUICoord, visible);
		
	}

	@Override
	public void resetLight(List<GUICoord> gUICoords, boolean isLight) {

		this.board.resetLight(gUICoords, isLight);
	}

	@Override
	public void movePiece(GUICoord initCoord, GUICoord targetCoord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undoMovePiece(GUICoord pieceToMoveInitCoord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPromotionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void promotePiece(GUICoord gUICoord, String promotionType) {
		// TODO Auto-generated method stub
		
	}

}

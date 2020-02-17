package controller;

import model.ChessModel;
import shared.GUICoord;
import shared.PieceSquareColor;
import view.ChessView;


public class ControllerLocal implements ChessControllerModel, ChessControllerView {

	@Override
	public boolean actionsWhenPieceIsSelectedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {
		System.out.println("Coucou 1 ");
			
		return false;
	}

	@Override
	public boolean actionsWhenPieceIsDraggedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {
			System.out.println("Coucou 2 ");
			
		return false;
	}

	@Override
	public void actionsWhenPieceIsMovedOnGui(GUICoord targetCoord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionsWhenPieceIsReleasedOnGui(GUICoord targetCoord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setView(ChessView chessGUI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(ChessModel chessModel) {
		// TODO Auto-generated method stub
		
	}

}

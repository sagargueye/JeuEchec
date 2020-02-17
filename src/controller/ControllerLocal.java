package controller;

import model.ChessModel;
import shared.GUICoord;
import shared.PieceSquareColor;
import view.Board;
import view.ChessView;
import view.PieceGui;


public class ControllerLocal implements ChessControllerModel, ChessControllerView {
	boolean turnPlayer = true;
	public void setTurnPlayer( boolean val) {
		turnPlayer= val;
	}
	public boolean getTurnPlayer() {
		return turnPlayer;
	}
	@Override
	public boolean actionsWhenPieceIsSelectedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {		
	    if( pieceSquareColor.equals(PieceSquareColor.WHITE)  && this.getTurnPlayer()==true ) {
	    	System.out.println("white:cest son tour ");
	    	this.setTurnPlayer(false);
	    	return true;
	    }else if(pieceSquareColor.equals(PieceSquareColor.BLACK)  && this.getTurnPlayer()==false){
	    	System.out.println("black: cest son tour");
	    	this.setTurnPlayer(true);
	    	return true;
	    }
		return false;
	}

	@Override
	public boolean actionsWhenPieceIsDraggedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {
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

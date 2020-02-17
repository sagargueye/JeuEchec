package controller;

import java.util.ArrayList;
import java.util.List;

import model.ChessModel;
import shared.GUICoord;
import shared.PieceSquareColor;
import view.Board;
import view.ChessView;
import view.View;


public class ControllerLocal implements ChessControllerModel, ChessControllerView {

	View view;
	List<GUICoord> corrds = new ArrayList<>();
	boolean turnPlayer = true;
		
		
	@Override
	public boolean actionsWhenPieceIsSelectedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {
		System.out.println("dans action ");
		GUICoord newCoord = null;
	    if( pieceSquareColor.equals(PieceSquareColor.WHITE)  && this.getTurnPlayer()==true ) {
	    	System.out.println("white:cest son tour ");
			for(int i =1; i<4; i++) {
				newCoord = new GUICoord(pieceToMoveCoord.getX(),pieceToMoveCoord.getY()-i);
				corrds.add(newCoord);
			}
	    	this.setTurnPlayer(false);
	    	this.corrds = corrds;
		    this.view.setPieceToMoveVisible(pieceToMoveCoord, true);
			this.view.resetLight(corrds,false);
	    	return true;
	    }
	    else if(pieceSquareColor.equals(PieceSquareColor.BLACK)  && this.getTurnPlayer()==false){
	    	System.out.println("black: cest son tour");
			for(int i =1; i<4; i++) {
				newCoord = new GUICoord(pieceToMoveCoord.getX(),pieceToMoveCoord.getY()+i);
				corrds.add(newCoord);
			}
	    	this.setTurnPlayer(true);
	    	this.corrds = corrds;
		    this.view.setPieceToMoveVisible(pieceToMoveCoord, true);
			this.view.resetLight(corrds,false);
			return true;
	    }
		
		return false;
	}

	private boolean getTurnPlayer() {
		
		return this.turnPlayer;
	}

	private void setTurnPlayer(boolean b) {
		this.turnPlayer = b;
		
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
		System.out.println("LALDZOFDEJFPOIJMFNBFVCPUSEIUFHPUESHGFPUSEHFPIUSHFUESFOUYSGVOUYSBGEOUYESDFSF");
		this.view.setPieceToMoveVisible(targetCoord, false);
		this.view.resetLight(corrds,true);
		corrds = new ArrayList<>();

	}

	@Override
	public void setView(ChessView chessGUI) {
		this.view = (View) chessGUI;
		
	}

	@Override
	public void setModel(ChessModel chessModel) {
		// TODO Auto-generated method stub
		
	}

}

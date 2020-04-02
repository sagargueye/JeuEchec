package controller;

import java.util.ArrayList;
import java.util.List;

import model.ChessModel;
import model.ChessPieceModel;
import model.ModelFactory;
import shared.GUICoord;
import shared.ModelCoord;
import shared.PieceSquareColor;
import view.Board;
import view.ChessView;
import view.View;


public class ControllerLocal implements ChessControllerModel, ChessControllerView {

	View view;
	ChessPieceModel ChessPieceModel;
	List<GUICoord> corrds = new ArrayList<>();
	boolean turnPlayer = true;
	GUICoord coordDepart = null;

		
	public List<GUICoord>	findListCoordPossible( PieceSquareColor pieceSquareColor,GUICoord pieceToMoveCoord){
		List<GUICoord> list= new ArrayList<>();				
		return list;
	}
	@Override
	public boolean actionsWhenPieceIsSelectedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {
		System.out.println("==============================");
		System.out.println("dans Controller");
		System.out.println("dans actionsWhenPieceIsSelectedOnGui");
		System.out.println("ses parametres:");
		System.out.println( pieceSquareColor+" et "+pieceToMoveCoord);


		this.coordDepart=pieceToMoveCoord;
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
	public void actionsWhenPieceIsReleasedOnGui(GUICoord targetCoord, String typePiece) {
		System.out.flush(); //vide le console
		System.out.println("==============================");
		System.out.println("dans encore le controller");
		System.out.println("dans actionsWhenPieceIsReleasedOnGuiencore le controller");
		System.out.println("ses parameter"+ targetCoord);


		this.view.resetLight(corrds,true);
		corrds = new ArrayList<>();
	
		
		char charFromInt=convertIntToChar(targetCoord.getX());
		System.out.println("le x en char: "+ charFromInt);
		ModelCoord ModelCoordXY= new ModelCoord(charFromInt, targetCoord.getY());
		ModelCoord coordDepartXY= new ModelCoord(convertIntToChar(this.coordDepart.getX()), this.coordDepart.getY());
		
		
		System.out.println("ModelCoordXY : "+ ModelCoordXY);
		System.out.println("coordonnees_valides :"+ModelFactory.coordonnees_valides(ModelCoordXY, coordDepartXY, typePiece));
		if( ModelFactory.coordonnees_valides(ModelCoordXY, coordDepartXY, typePiece)) {
			this.view.movePiece(this.coordDepart, targetCoord);
			this.view.setPieceToMoveVisible(targetCoord, false);
			System.out.println(targetCoord);
		}else {
			this.view.undoMovePiece(this.coordDepart);
			this.view.setPieceToMoveVisible(this.coordDepart, false);
			//on doit rejouer si on unmove
			this.setTurnPlayer((this.getTurnPlayer()==true)?false:true);
			System.out.println(targetCoord);
		}
		
	}
	public char convertIntToChar(int i) {
		char val;
		switch(i) {
			case 0:
				val='a';
				break;
			case 1:
				val='b';
				break;
			case 2:
				val='c';
				break;
			case 3:
				val='d';
				break;
			case 4:
				val='e';
				break;
			case 5:
				val='f';
				break;
			case 6:
				val='g';
				break;
			case 7:
				val='h';
				break;
			default:
			val=' ';
			break;
		}
		return val;
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

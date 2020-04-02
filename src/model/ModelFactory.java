package model;


import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import shared.ActionType;
import shared.ModelCoord;
import shared.PieceSquareColor;
import model.ChessPieceModel;

public class ModelFactory {

	public static ObjectProperty<PieceSquareColor> beginColor = new SimpleObjectProperty<PieceSquareColor>(PieceSquareColor.WHITE);
	public static IntegerProperty nbLigne = new SimpleIntegerProperty(8);
	public static IntegerProperty nbColonne = new SimpleIntegerProperty(8);
	public ChessPieceModel ChessPieceModel;

	//////////////////////////////////////////////////////////////////////////////////////
	//
	// fabriques des pièces
	//
	///////////////////////////////////////////////////////////////////////////////////////

	public static List<ChessPieceModel> createPieceModelList() {

		return PieceModelFactory.createPieceModelList();
	}
	public static ChessPieceModel createPiece(PieceSquareColor pieceCouleur, String type, ModelCoord pieceModelCoord){
		return PieceModelFactory.createPiece(pieceCouleur, type, pieceModelCoord);
	}


	//////////////////////////////////////////////////////////////////////////////////////
	//
	// Controle des coordonnées
	//
	///////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @param typePiece 
	 * @param x
	 * @param y
	 * @return true si les coordonnées passées en paramètre sont dans les limites du
	 *         plateau
	 */
	public static boolean coordonnees_valides(ModelCoord coord, ModelCoord coordDepart, String typePiece ) {
		boolean ret = false;
		System.out.println("dans coordonnees_valides param :coord= "+ coord+" et coordDepart= "+ coordDepart+" et typePiece="+typePiece);
		
		
		if (coord != null) {
			int x = coord.getCol() - 'a';
			int y = 7 - coord.getLigne();
			int xdepart = coordDepart.getCol() - 'a';
			int ydepart = 7 - coordDepart.getLigne();
			System.out.println(" x= : "+ x+" et y="+ y);
			System.out.println("nbColonne.get()= : "+ nbColonne.get()+" et nbLigne.get()="+ nbLigne.get());


			//si c'est un roi
			if( typePiece.indexOf("Roi")!=-1 ) {
				System.out.println("c'est un Roi");
				Roi royal=new Roi();
				System.out.println("result move:"+royal.doMove(coordDepart, coord)+":");
				if(royal.doMove(coordDepart, coord)==ActionType.MOVE) {
					ret=true;
				}
			}else
			//ret = (x <= nbColonne.get() - 1) && (x >= 0) && (y <= nbLigne.get()) && (y >= 0);
			if((x <= nbColonne.get() - 1) && (x >= 0) && (y <= nbLigne.get()) && (y >= 0)) {
				if(xdepart==x) {
					ret=true;
				}
			}
			
			
		}
		System.out.println(" return: "+ ret);
		return ret;
	}



}

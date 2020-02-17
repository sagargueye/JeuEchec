package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import hellodraganddrop.*;
import shared.GUICoord;

import java.util.List;

import controller.ChessController;

/**
 * @author francoiseperrin
 * <p>
 * Cette classe repr�sente le damier de la vue
 * <p>
 * Elle tire les valeurs d'affichage d'une fabrique de constante (GuiConfig) :
 * 		public final static int size = 10;
 * 		public final static double height = 600.0;
 * 
 */
public class Board extends GridPane implements ChessView{

	private final ChessController controller;
	private Node selectedPieceGui;					// la pi�ce � d�placer
	private int selectedPieceIndex;						// index de la pi�ce avant d�placement 

	private int nbCol, nbLig;                			// le nb de ligne et de colonne du damier
	private double height;								// taille du damier en pixel

	private EventHandler<MouseEvent> squareListener;   	// l'�couteur d'�v�nements souris sur les carr�s du damier
	private EventHandler<MouseEvent> pieceListener;   	// l'�couteur d'�v�nements souris sur les pi�ces

	public Board (ChessController controller) {
		super();

		this.nbCol = 8;
		this.nbLig = 8;
		this.height = (double) 800.0;

		this.controller = controller;

		//this.squareListener = new SquareListener();
		//this.pieceListener = new PieceListener();
		// initialisation du damier
		this.addSquaresOnCheckersBoard();
		//this.addPiecesOnCheckersBoard();
	}


	private void addSquaresOnCheckersBoard () {

		SquareGui square = null;
		PieceGui piece = null;
		
		shared.PieceSquareColor squareColor = null;
		for (int ligne = 0; ligne < this.nbLig; ligne++) {

			for (int col = 0; col < this.nbCol; col++) {
				// s�lection de la couleur de la case
				if ((col % 2 == 0 && ligne % 2 == 0) || (col % 2 != 0 && ligne % 2 != 0)) {
					squareColor = shared.PieceSquareColor.WHITE;
				} else {
					squareColor = shared.PieceSquareColor.BLACK;
				}


				// cr�ation d'un Pane
				square = (SquareGui) GuiFactory.createSquare(col, ligne);
				piece = (PieceGui) GuiFactory.createPiece(col, ligne);
				
				// ajout d'un �couteur sur le carr�
				//square.setOnMouseClicked(squareListener);
				
				
				square.setOnDragOver(new EventHandler <DragEvent>() {
			            public void handle(DragEvent event) {
			                /* data is dragged over the target */
			                System.out.println("onDragOver");
			                PieceGui piece = (PieceGui) Board.this.getSelectedPiece();
			                Board.this.getChessController().actionsWhenPieceIsDraggedOnGui(piece.getCouleur(), new GUICoord((int) piece.getX(),(int) piece.getY()));
			                /* accept it only if it is  not dragged from the same node 
			                 * and if it has a string data */
			                if (event.getGestureSource() != Board.this.getSelectedPiece().getParent() && event.getDragboard().hasImage()) {
			                    /* allow for both copying and moving, whatever user chooses */
			                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			                    
			                }
			                event.consume();
			            }
			        });

				square.setOnDragEntered(new EventHandler <DragEvent>() {
			            public void handle(DragEvent event) {
			                /* the drag-and-drop gesture entered the target */
			                System.out.println("onDragEntered");
			                /* show to the user that it is an actual gesture target */
			                if (event.getGestureSource() != Board.this.getSelectedPiece().getParent() && event.getDragboard().hasImage()) {

			                }
			                event.consume();
			            }
			        });


				square.setOnDragDropped(new EventHandler <DragEvent>() {
			            public void handle(DragEvent event) {
			                /* data dropped */
			                System.out.println("onDragDropped");
			                /* if there is a string data on dragboard, read it and use it */
			                Dragboard db = event.getDragboard();
			                boolean success = false;
			                if (db.hasImage()) {
				                System.out.println("ALLALLALALALALAL CADEVRAI MARCHER");
			        			((SquareGui) event.getSource()).setCenter(Board.this.getSelectedPiece());
			        			success = true;
			                }
			                event.setDropCompleted(success);

			                
			                /* let the source know whether the string was successfully 
			                 * transferred and used */

			                event.consume();
			            }
			        });
				
				
				
				//piece.setOnMouseClicked(squareListener);
				// gestion de la taille des Pane
				square.setPrefHeight(this.height/this.nbLig);	// TODO - � remplacer : bad practice
				square.setPrefWidth(this.height/this.nbCol);	// TODO - � remplacer : bad practice
				// ajout du carre sur le damier
				
				if(piece != null) {
					piece.setFitHeight(80);
					piece.setFitWidth(60);
					piece.setOnMouseClicked(pieceListener);
					piece.setOnDragDetected(pieceListener);
					piece.setOnDragDetected(new EventHandler <MouseEvent>() {
				            public void handle(MouseEvent event) {
				                /* drag was detected, start drag-and-drop gesture*/
				                System.out.println("onDragDetected");
				                Board.this.setSelectedPiece((Node) event.getSource());
				                boolean monSuperBoolean=Board.this.getChessController().actionsWhenPieceIsSelectedOnGui(((PieceGui) event.getSource()).getCouleur(), new GUICoord((int) ((PieceGui) event.getSource()).getX(),(int)((PieceGui) event.getSource()).getY()));
				                /* allow any transfer mode */
				                System.out.println("arrrrrrive");
				                if(monSuperBoolean==false) {
				                	return;
				                }
				                System.out.println(((SquareGui)((PieceGui) event.getSource()).getParent()).getCoord());
				                Dragboard db = Board.this.startDragAndDrop(TransferMode.ANY);
				                PieceGui piece = (PieceGui) event.getSource();
				                SquareGui square = (SquareGui) piece.getParent();
				                square.getCoord();
				                
				                // Board.this.add(piece,((SquareGui) piece.getParent()).getCoord().getX(),((SquareGui) piece.getParent()).getCoord().getY());
				                /* put a string on dragboard */
				                ClipboardContent content = new ClipboardContent();
				                content.putImage(((ImageView) Board.this.getSelectedPiece()).getImage());
				                db.setContent(content);
				                event.consume();
				            }
				        });

				       

					piece.setOnDragDone(new EventHandler <DragEvent>() {
				            public void handle(DragEvent event) {
				                /* the drag-and-drop gesture ended */
				                System.out.println("onDragDone");
				                /* if the data was successfully moved, clear it */
				                if (event.getTransferMode() == TransferMode.MOVE) {
				        			Board.this.movePiece(
				        					(Canvas) Board.this.getSelectedPiece(), 
				        					(Pane) event.getSource()
				        					);
				                }

				                event.consume();
				            }
				        });
					
					
					//square.getChildren().add(piece);
					//square.getChildren().add(piece);
					square.setCenter(piece);
				}
				this.add(square, col, ligne);

			}
		}
	}


	/**	private Node getSelectedPiece() {
		return this.selectedPieceGui;
	}
	
	private ChessController getChessController() {
		return controller;
	}
	 * @param selectedPiece
	 * @param targetSquare 
	 * Cette m�thode est appel�e par l'�couteur SquareListener
	 * lorsqu'un carr� est cliqu� afin d'y d�poser une pi�ce pr�c�demment s�lectionn�e
	 * on ne v�rifie pas les algos potentiels du d�placement de pi�ces d'un jeu de dame :
	 * pion qui monte, qui peut sauter des pi�ces de couleurs oppos�es, qui est oblig� de prendre, etc.
	 */
	private void movePiece (Canvas selectedPiece, Pane targetSquare) {
		Pane parentSquare;
		if (selectedPiece != null) {
			parentSquare = (Pane)  selectedPiece.getParent();
			targetSquare.getChildren().add(selectedPiece);
			parentSquare.getChildren().removeAll();
		}
	}

	private Node getSelectedPiece() {
		return this.selectedPieceGui;
	}
	
	private ChessController getChessController() {
		return controller;
	}
	/**
	 * @param selectedPieceGui 
	 * Cette m�thode est appel�e par l'�couteur PieceListener
	 * lorsqu'un clic est effectu� sur une pi�ce avant de la d�placer
	 */
	private void setSelectedPiece (Node selectedPieceGui) {
		this.selectedPieceGui =  (Node) selectedPieceGui;
	}



	@Override
	public void setPieceToMoveVisible(GUICoord gUICoord, boolean visible) {
		// TODO Auto-generated method stub
		
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


	@Override
	public void resetLight(List<GUICoord> gUICoords, boolean isLight) {
		// TODO Auto-generated method stub
		
	}



	


}




package model;

import java.util.List;

import shared.ActionType;
import shared.ModelCoord;
import shared.PieceSquareColor;

public class Roi implements ChessPieceModel {
	private String name;
	private String type;
	private PieceSquareColor color;
	private ModelCoord modelCoord;
	public boolean iscapture;
	
	public Roi(PieceSquareColor colore, ModelCoord modelCoord) {
		this.name="Roi";
		this.type="Roi"+colore;
		this.color=colore;
		this.modelCoord=modelCoord;
	}
	public Roi() {
		this.name="Roi";
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	@Override
	public ModelCoord getCoord() {
		return this.modelCoord;
	}
	@Override
	public PieceSquareColor getCouleur() {
		return this.color;
	}
	public boolean getiscapture() {
		return this.iscapture;
	}
	
	public void setIscapture(boolean val) {
		this.iscapture=val;
	}
	/**
	 * @author sagar.gueye 
	 * Le roi se déplace d'une seule case dans n'importe quelle direction
	 */
	@Override
	public ActionType doMove(ModelCoord initCoord, ModelCoord finalCoord) {
		System.out.println("ROI: dans doMove");
		System.out.println("ROI: les param:");
		System.out.println("initCoord "+initCoord);
		System.out.println("finalCoord "+finalCoord);

		ActionType val;
		ModelCoord cordLign1= new ModelCoord( (char) (initCoord.getCol()), (initCoord.getLigne()+1) );
		ModelCoord cordLign2= new ModelCoord( (char) (initCoord.getCol()), (initCoord.getLigne()-1) );
		
		ModelCoord cordCol1= new ModelCoord( (char) (initCoord.getCol()+1), (initCoord.getLigne()) );
		ModelCoord cordCol2= new ModelCoord( (char) (initCoord.getCol()-1), (initCoord.getLigne()) );
		
		ModelCoord cordDiag1= new ModelCoord( (char) (initCoord.getCol()+1), (initCoord.getLigne()+1) );
		ModelCoord cordDiag2= new ModelCoord( (char) (initCoord.getCol()-1), (initCoord.getLigne()+1) );
		ModelCoord cordDiag3= new ModelCoord( (char) (initCoord.getCol()+1), (initCoord.getLigne()-1) );
		ModelCoord cordDiag4= new ModelCoord( (char) (initCoord.getCol()-1), (initCoord.getLigne()-1) );
		
		
		if(finalCoord != null) {
			System.out.println("ROI: coor nest pas null");
			val=ActionType.ILLEGAL;
			char col=finalCoord.getCol();
			//deplacement sur la ligne d'avant ou la ligne dapres
			if( finalCoord.equals(cordLign1) || finalCoord.equals(cordLign2)  ) {
				val=ActionType.MOVE;
			}
			//deplacement sur la colonne suivante ou avant 
			else if( finalCoord.equals(cordCol1) || finalCoord.equals(cordCol2) ) {
				val=ActionType.MOVE;
			}
			//deplacement diagonale sur une case
			else if(finalCoord.equals(cordDiag1) || finalCoord.equals(cordDiag2) || finalCoord.equals(cordDiag3) || finalCoord.equals(cordDiag4) ) {
				val=ActionType.MOVE;
			}
		}else {
			System.out.println("ROI: coor est null");
			System.out.println("this.iscapture"+this.iscapture);
			val=ActionType.UNKNOWN;
		}
		System.out.println("ROI: val="+ val);
		System.out.println("ROI: fin function doMove______________");
		return val;
	}
	
	
	@Override
	public boolean catchPiece() {
		System.out.println("this.iscapture"+this.iscapture);
		if(this.iscapture) {
			return true;
		}
		return false;
	}
	@Override
	public boolean isAlgoMoveOk(ModelCoord coord) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAlgoMoveOk(ModelCoord coord, ActionType type) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<ModelCoord> getMoveItinerary(ModelCoord coord) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean undoLastMove() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean undoLastCatch() {
		// TODO Auto-generated method stub
		return false;
	}

/*
    @Override
    protected boolean moveIsLegal(int newX, int newY) {

        int newPositionX = newX - getX();
        int newPositionY = newY - getY();
        int checkX = this.getX();
        int checkY = this.getY();

        if (super.moveIsLegal(newX, newY)) {

            if ((Math.abs(newPositionX) == 1) && (newY == getY())) {

                while (checkX != newX) {

                    if (this.isValidTraceX(checkX, newY, newX)) {
                        return true;
                    }

                    if (checkX > newX) {
                        checkX--;
                    } else if (this.getX() < newX) {
                        checkX++;
                    }

                }

            } else if ((newX == getX()) && (Math.abs(newPositionY) == 1)) {

                while (checkY != newY) {

                    if (this.isValidTraceY(newX, checkY, newY)) {
                        return true;
                    }

                    if (checkY > newY) {
                        checkY--;
                    } else if (this.getY() < newY) {
                        checkY++;
                    }

                }

            } else if ((Math.abs(newPositionY) == 1) == (Math.abs(newPositionX) == 1)) {

                while (checkX != newX && checkY != newY) {

                    if (this.isValidTrace(checkX, checkY, newX, newY)) {
                        return true;
                    }

                    if (checkX > newX) {
                        checkX--;
                    } else if (this.getX() < newX) {
                        checkX++;
                    }

                    if (checkY > newY) {
                        checkY--;
                    } else if (this.getY() < newY) {
                        checkY++;
                    }
                }
            }

        }
        return false;
    }

    public boolean isValidTraceX(int newX, int newY, int lastX) {

        boolean isValid = true;
        if ((Board.board[newX][newY]) != null) {
            isValid = false;
        }
        if (((Board.board[lastX][newY]) != null)) {
            if (Board.board[lastX][newY].getColor() == this.getColor()) {
                isValid = false;
            } else {
                isValid = true;
            }
        }

        return isValid;

    }

    public boolean isValidTraceY(int newX, int newY, int lastY) {

        boolean isValid = true;
        if ((Board.board[newX][newY]) != null) {
            isValid = false;
        }
        if (((Board.board[newX][lastY]) != null)) {
            if (Board.board[newX][lastY].getColor() == this.getColor()) {
                isValid = false;
            } else {
                isValid = true;
            }
        }

        return isValid;

    }

    public boolean isValidTrace(int newX, int newY, int lastX, int lastY) {

        boolean isValid = true;
        if ((Board.board[newX][newY]) != null) {
            isValid = false;
        }
        if (((Board.board[lastX][lastY]) != null)) {
            if (Board.board[lastX][lastY].getColor() == this.getColor()) {
                isValid = false;
            } else {
                isValid = true;
            }
        }

        return isValid;

    }
    
	
*/
	
	
	
}

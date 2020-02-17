package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import shared.GUICoord;
import shared.PieceSquareColor;

public class SquareGui extends BorderPane implements ChessSquareGui {

	private PieceSquareColor color; 
	private GUICoord coord; 
	public SquareGui(GUICoord gUICoord, PieceSquareColor squareColor) {
		super();
		Color color = PieceSquareColor.BLACK.equals(squareColor) ?
				Color.BROWN : Color.rgb(230, 230, 230);
		this.color = squareColor;
		this.coord = gUICoord;
		Background bg = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
		this.setBackground(bg);
	}

	@Override
	public GUICoord getCoord() {
		// TODO Auto-generated method stub
		return this.coord;
	}

	@Override
	public void resetColor(boolean isLight) {
		// TODO Auto-generated method stub
		if(isLight) {
			Color color = PieceSquareColor.BLACK.equals(this.color) ?
					Color.BROWN : Color.rgb(230, 230, 230);
			Background bg = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
			this.setBackground(bg);
		}
		else {
			Color color = Color.rgb(33, 146, 188);
			Background bg = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
			this.setBackground(bg);
		}
	}




}

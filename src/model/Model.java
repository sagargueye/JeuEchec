package model;

import java.util.List;

import shared.ActionType;
import shared.ModelCoord;

public class Model implements ChessModel {

	@Override
	public List<ModelCoord> getPieceListMoveOK(ModelCoord initCoord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionType doMove(ModelCoord initCoord, ModelCoord finalCoord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pawnPromotion(ModelCoord promotionCoord, String promotionType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

}

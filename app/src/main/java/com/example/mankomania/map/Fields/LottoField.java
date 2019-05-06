package com.example.mankomania.map.Fields;

import com.example.mankomania.gameData.GameData;

public class LottoField implements Field {
    private int goOverLoss;
    private int goOnLoss;

    public LottoField(int goOverLoss, int goOnLoss) {
        this.goOverLoss = goOverLoss;
        this.goOnLoss = goOnLoss;
    }

    @Override
    public void doAction(GameData gameData) {
        // player can pay for lotto
        if(gameData.getLotto()==0){
            gameData.setMoney(gameData.getHasTurn(),gameData.getMoney()[gameData.getHasTurn()] - this.goOnLoss);
            gameData.setLotto(gameData.getLotto()+this.goOnLoss);
        }else{
            // player gets all the money
            gameData.setMoney(gameData.getHasTurn(),gameData.getMoney()[gameData.getHasTurn()] + gameData.getLotto());
            gameData.setLotto(0);
        }
    }

    @Override
    public void goOver(GameData gameData) {
        gameData.setMoney(gameData.getHasTurn(),gameData.getMoney()[gameData.getHasTurn()] - this.goOverLoss);
        gameData.setLotto(gameData.getLotto()+this.goOverLoss);
    }
}

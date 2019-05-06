package com.example.mankomania.map.Fields;

import com.example.mankomania.gameData.GameData;

public class LossField implements Field {
    private int amount;

    public LossField(int amount) {
        this.amount = amount;
    }

    @Override
    public void doAction(GameData gameData) {
        gameData.setMoney(gameData.getHasTurn(),gameData.getMoney()[gameData.getHasTurn()] - this.amount);
    }

    @Override
    public void goOver(GameData gameData) {
    }
}

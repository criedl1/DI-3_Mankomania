package com.example.mankomania.Map.Fields;

import com.example.mankomania.GameData.GameData;

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
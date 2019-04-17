package com.example.mankomania.Map.Fields;

import com.example.mankomania.GameData.GameData;

public interface Field {
    void doAction(final GameData gameData);
    void goOver(final GameData gameData);
}

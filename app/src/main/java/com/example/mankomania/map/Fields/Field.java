package com.example.mankomania.map.Fields;

import com.example.mankomania.GameData.GameData;

public interface Field {
    void doAction(final GameData gameData);
    void goOver(final GameData gameData);
}

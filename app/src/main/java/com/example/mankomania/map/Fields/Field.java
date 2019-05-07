package com.example.mankomania.map.Fields;

import com.example.mankomania.gamedata.GameData;

public interface Field {
    void doAction(final GameData gameData);
    void goOver(final GameData gameData);
}

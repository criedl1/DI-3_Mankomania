package com.example.mankomania.map;

import com.example.mankomania.network.NetworkConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class MessageReceiver {


    private final GameController gameController;

    MessageReceiver(GameController gameController) {
        this.gameController = gameController;
    }

    void handleMessage(String message) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(message).getAsJsonObject();

        switch (jsonToString(jsonObject, NetworkConstants.OPERATION)) {
            case NetworkConstants.SEND_MONEY:
                setMoneyUpdate(jsonObject);
                break;
            case NetworkConstants.SET_POSITION:
                setPositionUpdate(jsonObject);
                break;
            case NetworkConstants.SET_HYPO_AKTIE:
                setHypoAktieUpdate(jsonObject);
                break;
            case NetworkConstants.SET_STRABAG_AKTIE:
                setStrabagAktieUpdate(jsonObject);
                break;
            case NetworkConstants.SET_INFINEON_AKTIE:
                setInfineonAktieUpdate(jsonObject);
                break;
            case NetworkConstants.SET_LOTTO:
                setLottoUpdate(jsonObject);
                break;
            case NetworkConstants.SET_HOTEL:
                setHotelUpdate(jsonObject);
                break;
            case NetworkConstants.ROLL_DICE:
                rollDiceUpdate(jsonObject);
                break;
            case NetworkConstants.SPIN_WHEEL:
                spinWheelUpdate(jsonObject);
                break;
            case NetworkConstants.START_TURN:
                startTurnUpdate(jsonObject);
                break;
            case NetworkConstants.SET_PLAYER_COUNT:
                initPlayerCount(jsonObject);
                break;
            case NetworkConstants.SET_ID:
                setMyPlayerID(jsonObject);
                break;
            case NetworkConstants.SEND_PLAYER:
                setPlayer(jsonObject);
                break;
            case NetworkConstants.ROULETTE:
                setRouletteResult(jsonObject);
                break;
            case NetworkConstants.MONEY_UPDATE:
                showMoneyUpdate(jsonObject);
                break;
            case NetworkConstants.BLAME_RESULT:
                showBlameResult(jsonObject);
                break;
            case NetworkConstants.SUCCESSCHEAT:
                showCheatSuccess(jsonObject);
                break;
            case NetworkConstants.GAMEEND:
                showGameEnd(jsonObject);
                break;
            case NetworkConstants.GET_ORDER:
                showOrderSelection(jsonObject);
                break;
            case NetworkConstants.SEND_CASINO:
                casinoUpdate(jsonObject);
                break;
            default:
                throw new IllegalStateException("Network Object should not be here: " + message);
        }
    }

    private void showOrderSelection(JsonObject jsonObject) {
        JsonArray namesArr = jsonObject.getAsJsonArray(NetworkConstants.NAME);
        String[] names = new String[namesArr.size()];
        for (int i = 0; i < namesArr.size(); i++) {
            names[i] = namesArr.get(i).getAsString();
        }
        gameController.showOrderSelection(names);
    }

    private void showGameEnd(JsonObject jsonObject){
        int player = jsonToInt(jsonObject,NetworkConstants.PLAYER);
        gameController.endGame(player);
    }

    private void showCheatSuccess(JsonObject jsonObject) {
        int successor = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        gameController.showCheatSuccess(successor);
    }

    private void showBlameResult(JsonObject jsonObject) {
        boolean result = jsonToString(jsonObject, NetworkConstants.BLAME_RESULT).equals(NetworkConstants.BLAME_SUCCESS);
        int blamer = jsonToInt(jsonObject, NetworkConstants.BLAMER);
        int blamed = jsonToInt(jsonObject, NetworkConstants.BLAMED);
        gameController.showBlameResult(result,blamer,blamed);
    }

    private void setPlayer(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        String ip = jsonToString(jsonObject, NetworkConstants.IP);
        gameController.setPlayerIP(player, ip);
    }

    private void setRouletteResult(JsonObject jsonObject) {
        int moneyChange = jsonToInt(jsonObject, NetworkConstants.RESULT);
        gameController.setRouletteResult(moneyChange);
    }


    private void initPlayerCount(JsonObject jsonObject) {
        int playerCount = jsonToInt(jsonObject, NetworkConstants.COUNT);
        gameController.initPlayer(playerCount);
    }

    private void startTurnUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        gameController.setTurn(player);
    }


    private void setHypoAktieUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject, NetworkConstants.COUNT);

        gameController.setHypoAktieFromMessage(player, count);
    }

    private void setStrabagAktieUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject, NetworkConstants.COUNT);

        gameController.setStrabagAktiefromMessage(player, count);
    }

    private void setInfineonAktieUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject, NetworkConstants.COUNT);

        gameController.setInfineonAktiefromMessage(player, count);
    }

    private void setHotelUpdate(JsonObject jsonObject) {
        int hotel = jsonToInt(jsonObject, NetworkConstants.HOTEL);
        int player = jsonToInt(jsonObject, NetworkConstants.OWNER);
        gameController.setHotelfromMessage(player,hotel);
    }

    private void setPositionUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int position = jsonToInt(jsonObject, NetworkConstants.POSITION);
        gameController.setPosition(player, position);
    }

    private void setMoneyUpdate(JsonObject jsonObject) {

        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int money = jsonToInt(jsonObject, NetworkConstants.MONEY);
        gameController.setMoneyOnUI(player, money);
    }


    private void setLottoUpdate(JsonObject jsonObject) {
        int amount = jsonToInt(jsonObject, NetworkConstants.AMOUNT);

        gameController.setLotto(amount);
    }


    private void spinWheelUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int outcome = jsonToInt(jsonObject, NetworkConstants.RESULT);

        gameController.spinWheelUpdate(player, outcome);
    }

    private void rollDiceUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int outcome = jsonToInt(jsonObject, NetworkConstants.RESULT);
        gameController.rollDiceUpdate(player, outcome);
    }

    private void casinoUpdate(JsonObject jsonObject) {
        int result = jsonToInt(jsonObject, NetworkConstants.RESULT);
        gameController.casinoUpdate(result);
    }

    private void showMoneyUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int outcome = jsonToInt(jsonObject, NetworkConstants.RESULT);
        gameController.showMoneyUpdate(player, outcome);
    }

    private void setMyPlayerID(JsonObject jsonObject) {
        int id = jsonToInt(jsonObject, NetworkConstants.ID);
        gameController.setMyPlayerID(id);
    }

    private String jsonToString(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsString();
    }

    private int jsonToInt(JsonObject jsonObject, String key) {
        return Integer.parseInt(jsonObject.get(key).getAsString());
    }

}

package com.example.mankomania.network.logic;

public class ServerLogic {
    private ServerLogicStates sls;
    private int player;

    public ServerLogic() {
        this.sls = ServerLogicStates.END;
    }

    public boolean startTurn(int player) {
        if (sls == ServerLogicStates.END) {
            sls = ServerLogicStates.BEGIN;
            this.player = player;
            return true;
        }else{
            return false;
        }
    }

    public boolean throwDice(int player){
        if(sls == ServerLogicStates.BEGIN && this.player == player){
            sls = ServerLogicStates.ACTION;
            return true;
        }else {
            return false;
        }
    }

    public boolean doAction(int player) {
        if (sls == ServerLogicStates.ACTION && this.player == player)
            return true;
        return false;
    }

    public boolean changeValue(int player) {
        if (sls == ServerLogicStates.ACTION && this.player == player)
            return true;
        return false;
    }

    public boolean endTurn(int player){
        if(sls == ServerLogicStates.ACTION && this.player == player){
            sls = ServerLogicStates.END;
            return true;
        }else {
            return false;
        }
    }
}

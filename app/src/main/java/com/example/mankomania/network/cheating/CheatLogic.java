package com.example.mankomania.network.cheating;

import com.example.mankomania.network.server.ServerQueueHandler;

import java.util.Arrays;

public class CheatLogic {

    private final ServerQueueHandler server;
    private int[] cheaterArr;
    private boolean[] didBlame;
    private int playerCount;

    public CheatLogic(ServerQueueHandler server, int playerCount) {
        this.server = server;
        this.playerCount = playerCount;
        initArrays();
    }

    private void initArrays() {
        int[] cheatArr = new int[playerCount];
        boolean[] blameArr = new boolean[playerCount];

        Arrays.fill(cheatArr,-1);
        setCheaterArr(cheatArr);

        Arrays.fill(blameArr, false);
        setDidBlame(blameArr);
    }

    private int[] getCheaterArr() {
        return cheaterArr.clone();
    }

    private void setCheaterArr(int[] cheaterArr) {
        this.cheaterArr = cheaterArr;
    }

    private boolean[] getDidBlame() {
        return didBlame;
    }

    private void setDidBlame(boolean[] didBlame) {
        this.didBlame = didBlame;
    }

    public void decrementCheater() {
        int[] cheaterArr = this.getCheaterArr();
        int index = 0;
        for (int i : this.cheaterArr) {
            if (i > 0) {
                i--;
                if(i==0){
                    server.sendDidCheatSuccessfully(index);
                }
                cheaterArr[index] = i;
            }
            index++;
        }
        setCheaterArr(cheaterArr);
    }


    public boolean setCheater(int player) {
        if(this.neverCheated(player)){
            int[] arr = this.getCheaterArr();
            //Change GameData
            arr[player] = this.playerCount;
            this.setCheaterArr(arr);
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param player
     * @return if the player has ever cheated (current or past)
     */
    public boolean neverCheated(int player) {
        int[] cheatArr = this.getCheaterArr();
        return cheatArr[player] < 0;
    }

    /**
     *
     * @param player
     * @return if player did blame a person yet
     */
    private boolean neverBlamed(int player) {
        return !this.getDidBlame()[player];
    }

    /**
     * set that the player blamed someone
     * @param player
     * @return true if successful, false if not
     */
    private void setDidBlame(int player) {
        if(this.neverBlamed(player)){
            boolean[] tmpdidBlame = this.getDidBlame();
            tmpdidBlame[player] = true;
            this.setDidBlame(tmpdidBlame);
        }
    }

    public boolean isCurrentlyCheating(int cheater) {
        return this.getCheaterArr()[cheater]>0;
    }

    /**
     * blames a person and returns if successfully blamed
     * @param cheater
     * @return
     */
    public boolean blamePerson(int player, int cheater) {
        if(this.neverBlamed(player) && player != cheater) {
            this.setDidBlame(player);
            if(this.isCurrentlyCheating(cheater)){
                int[] isCheater = this.getCheaterArr();
                isCheater[cheater] = 0;
                this.setCheaterArr(isCheater);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }

    }
}

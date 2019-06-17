package com.example.mankomania.map;

import android.support.v4.content.ContextCompat;

import com.example.mankomania.R;
import com.example.mankomania.map.hotels.Hotel;
import com.example.mankomania.network.client.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController implements Serializable {

    private final MapView mapView;
    private Hotel[] hotels = new Hotel[]{
            new Hotel("SANDWIRTH"),
            new Hotel("PLATTENWIRT"),
            new Hotel("SEEPARKHOTEL"),
    };
    public List<Player> getPlayers() {
        return players;
    }

    int getPlayerCount(){
        if(this.players!= null){
            return this.players.size();
        }else{
            return 0;
        }
    }

    private List<Player> players;
    private Random randstock =new Random();

    //this wonderful boolean prevents Toast
    //from an infitity loop
    //I know its not the prettiest solution
    //but i tried to hard for that toast to
    //screw it in the last week.
    static boolean toastOnOf = false;


    public static final int[] allfields = {
            R.drawable.field_start,
            R.drawable.field_aktie1,
            R.drawable.field_lindwurm,
            R.drawable.field_lottery,
            R.drawable.field_casino,
            R.drawable.field_getsomemoney,
            R.drawable.field_alterplatz,
            R.drawable.field_aktie2,
            R.drawable.field_aktienboerse,
            R.drawable.field_horserace,
            R.drawable.field_zoo,
            R.drawable.field_stadium,
            R.drawable.field_casino,
            R.drawable.field_alterplatz,
            R.drawable.field_lindwurm,
            R.drawable.field_hotelsandwirth,
            R.drawable.field_horserace,
            R.drawable.field_minimundus,
            R.drawable.field_getsomemoney,
            R.drawable.field_aktie3,
            R.drawable.field_aktienboerse,
            R.drawable.field_woerthersee,
            R.drawable.field_lottery,
            R.drawable.field_casino,
            R.drawable.field_zoo,
            R.drawable.field_aktie2,
            R.drawable.field_aktienboerse,
            R.drawable.field_lindwurm,
            R.drawable.field_minimundus,
            R.drawable.field_klage,
            R.drawable.field_aktie1,
            R.drawable.field_casino,
            R.drawable.field_seeparkhotel,
            R.drawable.field_zoo,
            R.drawable.field_stadium,
            R.drawable.field_plattenwirt,
            R.drawable.field_getsomemoney,
            R.drawable.field_aktie3,
    };


    private Client client;
    private MessageReceiver receiver;


    private int myID;

    private int lotto;
    private int hasTurn;

    GameController(String ip, MapView mapView) {
        this.mapView = mapView;
        players = new ArrayList<>();

        this.initReceiver();
        client = new Client();
        Client.init(ip, mapView);
    }

    void startClient() {
        client.start();
    }

    private void initReceiver() {
        this.receiver = new MessageReceiver(this);
    }


    void handleMessage(String message) {
        this.receiver.handleMessage(message);
    }


    int getMyID() {
        return myID;
    }

    void rollDiceUpdate(int player, int outcome) {
        if (isMyTurn()) {
            mapView.showMyDiceResult(outcome);
        } else {
            mapView.showSomeonesDiceResult(player, outcome);
        }
    }

    void casinoUpdate(int result) {
        this.mapView.showCasinoResult(result);
    }

    void showMoneyUpdate(int player, int outcome) {
        if (isMyTurn()) {
            mapView.showMyAccountBalance(outcome);
        } else {
            mapView.showSomeonesAccountBalance(player, outcome);
        }
    }

    private void showAktienUpdate(int player, Aktien aktien) {
        if (isMyTurn()) {
            mapView.showMyAktienkauf(aktien);
        } else {
            mapView.showSomeonesAktienkauf(player, aktien);
        }
    }

    void showHotelUpdate(int player, int h) {
        Hotel hotel = hotels[h];
        if (isMyTurn()) {
            mapView.showMyHotelkauf(hotel);
        } else {
            mapView.showSomeonesHotelkauf(player, hotel);
        }
    }


    boolean isMyTurn() {
        return hasTurn == myID;
    }

    void rollTheDice() {
        client.rollTheDice();
    }

    void updateMoney(int playerIdx, int balance) {
        int bal = currentPlayer().getMoney();
        client.setMoneyOnServer(playerIdx, bal + balance);
        client.endTurn();
    }
    void updateMoneyHotelOwner(int playerIdx, int balance) {
        int bal = currentPlayer().getMoney();
        client.setMoneyOnServer(playerIdx, bal + balance);
    }

    public void setMyPlayerID(int player) {
        this.myID = player;
        this.players.get(myID).initMyMoneyField(ContextCompat.getColor(mapView, R.color.moneyBGMine));
    }

    public void setMoney(int player, int money) {
        this.players.get(player).setMoney(money);
        showMoneyUpdate(player, money);
    }


    void setPosition(int player, int position) {
        this.mapView.step1();
        this.players.get(player).setPosition(position);

    }

    void setHypoAktieFromMessage(int player, int count) {
        this.players.get(player).setAktie(Aktien.HYPO, count);
        showAktienUpdate(player, Aktien.HYPO);

    }

    void setHypoAktie(int player, int count) {
        client.setHypoAktieOnServer(player, count);
        updateMoney(player, -100000);
        client.endTurn();
    }

    void setStrabagAktiefromMessage(int player, int count) {
        this.players.get(player).setAktie(Aktien.STRABAG, count);
        showAktienUpdate(player, Aktien.STRABAG);
    }

    void setStrabagAktie(int player, int count) {
        client.setStrabagAktieOnServer(player, count);
        updateMoney(player, -100000);
        client.endTurn();
    }

    void setInfineonAktiefromMessage(int player, int count) {
        this.players.get(player).setAktie(Aktien.INFINEON, count);
        showAktienUpdate(player, Aktien.INFINEON);
    }

    void setInfineonAktie(int player, int count) {
        client.setInfineonAktieOnServer(player, count);
        updateMoney(player, -100000);
        client.endTurn();

    }
    void stockexchange(){
        int aktie = randstock.nextInt(2);//
        int riseordecrease = randstock.nextInt(4); //0 = steigen, 1 = dividende, 2,3 = fallen

        for (Player p:players) {
            int[] aktien = p.getAktien();
            if (riseordecrease==0){
                if (aktien[aktie]>0){
                    client.setMoneyOnServer(getPlayerIndex(p),p.getMoney()+100000);
                }
            }else if (riseordecrease==1){
                client.setMoneyOnServer(getPlayerIndex(p),p.getMoney()+100000);
            }
            else {
                if (aktien[aktie]>0){
                    client.setMoneyOnServer(getPlayerIndex(p),p.getMoney()-100000);
                }
            }
        }
        client.endTurn();
    }

    void setLotto(int amount) {
        this.lotto = amount;
        mapView.setLotto(this.lotto);
    }

    int getLotto() {
        return lotto;
    }

    void spinWheelUpdate(int player, int outcome) {

    }

    void setTurn(int player) {
        boolean wasMyTurn = false;
        if (isMyTurn() && player != myID) {
            wasMyTurn = true;
        }
        this.hasTurn = player;
        if (isMyTurn()) {
            mapView.startMyTurn();
        } else if (wasMyTurn) {
            mapView.endMyTurn();
        }
    }

    void initPlayer(int playerCount) {
        for (int i = 0; i < playerCount; i++) {
            this.players.add(new Player());
        }
        mapView.initPlayerFields();
    }

    Player currentPlayer() {
        return this.players.get(hasTurn);
    }

    void setRouletteResult(int moneyChange) {
        client.setMoneyOnServer(this.myID, this.currentPlayer().getMoney() + moneyChange);
        client.sendCasinoResult(moneyChange);
        client.endTurn();
    }


    /**
     * gibt Listen-Index des übergebenen Spielers aus
     *
     * @param p Player-Objekt
     * @return Index, wenn p in Liste gefunden, -1 sonst.
     */
    int getPlayerIndex(Player p) {
        for (int i = 0; i < players.size(); i++) {
            if (p.equals(players.get(i)))
                return i;
        }
        return -1;
    }

    void setPlayerIP(int player, String ip) {
        this.players.get(player).setIP(ip);
    }

    void startHorseRace() {
        // TODO - horse race
        client.endTurn();
    }

    public void getShare() {
        // TODO - add share
        client.endTurn();
    }

    void justEndTurn() {
        client.endTurn();
    }

    void sendMoveOverLotto() {
        this.setMoney(hasTurn, this.currentPlayer().getMoney() - 5000);
        this.client.setMoneyOnServer(this.myID, this.currentPlayer().getMoney() - 5000);
        this.client.setLottoOnServer(this.lotto + 5000);
    }

    void showBlameResult(boolean result, int blamer, int blamed) {
        this.mapView.showBlameResult(result, blamer, blamed);
    }

    void makeMeCheat() {
        this.players.get(myID).setDidCheat(true);
        this.mapView.hideCheatButton();
        this.client.setMeAsCheater();
    }

    void showCheatSuccess(int successor) {
        this.mapView.showCheatSuccess(successor);
    }

    void setBlame(int cheater) {
        this.players.get(myID).setDidBlame(true);
        this.mapView.hideBlameButton();
        this.client.sendBlame(cheater);
    }

    public Hotel[] getHotels() {
        return hotels;
    }

    public void sendHotel(int hotel, int player, int price){
        this.client.setHotelOnServer(hotel,player, price);
        updateMoney(player, -price);
        client.endTurn();
    }

    public void setHotelfromMessage(int player, int hotelIdx) {
        this.hotels[hotelIdx].setOwner(players.get(player));
        showHotelUpdate(player, hotelIdx);
    }
    void lotteryAction() {

        int myLotto = this.getLotto();
        if(myLotto==0){
            client.setLottoOnServer(myLotto+50000);
            client.setMoneyOnServer(this.myID, this.players.get(this.myID).getMoney()-50000);
            mapView.showLottoLoose();
        }else{
            client.setLottoOnServer(0);
            client.setMoneyOnServer(this.myID, this.players.get(this.myID).getMoney()+myLotto);
            mapView.showLottoWin();
        }
        client.endTurn();
    }
    void endGame(int player){
        if (this.myID== player){
            mapView.showMyWin();
        }
        else {
            mapView.showSomeonesWin(player+1);
        }
    }
}

package com.example.mankomania.map;

import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.mankomania.R;
import com.example.mankomania.network.client.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController implements Serializable {

    private final MapView mapView;
    List<Player> players;
    Random randstock =new Random();




    public static int[] allfields = {
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
            R.drawable.field_klage,
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
    private int cheater;
    private int lotto;
    private int hasTurn;
    private boolean isServer;

    GameController(String ip, String name,boolean isServer, MapView mapView) {
        this.mapView = mapView;
        players = new ArrayList<>();
        this.isServer = isServer;
        this.initReceiver();
        client = new Client();
        client.init(ip, mapView, name);
        Log.i("JONTEST", "Start Client with Name " + ip);
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


    public int getMyID() {
        return myID;
    }

    void rollDiceUpdate(int player, int outcome) {
        if (isMyTurn()) {
            mapView.showMyDiceResult(outcome);
        } else {
            mapView.showSomeonesDiceResult(player, outcome);
        }
    }

    void showMoneyUpdate(int player, int outcome) {
        if (isMyTurn()) {
            mapView.showMyAccountBalance(outcome);
        } else {
            mapView.showSomeonesAccountBalance(player, outcome);
        }
    }

    void showAktienUpdate(int player, Aktien aktien) {
        if (isMyTurn()) {
            mapView.showMyAktienkauf(aktien);
        } else {
            mapView.showSomeonesAktienkauf(player, aktien);
        }
    }

    void showHotelUpdate(int player, Hotel hotel) {
        if(isMyTurn()) {
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
        client.setMoneyOnServer(playerIdx,bal+balance);
        client.endTurn();
    }

   public void setMyPlayerID(int player) {
        this.myID = player;
        this.players.get(myID).initMyMoneyField(ContextCompat.getColor(mapView,R.color.moneyBGMine));
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
        client.setHypoAktieOnServer(player,count);
        updateMoney(player,-100000);
        client.endTurn();
    }

    void setStrabagAktiefromMessage(int player, int count) {
        this.players.get(player).setAktie(Aktien.STRABAG, count);
        showAktienUpdate(player, Aktien.STRABAG);
    }
    void setStrabagAktie(int player, int count) {
        client.setStrabagAktieOnServer(player,count);
        updateMoney(player,-100000);
        client.endTurn();
    }

    void setInfineonAktiefromMessage(int player, int count) {
        this.players.get(player).setAktie(Aktien.INFINEON, count);
        showAktienUpdate(player, Aktien.INFINEON);
    }
    void setInfineonAktie(int player, int count) {
        client.setInfineonAktieOnServer(player,count);
        updateMoney(player,-100000);
        client.endTurn();

    }
     void stockexchange(){
         int aktie = randstock.nextInt(2);//
         int riseordecrease = randstock.nextInt(4); //0 = steigen, 1 = dividende, 2,3 = fallen

         for (Player p:players) {
             int aktien [] = p.getAktien();
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


    void setPlattenwirtHotel(int player, int count) {
        this.players.get(player).setHotel(Hotel.PLATTENWIRT, count);
        showHotelUpdate(player, Hotel.PLATTENWIRT);
        client.endTurn();
    }
    void setSandwirtHotel(int player, int count) {
        this.players.get(player).setHotel(Hotel.SANDWIRTH, count);
        showHotelUpdate(player, Hotel.SANDWIRTH);
        client.endTurn();
    }
    void setSeeparkHotel(int player, int count) {
        this.players.get(player).setHotel(Hotel.SEEPARK, count);
        showHotelUpdate(player, Hotel.SEEPARK);
        client.endTurn();
    }


    void setCheater(int player) {
        this.cheater = player;
    }

    void setLotto(int amount) {
        this.lotto = amount;
        mapView.setLotto(this.lotto);
    }



    void spinWheelUpdate(int player, int outcome) {
        //TODO: Implement Method
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
        if(isServer){
            client.amServer();
        }
        client.setMyName();
    }

    Player currentPlayer() {
        return this.players.get(hasTurn);
    }

    void setRouletteResult(int moneyChange) {
        client.setMoneyOnServer(this.myID, this.currentPlayer().getMoney() + moneyChange);
        // TODO: end Turn with a Button ?
        client.endTurn();
    }


    /**
     * gibt Listen-Index des übergebenen Spielers aus
     *
     * @param p Player-Objekt
     * @return Index, wenn p in Liste gefunden, -1 sonst.
     */
    public int getPlayerIndex(Player p) {
        for (int i = 0; i < players.size(); i++) {
            if (p.equals(players.get(i)))
                return i;
        }
        return -1;
    }

    public void setPlayerIP(int player, String ip) {
        this.players.get(player).setIP(ip);
    }

    public void startHorseRace() {
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
        this.client.setMoneyOnServer(this.myID,this.currentPlayer().getMoney() - 5000);
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

    public void showOrderSelection(String[] names) {
        mapView.showOrderSelection(names);
    }
}

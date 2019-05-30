package com.example.mankomania.map;

import android.util.Log;

import com.example.mankomania.R;
import com.example.mankomania.network.client.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Serializable {

    private final MapView mapView;
    List<Player> players;


    public static int[] allfields = {
            R.drawable.field_start,
            R.drawable.field_aktie1,
            R.drawable.field_lindwurm,
            R.drawable.field_lottery,
            R.drawable.field_casino,
            R.drawable.field_getsomemoney,
            R.drawable.field_alterplatz,
            R.drawable.field_aktie2,
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
            R.drawable.field_woerthersee,
            R.drawable.field_lottery,
            R.drawable.field_casino,
            R.drawable.field_zoo,
            R.drawable.field_aktie2,
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

    public GameController(String ip, MapView mapView) {
        this.mapView = mapView;
        players = new ArrayList<>();

        this.initReceiver();
        client = new Client();
        client.init(ip, mapView);
        Log.i("JONTEST", "Start Client with IP " + ip);
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
        this.players.get(myID).initMyMoneyField();
    }

    public void setMoney(int player, int money) {
        this.players.get(player).setMoney(money);
        showMoneyUpdate(player, money);
    }


    void setPosition(int player, int position) {
        this.mapView.step1();
        this.players.get(player).setPosition(position);

    }

    void setHypoAktie(int player, int count) {
        this.players.get(player).setAktie(Aktien.HYPO, count);
        showAktienUpdate(player, Aktien.HYPO);
        client.endTurn();
        // TODO: update UI
    }

    void setStrabagAktie(int player, int count) {
        this.players.get(player).setAktie(Aktien.STRABAG, count);
        showAktienUpdate(player, Aktien.STRABAG);
        // TODO: update UI
        client.endTurn();
    }

    void setInfineonAktie(int player, int count) {
        this.players.get(player).setAktie(Aktien.INFINEON, count);
        showAktienUpdate(player, Aktien.INFINEON);
        // TODO: update UI
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


    public void justEndTurn() {
        client.endTurn();
    }

    void sendMoveOverLotto() {
        this.setMoney(hasTurn, this.currentPlayer().getMoney() - 5000);
        this.client.setLottoOnServer(this.lotto + 5000);
    }
}

package com.example.mankomania.slotmachine;

public class SlotMachineLogic {

    private static final String YOU_HAVE = "Du hast ";
    private static final String WON = " Gewonnen!";
    private static final String LOST = " Verloren";
    private static final String BIG_WIN = "Hauptgewinn! ";
    private static final String THREE_SYMBOLS = "Drei gleiche Symbole! ";
    private static final String TWO_SYMBOLS = "Zwei gleiche Symbole! ";

    int id1;
    int id2;
    int id3;
    SlotMachineActivity sma;

    public SlotMachineLogic(int id1, int id2, int id3, SlotMachineActivity sma) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.sma = sma;
    }

    public void checkWin(){
        if (id1 == id2 && id2 == id3) {
            if (id1 == 3) { //When player has three dollar signs
                setMoney(230000);
                setWinString(WON);
                setReturnString(BIG_WIN + YOU_HAVE + getMoney() + WON);

            } else {
                setMoney(130000);
                setWinString(WON);
                setReturnString(THREE_SYMBOLS + getMoney() + WON);
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            setMoney(30000);
            setWinString(WON);
            setReturnString(TWO_SYMBOLS + getMoney() + WON);
        }
        else{
            setMoney(-20000);
            setWinString(LOST);
            setReturnString(YOU_HAVE + getMoney()*-1 + LOST);
        }
    }

    //Made Getter and Setter here too, to have them in both classes

    public int getMoney(){
        return sma.getMoney();
    }

    public void setMoney(int money){
        sma.setMoney(money);
    }

    public void setReturnString(String string){
        sma.setReturnString(string);
    }

    public String getReturnString(){
        return sma.getReturnString();
    }

    public void setWinString(String string){
        sma.setWinString(string);
    }

    public String getWinString(){
        return sma.getWinString();
    }
}

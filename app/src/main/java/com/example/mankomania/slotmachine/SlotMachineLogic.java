package com.example.mankomania.slotmachine;

public class SlotMachineLogic {

    private static final String YOU_HAVE = "Du hast ";
    private static final String WON = " Gewonnen!";
    private static final String LOST = " Verloren";
    private static final String BIGWIN = "Hauptgewinn! ";
    private static final String THREESYMBOLS = "Drei gleiche Symbole! ";
    private static final String TWOSYMBOLS = "Zwei gleiche Symbole! ";

    private int id1;
    private int id2;
    private int id3;
    private SlotMachineActivity sma;

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
                sma.setMoneyamout(getMoney());
                setWinString(WON);
                setReturnString(BIGWIN + YOU_HAVE + getMoney() + WON);

            } else {
                setMoney(120000);
                sma.setMoneyamout(getMoney());
                setWinString(WON);
                setReturnString(THREESYMBOLS + getMoney() + WON);
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            setMoney(50000);
            sma.setMoneyamout(getMoney());
            setWinString(WON);
            setReturnString(TWOSYMBOLS + getMoney() + WON);
        }
        else{
            setMoney(-20000);
            sma.setMoneyamout(getMoney());
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

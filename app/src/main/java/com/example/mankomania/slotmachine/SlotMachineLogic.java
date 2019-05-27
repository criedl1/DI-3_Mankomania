package com.example.mankomania.slotmachine;

public class SlotMachineLogic {

    private static final String you_have = "Du hast ";
    private static final String won = " Gewonnen!";
    private static final String lost = " Verloren";
    private static final String bigWin = "Hauptgewinn! ";
    private static final String threeSymbols = "Drei gleiche Symbole! ";
    private static final String twoSymbols = "Zwei gleiche Symbole! ";

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
                sma.setMoneyamout(getMoney());
                setWinString(won);
                setReturnString(bigWin + you_have + getMoney() + won);

            } else {
                setMoney(120000);
                sma.setMoneyamout(getMoney());
                setWinString(won);
                setReturnString(threeSymbols + getMoney() + won);
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            setMoney(50000);
            sma.setMoneyamout(getMoney());
            setWinString(won);
            setReturnString(twoSymbols + getMoney() + won);
        }
        else{
            setMoney(-20000);
            sma.setMoneyamout(getMoney());
            setWinString(lost);
            setReturnString(you_have+ getMoney()*-1 + lost);
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

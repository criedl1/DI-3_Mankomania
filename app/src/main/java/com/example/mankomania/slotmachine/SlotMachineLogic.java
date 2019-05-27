package com.example.mankomania.slotmachine;

public class SlotMachineLogic {

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
                setWinString("Gewonnen!");
                setReturnString("Hauptgewinn! Du hast " + getMoney() + " gewonnen!");

            } else {
                setMoney(120000);
                sma.setMoneyamout(getMoney());
                setWinString("Gewonnen!");
                setReturnString("Drei gleiche Symbole! Du hast " + getMoney() + " gewonnen!");
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            setMoney(50000);
            sma.setMoneyamout(getMoney());
            setWinString("Gewonnen!");
            setReturnString("Zwei gleiche Symbole! Du hast " + getMoney() + " gewonnen!");
        }
        else{
            setMoney(-20000);
            sma.setMoneyamout(getMoney());
            setWinString("Verloren!");
            setReturnString("Du hast " + getMoney()*-1 + " verloren!");
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

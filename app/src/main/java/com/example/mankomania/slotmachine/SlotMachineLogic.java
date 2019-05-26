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
                sma.setMoney(230000);
                sma.setMoneyamout(sma.getMoney());
                sma.setWinString("Gewonnen!");
                sma.setReturnString("Hauptgewinn! Du hast " + sma.getMoney() + " gewonnen!");

            } else {
                sma.setMoney(120000);
                sma.setMoneyamout(sma.getMoney());
                sma.setWinString("Gewonnen!");
                sma.setReturnString("Drei gleiche Symbole! Du hast " + sma.getMoney() + " gewonnen!");
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            sma.setMoney(50000);
            sma.setMoneyamout(sma.getMoney());
            sma.setWinString("Gewonnen!");
            sma.setReturnString("Zwei gleiche Symbole! Du hast " + sma.getMoney() + " gewonnen!");
        }
        else{
            sma.setMoney(-20000);
            sma.setMoneyamout(sma.getMoney());
            sma.setWinString("Verloren!");
            sma.setReturnString("Du hast " + sma.getMoney()*-1 + " verloren!");
        }
    }
}

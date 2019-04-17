package com.example.mankomania;

public class RouletteClass {

    static int randomNumber;

    public FieldClass[] setUpFields() {
        FieldClass fieldClass0 = new FieldClass(ColorEnum.GREEN, 0);
        FieldClass fieldClass32 = new FieldClass(ColorEnum.RED, 32);
        FieldClass fieldClass15 = new FieldClass(ColorEnum.BLACK, 15);
        FieldClass fieldClass19 = new FieldClass(ColorEnum.RED, 19);
        FieldClass fieldClass4 = new FieldClass(ColorEnum.BLACK, 4);
        FieldClass fieldClass21 = new FieldClass(ColorEnum.RED, 21);
        FieldClass fieldClass2 = new FieldClass(ColorEnum.BLACK, 2);
        FieldClass fieldClass25 = new FieldClass(ColorEnum.RED, 25);
        FieldClass fieldClass17 = new FieldClass(ColorEnum.BLACK, 17);
        FieldClass fieldClass34 = new FieldClass(ColorEnum.RED, 34);
        FieldClass fieldClass6 = new FieldClass(ColorEnum.BLACK, 6);
        FieldClass fieldClass27 = new FieldClass(ColorEnum.RED, 27);
        FieldClass fieldClass13 = new FieldClass(ColorEnum.BLACK, 13);
        FieldClass fieldClass36 = new FieldClass(ColorEnum.RED, 36);
        FieldClass fieldClass11 = new FieldClass(ColorEnum.BLACK, 11);
        FieldClass fieldClass30 = new FieldClass(ColorEnum.RED, 30);
        FieldClass fieldClass8 = new FieldClass(ColorEnum.BLACK, 8);
        FieldClass fieldClass23 = new FieldClass(ColorEnum.RED, 23);
        FieldClass fieldClass10 = new FieldClass(ColorEnum.BLACK, 10);
        FieldClass fieldClass5 = new FieldClass(ColorEnum.RED, 5);
        FieldClass fieldClass24 = new FieldClass(ColorEnum.BLACK, 24);
        FieldClass fieldClass16 = new FieldClass(ColorEnum.RED, 16);
        FieldClass fieldClass33 = new FieldClass(ColorEnum.BLACK, 33);
        FieldClass fieldClass1 = new FieldClass(ColorEnum.RED, 1);
        FieldClass fieldClass20 = new FieldClass(ColorEnum.BLACK, 20);
        FieldClass fieldClass14 = new FieldClass(ColorEnum.RED, 14);
        FieldClass fieldClass31 = new FieldClass(ColorEnum.BLACK, 31);
        FieldClass fieldClass9 = new FieldClass(ColorEnum.RED, 9);
        FieldClass fieldClass22 = new FieldClass(ColorEnum.BLACK, 22);
        FieldClass fieldClass18 = new FieldClass(ColorEnum.RED, 18);
        FieldClass fieldClass29 = new FieldClass(ColorEnum.BLACK, 29);
        FieldClass fieldClass7 = new FieldClass(ColorEnum.RED, 7);
        FieldClass fieldClass28 = new FieldClass(ColorEnum.BLACK, 28);
        FieldClass fieldClass12 = new FieldClass(ColorEnum.RED, 12);
        FieldClass fieldClass35 = new FieldClass(ColorEnum.BLACK, 35);
        FieldClass fieldClass3 = new FieldClass(ColorEnum.RED, 3);
        FieldClass fieldClass26 = new FieldClass(ColorEnum.BLACK, 26);


        FieldClass[] array = {fieldClass0, fieldClass32, fieldClass15, fieldClass19, fieldClass4, fieldClass21, fieldClass2, fieldClass25, fieldClass17,
                fieldClass34, fieldClass6, fieldClass27, fieldClass13, fieldClass36, fieldClass11, fieldClass30, fieldClass8, fieldClass23,
                fieldClass10, fieldClass5, fieldClass24, fieldClass16, fieldClass33, fieldClass1, fieldClass20, fieldClass14, fieldClass31,
                fieldClass9, fieldClass22, fieldClass18, fieldClass29, fieldClass7, fieldClass28, fieldClass12, fieldClass35, fieldClass3,
                fieldClass26};

        return array;
    }

    public int spinIt(){
        randomNumber = (int) (Math.random() *36) + 0;
        return randomNumber;
    }

    public static int getRandomNumber(){
        return randomNumber;
    }
}

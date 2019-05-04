package com.example.mankomania.roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.mankomania.R;

public class PopClass extends AppCompatDialogFragment {

    private String theNumberIs;
    private String color;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            //TODO: Figure out if and how this is working without static
            alert.setTitle(RotateActivity.getReturnString());

            //TODO: ColorActivity muss die Farbe mitgeben!
            color = RotateActivity.getColor();

        if(color == "BLACK"){
                color = getString(R.string.roulette_black);}
        else if(color == "RED"){
                color = getString(R.string.roulette_red);}
        else{ color = getString(R.string.roulette_green);
        }

        //TODO: Figure out if and how this is working without static
        //TODO: newMoney liefert vorerst den falschen Wert!!!!
        theNumberIs = Integer.toString(RotateActivity.getRandomNumber());
                alert.setMessage(getString(R.string.roulette_result, theNumberIs, color));
        alert.setPositiveButton(getString(R.string.roulette_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //by letting this empty, the pop-up closes when button is pressed.
            }
        });

        return alert.create();
    }




}

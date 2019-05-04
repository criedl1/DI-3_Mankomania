package com.example.mankomania.roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.mankomania.R;

public class PopClass extends AppCompatDialogFragment {

    private int randomNumber;
    private String theNumberIsString;
    private String colorString;
    private String returnString;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        colorString = getArguments().getString("color");
        randomNumber = getArguments().getInt("randomNumber"); //toString is not possible here
        theNumberIsString = Integer.toString(randomNumber);
        returnString = getArguments().getString("returnString");


        alert.setTitle(getReturnString());
        if(colorString == "BLACK"){
                colorString = getString(R.string.roulette_black);}
        else if(colorString == "RED"){
                colorString = getString(R.string.roulette_red);}
        else{colorString = getString(R.string.roulette_green);
        }

                alert.setMessage(getString(R.string.roulette_result, getTheNumberIsString(), getColorString()));
        alert.setPositiveButton(getString(R.string.roulette_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //by letting this empty, the pop-up closes when button is pressed.
            }
        });

        return alert.create();
    }


    public String getTheNumberIsString() {
        return theNumberIsString;
    }

    public String getColorString() {
        return colorString;
    }

    public String getReturnString() {
        return returnString;
    }


}

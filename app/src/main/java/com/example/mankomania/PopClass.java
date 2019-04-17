package com.example.mankomania;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.DisplayMetrics;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopClass extends AppCompatDialogFragment {

    String theNumberIs;
    String color;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        if(ColorActivity.getReturnString()!= null){
        alert.setTitle(ColorActivity.getReturnString());
        ColorActivity.setReturnString(null);
        }

        else if(DozenActivity.getReturnString()!= null){
        alert.setTitle(DozenActivity.getReturnString());
        DozenActivity.setReturnString(null)
        ;}

        else if(NumberActivity.getReturnString() != null){
        alert.setTitle(NumberActivity.getReturnString());
        NumberActivity.setReturnString(null)
        ;}

        else{

        }

        color = RouletteClass.getTheField().getColor().toString();

        if(color.equals("BLACK")){
            color = "schwarz";
        }
        else if(color.equals("RED")){
            color = "rot";
        }
        else{
            color = "green";
        }

        theNumberIs = Integer.toString(RouletteClass.getRandomNumber());
        alert.setMessage("Die Zahl lautet: " + theNumberIs + ", die Farbe ist " + color + ".");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return alert.create();
    }
}
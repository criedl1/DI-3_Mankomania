package com.example.mankomania.Roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.mankomania.R;

public class PopClass extends AppCompatDialogFragment {

    String theNumberIs;
    String color;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        if (ColorActivity.getReturnString() != null) {
            alert.setTitle(ColorActivity.getReturnString());
            ColorActivity.setReturnString(null);
        } else if (DozenActivity.getReturnString() != null) {
            alert.setTitle(DozenActivity.getReturnString());
            DozenActivity.setReturnString(null);
        } else if (NumberActivity.getReturnString() != null) {
            alert.setTitle(NumberActivity.getReturnString());
            NumberActivity.setReturnString(null);
        }

        color = RouletteClass.getTheField().getColor().toString();

        switch (color) {
            case "BLACK":
                color = getString(R.string.roulette_black);
                break;
            case "RED":
                color = getString(R.string.roulette_red);
                break;
            default:
                color = getString(R.string.roulette_green);
                break;
        }

        theNumberIs = Integer.toString(RouletteClass.getRandomNumber());
        alert.setMessage(getString(R.string.roulette_result, theNumberIs, color));
        alert.setPositiveButton(getString(R.string.roulette_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return alert.create();
    }




}

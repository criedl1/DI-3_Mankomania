package com.example.mankomania.slotmachine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.mankomania.R;

public class PopClass extends AppCompatDialogFragment {

    private int amount;
    private String returnString;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        amount = getArguments().getInt("amount");
        returnString = getArguments().getString("returnString");


        alert.setTitle(getString(R.string.slot_you_have) + returnString);
        alert.setMessage(getString(R.string.slot_you_have) + amount + returnString);
        alert.setPositiveButton(getString(R.string.casino_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //by letting this empty, the pop-up closes when button is pressed.
            }
        });
        return alert.create();
    }
}
package com.example.mankomania.roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.mankomania.R;

public class ErrorPopUp extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());


        alert.setTitle(getString(R.string.roulette_invalid_number));
        alert.setMessage(getString(R.string.roulette_enter_number_between));
        alert.setPositiveButton(getString(R.string.roulette_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //by letting this empty, the pop-up closes
            }
        });

        return alert.create();
    }
}
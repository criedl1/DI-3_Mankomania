package com.example.mankomania.slotmachine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import com.example.mankomania.roulette.MainActivityRoulette;

public class PopClass extends AppCompatDialogFragment {

    private int money;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        String returnString = getArguments().getString("returnString");
       String winString = getArguments().getString("winString");
         money = getArguments().getInt("money");


        alert.setTitle(winString);
        alert.setMessage(returnString);
        alert.setPositiveButton("Weiter zum Roulette", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent it = new Intent(getActivity(), MainActivityRoulette.class);
                Bundle bundle = new Bundle();
                bundle.putInt("slotMoney", money);
                it.putExtras(bundle);
                startActivity(it);
                getActivity().finish();
            }
        });
        return alert.create();
    }
}


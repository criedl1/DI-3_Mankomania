package com.example.mankomania.roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.mankomania.R;

public class PopUp extends AppCompatDialogFragment {

    private String theNumberIsString;
    private String colorString;
    private String returnString;
    private int money;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        setCancelable(false);
       String popUpFor = getArguments().getString("popUpFor");

        if (popUpFor.equals("SlotMachineResult")) {
            returnString = getArguments().getString("returnString");
            money = getArguments().getInt("money");
            String winString = getArguments().getString("winString");
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
        }

        else if (popUpFor.equals("RouletteResult")) {
            returnString = getArguments().getString("returnString");
            money = getArguments().getInt("money");
            colorString = getArguments().getString("color");
            int randomNumber = getArguments().getInt("randomNumber"); //toString is not possible here
            theNumberIsString = Integer.toString(randomNumber);

            alert.setTitle(getReturnString());
            if (colorString.equals("BLACK")) {
                colorString = getString(R.string.roulette_black);
            } else if (colorString.equals("RED")) {
                colorString = getString(R.string.roulette_red);
            } else {
                colorString = getString(R.string.roulette_green);
            }

            alert.setMessage(getString(R.string.roulette_result, getTheNumberIsString(), getColorString()));
            alert.setPositiveButton(getString(R.string.casino_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendMoneyChange(money);
                    getActivity().finish();
                }
            });


        }

        else if (popUpFor.equals("Error")){
            alert.setTitle(getString(R.string.roulette_invalid_number));
            alert.setMessage(getString(R.string.roulette_enter_number_between));
            alert.setPositiveButton(getString(R.string.casino_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //by letting this empty, the pop-up closes
                }
            });
        }
        return alert.create();
    }


    private String getTheNumberIsString() {
        return theNumberIsString;
    }

    private String getColorString() {
        return colorString;
    }

    private String getReturnString() {
        return returnString;
    }

    private void sendMoneyChange(int money) {
        SendMoneyClass moneyClass = new SendMoneyClass();
        moneyClass.sendMoneyChange(money);
    }
}
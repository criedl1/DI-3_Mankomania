package com.example.mankomania.map.hotels;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.mankomania.map.Player;

public class BuyHotelDialog extends DialogFragment {
    private Player cPlayer;
    private Hotel hotel;

    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Player getcPlayer() {
        return cPlayer;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        args.getSerializable("PLAYERS");
        cPlayer = (Player) args.getSerializable("CPLAYER");
        hotel = (Hotel) args.getSerializable("HOTEL");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Wollen Sie das Hotel " + hotel.getHotelName() + " kaufen?")
                .setCancelable(false)
                .setPositiveButton("JA!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogPositiveClick(BuyHotelDialog.this);
                        dismiss();
                    }
                })
                .setNegativeButton("NEIN!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(BuyHotelDialog.this);
                        dismiss();
                    }
                });
        return builder.create();
    }
}

package com.example.mankomania.map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mankomania.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

@SuppressLint("ValidFragment")
public class MiniMapDialogFragment extends DialogFragment {
    List<Player> players;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        GameController controller = (GameController) args.getSerializable("PLAYERS");
        players = controller.getPlayers();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View myDialog = inflater.inflate(R.layout.dialog_mini_map, null);

        PhotoView map = myDialog.findViewById(R.id.miniMapImg);
        map.setImageResource(R.drawable.mankomania_minimap);

        builder.setView(myDialog);
        return builder.create();
    }

}

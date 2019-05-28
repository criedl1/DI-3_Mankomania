package com.example.mankomania.map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.example.mankomania.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

@SuppressLint("ValidFragment")
public class MiniMapDialogFragment extends DialogFragment {
    private List<Player> players;
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;
    private PhotoView map;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Bundle args = getArguments();
        GameController controller = (GameController) args.getSerializable("PLAYERS");
        players = controller.players;
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View myDialog = inflater.inflate(R.layout.dialog_mini_map, null);
        /*myDialog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });*/

        map = myDialog.findViewById(R.id.miniMapImg);
        map.setImageResource(R.drawable.mankomania_minimap);
//        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        // TODO - set Player positions on minimap
        builder.setView(myDialog);
        builder.setPositiveButton(R.string.mini_map_OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            scaleFactor *= scaleGestureDetector.getScaleFactor();
            scaleFactor = Math.max(0.1f,
                    Math.min(scaleFactor, 10.0f));
            map.setScaleX(scaleFactor);
            map.setScaleY(scaleFactor);
            return true;
        }
    }
}

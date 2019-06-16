package com.example.mankomania.map;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.mankomania.R;
import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.SimpleFloatViewManager;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    private final String[] names;
    private final int[] order;
    public MapView mapView;
    public Button yes;

    public CustomDialogClass(MapView a, String[] names) {
        super(a);
        // TODO Auto-generated constructor stub
        this.mapView = a;
        this.names = names;
        this.order = new int[this.names.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_order_dialog);
        yes = findViewById(R.id.save);
        yes.setOnClickListener(this);

        final ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(mapView, R.layout.support_simple_spinner_dropdown_item, this.names);
        //fill with names
        DragSortListView dragSortListView = findViewById(R.id.chose_order);
        dragSortListView.setAdapter(itemAdapter);
        dragSortListView.setDropListener(new DragSortListView.DropListener() {
            @Override
            public void drop(int from, int to) {
                String name = names[from];
                names[from] = names[to];
                names[to] = name;
                int tmpOrder = order[from];
                order[from] = order[to];
                order[to] = tmpOrder;
                itemAdapter.notifyDataSetChanged();
            }
        });

        //prevent the background from turning black while moving
        SimpleFloatViewManager simpleFloatViewManager = new SimpleFloatViewManager(dragSortListView);
        simpleFloatViewManager.setBackgroundColor(Color.TRANSPARENT);
        dragSortListView.setFloatViewManager(simpleFloatViewManager);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save) {
            mapView.sendOrder(this.order);
            this.dismiss();
        }
        dismiss();
    }
}
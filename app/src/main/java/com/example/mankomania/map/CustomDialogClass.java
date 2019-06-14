package com.example.mankomania.map;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.mankomania.R;
import com.mobeta.android.dslv.DragSortListView;

public class CustomDialogClass extends Dialog implements
    android.view.View.OnClickListener {

  private final String[] names;
  public Activity c;
  public Dialog d;
  public Button yes;

  public CustomDialogClass(Activity a, String[] names) {
    super(a);
    // TODO Auto-generated constructor stub
    this.c = a;
    this.names = names;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.choose_order_dialog);
    yes = (Button) findViewById(R.id.save);
    yes.setOnClickListener(this);

    final ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(c, R.layout.support_simple_spinner_dropdown_item, this.names) { // The third parameter works around ugly Android legacy. http://stackoverflow.com/a/18529511/145173

    };
    DragSortListView dragSortListView = findViewById(R.id.chose_order);
    dragSortListView.setAdapter(itemAdapter);
    dragSortListView.setDropListener(new DragSortListView.DropListener() {
      @Override public void drop(int from, int to) {
        String name = names[from];
        names[from]=names[to];
        names[to]=name;
        itemAdapter.notifyDataSetChanged();
      }
    });

  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.save) {
      c.finish();
    }
    dismiss();
  }
}
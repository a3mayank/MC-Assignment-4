package com.mayankattri.mc_assignment_4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mayank on 1/11/16.
 */
public class AddNoteFragment extends DialogFragment {



    public AddNoteFragment() {
    }

    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add a To Do Item");

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_add_note, null));
        // Add action buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                addNote();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AddNoteFragment.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    public void addNote() {

        EditText et_title = (EditText) getDialog().findViewById(R.id.ET_title);
        EditText et_detail = (EditText) getDialog().findViewById(R.id.ET_detail);

//        String id = et_id.getText().toString();
        String title = "" + et_title.getText().toString();
        String detail = "" +  et_detail.getText().toString();

        if (!title.equals("") && !detail.equals("")) {
            DBHandler db = new DBHandler(getActivity());

            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date dateobj = new Date();

            Note note = new Note(0, title, detail, df.format(dateobj));
            db.addNote(note);
            MainActivityFragment.notesList.add(note);
            MainActivityFragment.mAdapter.notifyDataSetChanged();

            Toast.makeText(getActivity().getBaseContext(), "Added", Toast.LENGTH_SHORT).show();
        }
    }
}

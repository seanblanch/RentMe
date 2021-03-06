package com.firebase.rentme.dialogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.rentme.R;

public class SelectBedroomsDialog extends AppCompatDialogFragment
{
    private Button upButton;
    private Button downButton;
    private EditText valueEditText;
    SelectBedroomsDialogListener listener;
    private int values;

    public SelectBedroomsDialog(int values)
    {
        this.values = values;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        try {
            listener = (SelectBedroomsDialogListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() +
                    "must implement SelectBedroomsDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.select_room_dialog, null);

        builder.setView(view)
                .setTitle("Select Bedrooms")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.applyBedroomValues(valueEditText.getText().toString(), values);
                    }
                });

        valueEditText = view.findViewById(R.id.roomNumberEditText);
        setEditTextState();

        upButton = view.findViewById(R.id.upButton);
        setUpButtonListener();

        downButton = view.findViewById(R.id.downButton);
        setDownButtonListener();
        setDownButtonState();

        return builder.create();
    }

    private void setUpButtonListener()
    {
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                values++;
                setDownButtonState();
                setEditTextState();
            }
        });
    }

    private void setDownButtonListener()
    {
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(values != 0)
                {
                    values--;
                    setDownButtonState();
                    setEditTextState();
                }
            }
        });
    }

    private void setDownButtonState()
    {
        if(values == 0)
        {
            downButton.setBackgroundResource(R.drawable.down_arrow_grey);
        }
        else
        {
            downButton.setBackgroundResource(R.drawable.down_arrow);
        }
    }

    private void setEditTextState()
    {
        if(values == 0)
        {
            valueEditText.setText("Studio");
        }
        else
        {
            valueEditText.setText(Integer.toString(values));
        }
    }

    public interface SelectBedroomsDialogListener{
        void applyBedroomValues(String bedroomsText, int bedroomsValue);
    }
}




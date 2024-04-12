package com.example.customviewapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class EditFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = EditFragment.class.getSimpleName();

    private ClipboardManager clipboard;
    private ClipData clip;
    private Button btnCopy, btnPaste;
    private EditText editTextCopy;
    private TextView textViewPaste;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit Fragment");
        setIcon(R.drawable.ic_edit);
        resetToolBarState();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        btnCopy = (Button) view.findViewById(R.id.btnCopy);
        btnPaste = (Button) view.findViewById(R.id.btnPaste);
        editTextCopy = (EditText) view.findViewById(R.id.edtCopy);
        textViewPaste = (TextView) view.findViewById(R.id.txtPaste);

        btnCopy.setOnClickListener(this);
        btnPaste.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        Log.d(TAG,"onCreateOptionsMenu()");
        setNavigationIcon(null);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG,"onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCopy) {
            Log.d(TAG,"onClick btnCopy");
            String textToCopy = editTextCopy.getText().toString();
            clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText(null, textToCopy);
            if (clipboard == null) return;
            clipboard.setPrimaryClip(clip);
        } else if (view.getId() == R.id.btnPaste) {
            Log.d(TAG,"onClick btnPaste");
            clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard == null) return;
            clip = clipboard.getPrimaryClip();
            if (clip == null) return;
            ClipData.Item item = clip.getItemAt(0);
            if (item == null) return;
            CharSequence textToPaste = item.getText();
            if (textToPaste == null) return;
            else textViewPaste.setText(textToPaste);
        } else {
            Log.d(TAG,"onClick default");
        }
    }
}
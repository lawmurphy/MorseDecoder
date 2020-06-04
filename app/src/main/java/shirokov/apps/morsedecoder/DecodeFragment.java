package shirokov.apps.morsedecoder;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import static android.content.Context.CLIPBOARD_SERVICE;

public class DecodeFragment extends Fragment {


    private TextView resultString, inputString;
    private Button clearButton, dotButton, dashButton, letterSpaceButton, wordSpaceButton, backspace, pastButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_decode, container, false);
        init(rootView);
        setListeners();




        return rootView;



    }


    private void init(View rootView) {
        inputString = rootView.findViewById(R.id.inputView);
        resultString = rootView.findViewById(R.id.resultView);
        clearButton = rootView.findViewById(R.id.buttonClear);
        dotButton = rootView.findViewById(R.id.dotButton);
        pastButton = rootView.findViewById(R.id.pastBuffer);
        dashButton = rootView.findViewById(R.id.dashButton);
        letterSpaceButton = rootView.findViewById(R.id.letterSpace);
        wordSpaceButton = rootView.findViewById(R.id.wordSpace);
        backspace = rootView.findViewById(R.id.backspace);
    }

    private void setListeners() {

        inputString.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                resultString.setText(Morse.morseToString(inputString.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        clearButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputString.setText("");
                    }
                }
        );

        dotButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputString.append(".");
                    }
                }
        );

        dashButton.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        inputString.append("-");
                    }
                }
        );

        letterSpaceButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputString.append(" ");
                    }
                }
        );

        wordSpaceButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputString.append(" / ");
                    }
                }
        );

        try {
            backspace.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            inputString.setText(inputString.getText().toString().substring(0, inputString.length() - 1));
                        }
                    }
            );
        } catch (Exception e) {

        }

        pastButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Button button = (Button) v;
                            ClipboardManager clipboard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(CLIPBOARD_SERVICE);
                            ClipData clipData = clipboard.getPrimaryClip();
                            if (clipData != null) inputString.setText(clipData.getItemAt(0).getText());

                    }
                }
        );


    }










}

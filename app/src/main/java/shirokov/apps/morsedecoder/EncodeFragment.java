package shirokov.apps.morsedecoder;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class EncodeFragment extends Fragment {

    private EditText inputString;
    private TextView resultString;
    private Button buttonClear, copyButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_encode, container, false);
        init(rootView);
        setListeners();

        return rootView;

    }


    private void init(View rootView) {
        inputString = rootView.findViewById(R.id.inputView);
        resultString = rootView.findViewById(R.id.resultView);
        // inputString.setMovementMethod(new ScrollingMovementMethod());
        buttonClear = rootView.findViewById(R.id.buttonClear);
        copyButton = rootView.findViewById(R.id.copyToBuffer);

    }

    private void setListeners() {

            inputString.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                resultString.setText(Morse.stringToMorse(inputString.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

            buttonClear.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            inputString.setText("");
                            inputString.setSelection(0);
                        }
                    }
            );

            copyButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ClipboardManager clipboard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("", resultString.getText().toString());
                            clipboard.setPrimaryClip(clip);
                        }
                    }
            );








    }





}

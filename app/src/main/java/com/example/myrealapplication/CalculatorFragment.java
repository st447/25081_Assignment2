package com.example.myrealapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText input;
    private String inputText = "";
    private double firstValue = 0;
    private String operator = "";

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        input = view.findViewById(R.id.input);

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();
            handleButtonPress(buttonText);
        };

        // Set onClickListener for all buttons
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonClear, R.id.buttonEqual
        };

        for (int id : buttonIds) {
            view.findViewById(id).setOnClickListener(listener);
        }

        return view;
    }

    private void handleButtonPress(String buttonText) {
        switch (buttonText) {
            case "C":
                inputText = "";
                firstValue = 0;
                operator = "";
                input.setText(inputText);
                break;
            case "=":
                if (!inputText.isEmpty() && !operator.isEmpty()) {
                    double secondValue = Double.parseDouble(inputText);
                    double result = 0;
                    switch (operator) {
                        case "+":
                            result = firstValue + secondValue;
                            break;
                        case "-":
                            result = firstValue - secondValue;
                            break;
                        case "*":
                            result = firstValue * secondValue;
                            break;
                        case "/":
                            result = firstValue / secondValue;
                            break;
                    }
                    inputText = String.valueOf(result);
                    input.setText(inputText);
                    firstValue = 0;
                    operator = "";
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!inputText.isEmpty()) {
                    firstValue = Double.parseDouble(inputText);
                    operator = buttonText;
                    inputText = "";
                }
                break;
            default:
                inputText += buttonText;
                input.setText(inputText);
                break;
        }
    }
}

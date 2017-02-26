package org.sfc.currencyconverter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyConverterFragment extends Fragment {

    private static final String KEY_VALUE_IN = "valueIN";
    private static final String KEY_POSITION = "valueIN";

    private double mValueIn;
    private double mValueOut;
    private int mPosition;
    private String mCurrencyName;

    private ArrayAdapter mAdapter;

    public CurrencyConverterFragment() {
        // Required empty public constructor
    }

    private TextInputEditText mInsertValueInputEditText;
    private Spinner mCurrencySpinner;
    private TextView mCurrencyOutTextView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            mValueIn = savedInstanceState.getDouble(KEY_VALUE_IN);
            mPosition = savedInstanceState.getInt(KEY_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_converter, container, false);

        mInsertValueInputEditText = (TextInputEditText) view.findViewById(R.id.insert_currency_textInputEditText);
        mCurrencyOutTextView = (TextView) view.findViewById(R.id.currency_out_text_view);
        mCurrencySpinner = (Spinner) view.findViewById(R.id.currency_spinner);

        mInsertValueInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCurrencyName = mCurrencySpinner.getItemAtPosition(mPosition).toString();
                try {
                    if(mInsertValueInputEditText.getText().toString() == null || mInsertValueInputEditText.getText().toString().isEmpty()) {
                        mValueIn = 0.0;
                    } else {
                        mValueIn = Double.parseDouble(s.toString());
                    }
                    getValueOut(mCurrencyName);
                }catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                mCurrencyName = mCurrencySpinner.getItemAtPosition(position).toString();
                try {
                    if(mInsertValueInputEditText.getText().toString() == null || mInsertValueInputEditText.getText().toString().isEmpty()) {
                        mValueIn = 0.0;
                    } else {
                        mValueIn = Double.parseDouble(mInsertValueInputEditText.getText().toString());
                    }
                    getValueOut(mCurrencyName);
                }catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerAdapter();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(KEY_VALUE_IN, mValueIn);
        outState.putInt(KEY_POSITION, mPosition);
    }

    private void getValueOut(String currencyName) {
        mValueOut = mValueIn * (CurrencyEnum.findByKey(currencyName).getCurrencyValue());
        mCurrencyOutTextView.setText(String.valueOf(mValueOut)+"€");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerAdapter();
    }

    private void spinnerAdapter(){
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, CurrencyEnum.getCurrencyNames());
        mAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mCurrencySpinner.setAdapter(mAdapter);
    }

}

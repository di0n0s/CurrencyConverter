package org.sfc.currencyconverter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyConverterFragment extends Fragment {

    private static final String TAG = "CurrencyConverterFragment";

    private double valueIn;

    private Unbinder mUnbinder;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_converter, container, false);

        mInsertValueInputEditText = (TextInputEditText) view.findViewById(R.id.insert_euro_textInputEditText);
        mCurrencyOutTextView = (TextView) view.findViewById(R.id.currency_out_text_view);
        mCurrencySpinner = (Spinner) view.findViewById(R.id.currency_spinner);

        mCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currencyName = mCurrencySpinner.getItemAtPosition(position).toString();
                try {
                    if(mInsertValueInputEditText.getText().toString() == null || mInsertValueInputEditText.getText().toString().isEmpty()) {
                        valueIn = 0.0;
                    } else {
                        valueIn = Double.parseDouble(mInsertValueInputEditText.getText().toString());
                    }
                        switch (currencyName) {
                            case "Dolar estadounidense":
                                double valueOut = valueIn * (CurrencyEnum.EEUUDolar.getCurrencyValue());
                                mCurrencyOutTextView.setText(String.valueOf(valueOut));
                                break;

                        }
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

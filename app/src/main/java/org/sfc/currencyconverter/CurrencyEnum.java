package org.sfc.currencyconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sfcar on 25/02/2017.
 */

public enum CurrencyEnum {
    EEUUDolar("Dolar estadounidense", 0.946835203),
    JapanYen("Yen japonés", 0.00844292951),
    SterlingPound("Libra esterlina", 1.18027),
    SwissFranc("Franco Suizo", 0.939693225),
    AustralianDollar("Dolar australiano", 0.726411968),
    CanadianDollar("Dolar canadiense", 0.723188941),
    SwedishKrona("Corona sueca", 0.104775837),
    HongKongDollar("Dolar de Hong-Kong", 0.121999716),
    NorwegianKrone("Corona noruega", 0.113027506),
    NewZealanderDollar("Dolar neozelandés", 0.681721346);

    private final String mCurrencyName;
    private final double mCurrencyValue;

    CurrencyEnum(String currencyName, double currencyValue) {
        this.mCurrencyName = currencyName;
        this.mCurrencyValue = currencyValue;
    }

    //HashMap para encontrar el field según el valor
    private static final Map<String, CurrencyEnum> map;

    static {
        map = new HashMap<String, CurrencyEnum>();
        for (CurrencyEnum currencyEnum : CurrencyEnum.values()){
            map.put(currencyEnum.mCurrencyName, currencyEnum);
        }
    }

    public static CurrencyEnum findByKey(String position){
        return map.get(position);
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public static List<String> getCurrencyNames(){
        List<String> namesArray = new ArrayList<>();
        for(CurrencyEnum currencyItem : values()){
            namesArray.add(currencyItem.getCurrencyName());
        }
        return namesArray;
    }

    public double getCurrencyValue() {
        return mCurrencyValue;
    }

}

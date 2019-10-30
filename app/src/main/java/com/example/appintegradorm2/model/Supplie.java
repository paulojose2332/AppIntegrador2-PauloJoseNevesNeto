package com.example.appintegradorm2.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Supplie extends RealmObject {

    @PrimaryKey
    private String id;
    private String momentKm;
    private String gasStation;
    private int qtdGas;
    private Date dataPura;

    @Ignore
    private Calendar data;

    public Supplie(){
        id = UUID.randomUUID().toString(); //gerando um id único
    }

    public String getId(){
        return id;
    }

    public String getMomentKm() {
        return momentKm;
    }

    public void setMomentKm(String momentKm) {
        this.momentKm = momentKm;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }

    public Calendar getData() {
        if (dataPura != null){
            //gambiarra para manter um atributo do tipo date suportado pelo realm
            data = Calendar.getInstance();
            data.setTime(dataPura);
        }
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
        //gambiarra para manter um atributo do tipo date suportado pelo realm
        this.dataPura = data.getTime();
    }

    public int getQtdGas() {
        return qtdGas;
    }

    public void setQtdGas(int qtdGas) {
        this.qtdGas = qtdGas;
    }
}

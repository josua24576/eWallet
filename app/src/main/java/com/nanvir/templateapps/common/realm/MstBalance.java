package com.nanvir.templateapps.common.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MstBalance extends RealmObject {

    @PrimaryKey
    private int id;
    private int balanceTypeId;
    private double amount;
    private double rate;

    public MstBalance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalanceTypeId() {
        return balanceTypeId;
    }

    public void setBalanceTypeId(int balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}

package com.itm.cartera_multibanco.model;

import java.math.BigDecimal;

public class Cartera {

    private String cedula;
    private BigDecimal saldo;

    public Cartera() {
    }

    public Cartera(String cedula, BigDecimal saldo) {
        this.cedula = cedula;
        this.saldo = saldo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
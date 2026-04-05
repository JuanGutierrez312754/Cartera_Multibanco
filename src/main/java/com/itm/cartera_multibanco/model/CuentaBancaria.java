package com.itm.cartera_multibanco.model;

import java.math.BigDecimal;

public class CuentaBancaria {

    private String cuenta;
    private String cedula;
    private Integer bancoId;
    private BigDecimal saldo;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String cuenta, String cedula, Integer bancoId, BigDecimal saldo) {
        this.cuenta = cuenta;
        this.cedula = cedula;
        this.bancoId = bancoId;
        this.saldo = saldo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
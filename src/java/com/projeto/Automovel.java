package com.projeto;

import java.util.Objects;

public class Automovel {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double valor;

    public Automovel(String placa, String modelo, String marca, int ano, double valor) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.valor = valor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Placa: " + placa +
               ", Modelo: " + modelo +
               ", Marca: " + marca +
               ", Ano: " + ano +
               ", Valor: R$" + String.format("%.2f", valor);
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%d,%.2f", placa, modelo, marca, ano, valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automovel automovel = (Automovel) o;
        return placa.equalsIgnoreCase(automovel.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa.toLowerCase());
    }
}

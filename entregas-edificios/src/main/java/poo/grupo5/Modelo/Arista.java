package poo.grupo5.Modelo;

import poo.grupo5.Modelo.Estructuras.Estructura;

import java.io.Serializable;
import java.util.Objects;

public class Arista implements Serializable {
    private Estructura destino;
    private int distancia;

    public Arista(Estructura destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    public Estructura getDestino() { return destino; }
    public double getDistancia() { return distancia; }

    @Override
    public String toString() {
        return "Arista{" + "destino=" + destino + ", distancia=" + distancia + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arista arista = (Arista) o;
        return distancia == arista.distancia &&
                Objects.equals(destino, arista.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destino, distancia);
    }
}
package poo.grupo5.Modelo;

import poo.grupo5.Modelo.Estructuras.Estructura;

import java.io.Serializable;
import java.util.Objects;

public class Arista implements Serializable {
    private String destino;
    private int distancia;

    public Arista(String destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    public boolean tieneDestino(String destino) { return destino.equals(this.destino); }
    public String getDestino() { return destino; }
    public int getDistancia() { return distancia; }

    @Override
    public String toString() {
        return "Arista{destino=" + destino + ", distancia=" + distancia + '}';
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
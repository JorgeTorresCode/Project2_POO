package poo.grupo5.Modelo.Vehiculos;

import java.io.Serializable;

public class EBike extends Vehiculo implements Serializable {
    private static int consumoDistancia;
    private static double volumenMin;
    private static double volumenMax;
    private static int ultimoId = 0;

    public EBike() {
        super();
        this.setId(String.format("EB-%03d", ultimoId++));
    }

    public static void setConsumoDistancia(int consumoDistancia) {EBike.consumoDistancia = consumoDistancia;}
    public static void setVolumenMin (double volumenMin) {EBike.volumenMin = volumenMin;}
    public static void setVolumenMax(double volumenMax) {EBike.volumenMax = volumenMax;}

    @Override
    public double getVolumenMin(){return volumenMin;}
    public double getVolumenMax(){return volumenMax;}
    public static int estimatedEnergyCost(int distancia){return distancia/100 *consumoDistancia;}

    @Override
    public String toString() {
        return "EBike{id=" + getId() + ", volumenMax=" + volumenMax + ", volumenMin" + volumenMin + "}";
    }
}
package poo.grupo5.Modelo;

import poo.grupo5.Excepciones.MiExcepcion;

import java.util.ArrayList;

public class CentroVehiculos extends Estructura {
    private ArrayList<Vehiculo> vehiculos;
    private int capacidadMax;
    private static int ultimoId = 0;

    public CentroVehiculos(int capacidadMax) {
        this.capacidadMax = capacidadMax;
        this.vehiculos = new ArrayList<>();
        this.id = String.format("C-%03d", ultimoId++);
    }

    public boolean esCentro() { return true; }

    public void setCapacidadMax(int capacidadMax) { this.capacidadMax = capacidadMax; }


}
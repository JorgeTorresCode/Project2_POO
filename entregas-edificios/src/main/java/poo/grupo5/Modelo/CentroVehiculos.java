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

    public void crearVehiculo(int tipo, int cantidad,double pesoMax, double pesoMin,int consumoBateria){
        switch(tipo){
            case 0:
                Dron.setConsumoDistancia(consumoBateria);
                Dron.setVolumenMin(pesoMin);
                Dron.setVolumenMax(pesoMax);
                for (int i=0; i<cantidad; i++) {
                    Dron dron = new Dron();
                    vehiculos.add(dron);
                }
                break;
            case 1:
                Rover.setConsumoDistancia(consumoBateria);
                Rover.setVolumenMin(pesoMin);
                Rover.setVolumenMax(pesoMax);
                for (int i=0; i<cantidad; i++) {
                    Rover rover = new Rover();
                    vehiculos.add(rover);
                }
                break;

            case 2:
                EBike.setConsumoDistancia(consumoBateria);
                EBike.setVolumenMin(pesoMin);
                EBike.setVolumenMax(pesoMax);
                for (int i=0; i<cantidad; i++) {
                    EBike eBike = new EBike();
                    vehiculos.add(eBike);
                }
        }
    }

    public Vehiculo buscarVehiculoIndicado(int distancia, double peso) throws MiExcepcion {
        double mayorPeso = 0;
        double menorPeso = 0;
        int menorConsumoBateria = 0;
        for (Vehiculo vehiculo : vehiculos) {
            double maxVolTemp = vehiculo.getVolumenMax();
            double minVolTemp = vehiculo.getVolumenMin();
            int maxBatery = vehiculo.estimatedEnergyCost(distancia);
            if(maxVolTemp > peso && minVolTemp < peso && vehiculo.getEnergia() >= maxBatery ){
                return vehiculo;
            }
            if (mayorPeso < maxVolTemp) {mayorPeso = maxVolTemp;}
            if (menorPeso > minVolTemp) {menorPeso = minVolTemp;}
            if (menorConsumoBateria > maxBatery) {menorConsumoBateria = maxBatery;}
        }
        if (peso > mayorPeso) {throw new MiExcepcion(2);}
        if (peso < menorPeso) {throw new MiExcepcion(3);}
        if (menorConsumoBateria >= 100){throw new MiExcepcion(0);}
        throw new MiExcepcion(1);
    }

}
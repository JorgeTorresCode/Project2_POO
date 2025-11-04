package poo.grupo5.Logica;

import java.util.ArrayList;
import java.util.Collection;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.*;
public class AdmVehiculos {
    private Collection<Vehiculo> listaVehiculos;

    public AdmVehiculos(){
        this.listaVehiculos = new ArrayList<>();
    }

    public void crearVehiculo(int tipo, int cantidad,double pesoMax, double pesoMin,int consumoBateria){
        switch(tipo){
            case 0:
                Dron.setConsumoDistancia(consumoBateria);
                Dron.setVolumenMin(pesoMin);
                Dron.setVolumenMax(pesoMax);
                for (int i=0; i<cantidad; i++) {
                    Dron dron = new Dron();
                    listaVehiculos.add(dron);
                }
                break;
            case 1:
                Rover.setConsumoDistancia(consumoBateria);
                Rover.setVolumenMin(pesoMin);
                Rover.setVolumenMax(pesoMax);
                for (int i=0; i<cantidad; i++) {
                    Rover rover = new Rover();
                    listaVehiculos.add(rover);
                }
                break;

            case 2:
                EBike.setConsumoDistancia(consumoBateria);
                EBike.setVolumenMin(pesoMin);
                EBike.setVolumenMax(pesoMax);
                for (int i=0; i<cantidad; i++) {
                    EBike eBike = new EBike();
                    listaVehiculos.add(eBike);
                }
        }
    }

    public Vehiculo buscarVehiculoIndicado(int distancia, double peso) throws MiExcepcion {
        double mayorPeso = 0;
        double menorPeso = 0;
        int menorConsumoBateria = 0;
        for (Vehiculo vehiculo : listaVehiculos) {
            double maxVolTemp = vehiculo.getVolumenMax();
            double minVolTemp = vehiculo.getVolumenMin();
            int maxBatery = vehiculo.estimatedEnergyCost(distancia);
            if(maxVolTemp > peso && minVolTemp < peso && vehiculo.getEnergia() >= maxBatery ){
                vehiculo.setEnergia(maxBatery);
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

    public void liberarVehiculo(String id) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getId().equals(id)) {
                vehiculo.setTareaActual(null);
            }
        }
    }
    public Collection<Integer> bateriaPorTipo(ArrayList<Integer> lista1) {
        lista1.set(0,Dron.estimatedEnergyCost(lista1.get(0)));
        lista1.set(1, Rover.estimatedEnergyCost(lista1.get(1)));
        lista1.set(2, EBike.estimatedEnergyCost(lista1.get(2)));
        return lista1;
    }

}

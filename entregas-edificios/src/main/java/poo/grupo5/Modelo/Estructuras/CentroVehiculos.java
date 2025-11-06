package poo.grupo5.Modelo.Estructuras;

import poo.grupo5.Enumerates.EstadoVehiculo;
import poo.grupo5.Excepciones.ICODIGOS;
import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.Vehiculos.Dron;
import poo.grupo5.Modelo.Vehiculos.EBike;
import poo.grupo5.Modelo.Vehiculos.Rover;
import poo.grupo5.Modelo.Vehiculos.Vehiculo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CentroVehiculos extends Estructura implements Serializable {
    private ArrayList<Vehiculo> listaVehiculos;
    private static int ultimoId = 0;

    public CentroVehiculos() {
        this.listaVehiculos = new ArrayList<>();
        this.id = String.format("C-%03d", ultimoId++);
    }

    public void crearVehiculos(int tipo, int cantidad, double pesoMax, double pesoMin, int consumoBateria) {
        switch(tipo) {
            case 0:
                Dron.setConsumoDistancia(consumoBateria);
                Dron.setVolumenMin(pesoMin);
                Dron.setVolumenMax(pesoMax);
                for (int i = 0; i < cantidad; i++) {
                    listaVehiculos.add(new Dron());
                }
                break;
            case 1:
                Rover.setConsumoDistancia(consumoBateria);
                Rover.setVolumenMin(pesoMin);
                Rover.setVolumenMax(pesoMax);
                for (int i = 0; i < cantidad; i++) {
                    listaVehiculos.add(new Rover());
                }
                break;
            case 2:
                EBike.setConsumoDistancia(consumoBateria);
                EBike.setVolumenMin(pesoMin);
                EBike.setVolumenMax(pesoMax);
                for (int i = 0; i < cantidad; i++) {
                    listaVehiculos.add(new EBike());
                }
                break;
        }
    }

    public Vehiculo buscarVehiculoIndicado(int distancia, double peso) throws MiExcepcion {
        double mayorPeso = 0;
        double menorPeso = Double.MAX_VALUE;
        int menorConsumoBateria = Integer.MAX_VALUE;

        for (Vehiculo vehiculo : listaVehiculos) { // ✅ Cambiado 'lista' por 'listaVehiculos'
            double maxVolTemp = vehiculo.getVolumenMax();
            double minVolTemp = vehiculo.getVolumenMin();
            int maxBatery = vehiculo.estimatedEnergyCost(distancia); // ✅ Llamada de instancia

            if (maxVolTemp >= peso && minVolTemp <= peso &&
                    vehiculo.getEnergia() >= maxBatery &&
                    vehiculo.getEstado() == EstadoVehiculo.DISPONIBLE) {

                vehiculo.setEstado(EstadoVehiculo.EN_ENTREGA);
                vehiculo.setEnergia(vehiculo.getEnergia() - maxBatery);
                return vehiculo;
            }
            if (maxVolTemp > mayorPeso) {
                mayorPeso = maxVolTemp;
            }
            if (minVolTemp < menorPeso) {
                menorPeso = minVolTemp;
            }
            if (maxBatery < menorConsumoBateria) {
                menorConsumoBateria = maxBatery;
            }
        }
        if (peso > mayorPeso) {
            throw new MiExcepcion(ICODIGOS.ExcesiveVolumeException);
        }
        if (peso < menorPeso) {
            throw new MiExcepcion(ICODIGOS.LowVolumeException);
        }
        if (menorConsumoBateria >= 100) {
            throw new MiExcepcion(ICODIGOS.ExcesiveVolumeException);
        }
        throw new MiExcepcion(ICODIGOS.NoVehicleAvailableException);
    }

    public void liberarVehiculo(String id) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getId().equals(id)) {
                vehiculo.setEstado(EstadoVehiculo.DISPONIBLE);
                if (vehiculo.getEnergia() <= 15) {
                    vehiculo.charge();
                }
            }
        }
    }

    public Collection<Integer> bateriaPorTipo(ArrayList<Integer> lista1) {
        lista1.set(0, Dron.estimatedEnergyCost(lista1.get(0)));
        lista1.set(1, Rover.estimatedEnergyCost(lista1.get(1)));
        lista1.set(2, EBike.estimatedEnergyCost(lista1.get(2)));
        return lista1;
    }

    public int bateriaConsumida(ArrayList<Integer> lista1) {
        lista1.set(0, Dron.estimatedEnergyCost(lista1.get(0)));
        lista1.set(1, Rover.estimatedEnergyCost(lista1.get(1)));
        lista1.set(2, EBike.estimatedEnergyCost(lista1.get(2)));
        int resp = 0;
        for(int actual : lista1) {
            resp += actual;
        }
        return resp;
    }

    public boolean esCentro() {
        return true;
    }

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    @Override
    public String toString() {
        return "CentroVehiculos{id=" + id + ", capacidadMax=Ilimitada}";
    }
}
package poo.grupo5.Logica;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.*;
import poo.grupo5.Modelo.CentroVehiculos;

import java.util.ArrayList;
import java.util.Collection;

public class Control {
    private CampusMap campusMap;
    private AdmVehiculos centroVehiculos;
    private AdmPedidos admPedidos;

    public Control() {
        campusMap = new CampusMap();
        centroVehiculos = new AdmVehiculos();
        admPedidos = new AdmPedidos();
    }

    public void crearEdificios(int cantEdificios) {
        for (int i = 0; i < cantEdificios; i++) {
            campusMap.crearEstructura(false);
        }
    }

    public void crearCentroVehiculos() {
        campusMap.crearEstructura(true);
    }

    public void crearDrones(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        centroVehiculos.crearVehiculo(0, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearRovers(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        centroVehiculos.crearVehiculo(1, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearEBikes(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        centroVehiculos.crearVehiculo(2, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void generarPedido(String desc, double peso, Edificio origen, Edificio destino) throws MiExcepcion {
        int distancia = 30;
        Vehiculo vehiculo = centroVehiculos.buscarVehiculoIndicado(distancia, peso);
        admPedidos.crearPedido(desc, peso, origen, destino, vehiculo.getId(), distancia);
    }

    public void empezarPedido() throws MiExcepcion {
        admPedidos.realizarPedido();
    }

    public void terminarPedido() {
        String id = admPedidos.TerminarPedido();
        centroVehiculos.liberarVehiculo(id);
    }

    public Collection<Integer> consultarEnergiaPorVehiculo() {
        ArrayList<Integer> lista1 =  admPedidos.distanciaPorVehiculo();
        return centroVehiculos.bateriaPorTipo(lista1);
    }
}

package poo.grupo5.Logica;

import poo.grupo5.Modelo.*;
import poo.grupo5.Modelo.CentroVehiculos;

public class Control {
    private CampusMap campusMap;
    private CentroVehiculos centroVehiculos;
    private AdmPedidos admPedidos;

    public Control() {
        campusMap = new CampusMap();
        centroVehiculos = new CentroVehiculos(1024);
        admPedidos = new AdmPedidos();
    }
    public void crearEdificios(int cantEdificios) {
        for (int i=0; i<cantEdificios; i++) {
            campusMap.crearEstructura(false);
        }
    }

    public void crearCentroVehiculos() {
        campusMap.crearEstructura(true);
    }

    public void crearDrones(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {centroVehiculos.crearVehiculo(0,cantidad,pesoMax,pesoMin,consumoBateria);}
    public void crearRovers(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {centroVehiculos.crearVehiculo(1,cantidad,pesoMax,pesoMin,consumoBateria);}
    public void crearEBikes(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {centroVehiculos.crearVehiculo(2,cantidad,pesoMax,pesoMin,consumoBateria);}

    public void generarPedido(String desc, double peso, Edificio origen, Edificio destino) {
        int distacia = //lo que implementaste con grafos
         Vehiculo vehiculo = centroVehiculos.buscarVehiculoIndicado(distancia, peso);
        admPedidos.crearPedido(desc, peso,origen,destino,vehiculo);
    }

}

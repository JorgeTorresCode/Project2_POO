package poo.grupo5.Logica;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.EstadoCampus;
import poo.grupo5.Modelo.Estructuras.Edificio;
import poo.grupo5.Modelo.Estructuras.Estructura;
import poo.grupo5.Modelo.Vehiculos.Vehiculo;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class Control implements Serializable {
    private CampusMap campusMap;
    private AdmPedidos admPedidos;
    private AdmEstructuras admEstructuras;

    public Control() {
        campusMap = new CampusMap();
        admPedidos = new AdmPedidos();
        admEstructuras = new AdmEstructuras();
    }

    public void crearEdificios(int cantEdificios) {
        for (int i = 0; i < cantEdificios; i++) {
            String id = admEstructuras.crearEdificio();
            campusMap.agregarNodo(id);
        }
    }

    public void relacionarEstructuras(String origen, String destino, int distancia) {
        campusMap.agregarArista(origen, destino, distancia);
    }

    public void crearCentroVehiculos() {
        String id = admEstructuras.crearCentro();
        campusMap.agregarNodo(id);
    }

    public void crearDrones(int cantidad, double pesoMax, double pesoMin, int consumoBateria) {
        admEstructuras.crearVehiculos(0, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearRovers(int cantidad, double pesoMax, double pesoMin, int consumoBateria) {
        admEstructuras.crearVehiculos(1, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearEBikes(int cantidad, double pesoMax, double pesoMin, int consumoBateria) {
        admEstructuras.crearVehiculos(2, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void generarPedido(String descripcion, double peso, Edificio origen, Edificio destino, LocalDateTime fechaInicio) throws MiExcepcion {
        int distancia = campusMap.obtenerDistancia(origen.getId(), destino.getId());
        Vehiculo vehiculo = admEstructuras.buscarVehiculoIndicado(distancia, peso);
        admPedidos.crearPedido(descripcion, peso, origen, destino, vehiculo.getId(), distancia, fechaInicio);
    }

    public void empezarPedido() throws MiExcepcion {
        admPedidos.realizarPedido();
    }

    public void terminarPedido() {
        String id = admPedidos.terminarPedidos();
        admEstructuras.liberarVehiculo(id);
    }

    public Collection<Integer> consultarEnergiaPorVehiculo() {
        ArrayList<Integer> lista1 = admPedidos.distanciaPorVehiculo();
        return admEstructuras.bateriaPorTipo(lista1);
    }

    public int consultarEnergiaGeneral() {
        ArrayList<Integer> lista1 = admPedidos.distanciaPorVehiculo();
        return admEstructuras.bateriaConsumida(lista1);
    }

    public Collection<Integer> consultarTiempoPorVehiculo() {
        return admPedidos.tiempoPorVehiculo();
    }

    public int consultarTiempoGeneral() {
        return admPedidos.tiempoGeneral();
    }

    public String mostrarEstructuras() {
        return admEstructuras.mostrarEstructuras();
    }

    public String mostrarAristas() {
        return campusMap.mostrarAristas();
    }

    public String mostrarVehiculos() {
        return admEstructuras.mostrarVehiculos();
    }

    public String mostrarPedidos() {
        return admPedidos.mostrarPedidos();
    }

    public void guardar() throws IOException {
        EstadoCampus estado = new EstadoCampus(campusMap, admPedidos, admEstructuras);
        GestorArchivos.guardarEstado(estado, "Campus.dat");
    }

    public void cargar() throws IOException, ClassNotFoundException {
        EstadoCampus estado = GestorArchivos.cargarEstado("Campus.dat");
        this.campusMap = estado.campusMap;
        this.admPedidos = estado.admPedidos;
        this.admEstructuras = estado.admEstructuras;
    }

    public Collection<Estructura> consultarEstructuras() {
        return admEstructuras.getEstructuras();
    }

    public Collection<Edificio> consultarEdificios() {
        return admEstructuras.getEdificios();
    }

    public Collection<Vehiculo> consultarVehiculos() {
        return admEstructuras.getVehiculosCentros();
    }

    public CampusMap getCampusMap() {
        return campusMap;
    }
}

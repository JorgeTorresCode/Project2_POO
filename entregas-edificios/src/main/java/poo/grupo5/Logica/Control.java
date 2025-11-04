package poo.grupo5.Logica;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.EstadoCampus;
import poo.grupo5.Modelo.Estructuras.Edificio;
import poo.grupo5.Modelo.Estructuras.Estructura;
import poo.grupo5.Modelo.Vehiculos.Vehiculo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Control implements Serializable {
    private CampusMap campusMap;
    private AdmVehiculos admVehiculos;
    private AdmPedidos admPedidos;

    public Control() {
        campusMap = new CampusMap();
        admVehiculos = new AdmVehiculos();
        admPedidos = new AdmPedidos();
    }

    public void crearEdificios(int cantEdificios) {
        for (int i = 0; i < cantEdificios; i++) {
            campusMap.crearEdificio();
        }
    }

    public void relacionarEstructuras(String idOrigen, String idDestino, int distancia) {
        campusMap.agregarAristaDoble(idOrigen, idDestino, distancia);
    }

    public void crearCentroVehiculos() {
        campusMap.crearCentro();
    }

    public void crearDrones(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        admVehiculos.crearVehiculo(0, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearRovers(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        admVehiculos.crearVehiculo(1, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearEBikes(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        admVehiculos.crearVehiculo(2, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void generarPedido(String desc, double peso, Edificio origen, Edificio destino) throws MiExcepcion {
        int distancia = 30;
        Vehiculo vehiculo = admVehiculos.buscarVehiculoIndicado(distancia, peso);
        admPedidos.crearPedido(desc, peso, origen, destino, vehiculo.getId(), distancia);
    }

    public void empezarPedido() throws MiExcepcion {
        admPedidos.realizarPedido();
    }

    public void terminarPedido() {
        String id = admPedidos.TerminarPedido();
        admVehiculos.liberarVehiculo(id);
    }

    public Collection<Integer> consultarEnergiaPorVehiculo() {
        ArrayList<Integer> lista1 = admPedidos.distanciaPorVehiculo();
        return admVehiculos.bateriaPorTipo(lista1);
    }

    public int consultarEnergiaGeneral() {
        ArrayList<Integer> lista1 = admPedidos.distanciaPorVehiculo();
        return admVehiculos.bateriaConsumida(lista1);
    }

    public Collection<Integer> consultarTiempoPorVehiculo() {
        return admPedidos.tiempoPorVehiculo();
    }

    public int consultarTiempoGeneral() {
        return admPedidos.tiempoGeneral();
    }

    public String mostrarEstructuras() {
        return campusMap.mostrarEstructuras();
    }

    public String mostrarAristas() {
        return campusMap.mostrarAristas();
    }

    public void guardar() throws IOException {
        EstadoCampus estado = new EstadoCampus(campusMap, admVehiculos, admPedidos);
        GestorArchivos.guardarEstado(estado, "Campus.dat");
    }

    public void cargar() throws IOException, ClassNotFoundException {
        EstadoCampus estado = GestorArchivos.cargarEstado("Campus.dat");
        this.campusMap = estado.campusMap;
        this.admVehiculos = estado.admVehiculos;
        this.admPedidos = estado.admPedidos;
    }

    public Collection<Estructura> consultarEstructuras() {
        return campusMap.getPuntosDelCampus();
    }
}

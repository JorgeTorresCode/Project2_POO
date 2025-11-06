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
    private AdmPedidos admPedidos;
    private AdmEstructuras admEstructuras;

    public Control() {
        campusMap = new CampusMap();
        admPedidos = new AdmPedidos();
        admEstructuras = new AdmEstructuras();
    }

    public void crearEdificios(int cantEdificios) {
        for (int i = 0; i < cantEdificios; i++) {
            campusMap.crearEdificio(admEstructuras.crearEdificio());
        }
    }

    public void relacionarEstructuras(String idOrigen, String idDestino, int distancia) {
        campusMap.agregarAristaDoble(idOrigen, idDestino, distancia,admEstructuras.encontrarEdificio(idOrigen), admEstructuras.encontrarEdificio(idDestino));
    }

    public void crearCentroVehiculos() {
        campusMap.crearCentro();
    }

    public void crearDrones(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        campusMap.crearVehiculo(0, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearRovers(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        campusMap.crearVehiculo(1, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void crearEBikes(int cantidad, int pesoMax, int pesoMin, int consumoBateria) {
        campusMap.crearVehiculo(2, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    public void generarPedido(String desc, double peso, Edificio origen, Edificio destino) throws MiExcepcion {
        int distancia = 30;
        Vehiculo vehiculo = campusMap.buscarVehiculoIndicado(distancia, peso);
        admPedidos.crearPedido(desc, peso, origen, destino, vehiculo.getId(), distancia);
    }

    public void empezarPedido() throws MiExcepcion {
        admPedidos.realizarPedido();
    }

    public void terminarPedido() {
        String id = admPedidos.TerminarPedido();
        campusMap.liberarVehiculo(id);
    }

    public Collection<Integer> consultarEnergiaPorVehiculo() {
        ArrayList<Integer> lista1 = admPedidos.distanciaPorVehiculo();
        return campusMap.bateriaPorTipo(lista1);
    }

    public int consultarEnergiaGeneral() {
        ArrayList<Integer> lista1 = admPedidos.distanciaPorVehiculo();
        return campusMap.bateriaConsumida(lista1);
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

    public void guardar() throws IOException {
        EstadoCampus estado = new EstadoCampus(campusMap, admPedidos);
        GestorArchivos.guardarEstado(estado, "Campus.dat");
    }

    public void cargar() throws IOException, ClassNotFoundException {
        EstadoCampus estado = GestorArchivos.cargarEstado("Campus.dat");
        this.campusMap = estado.campusMap;
        this.admPedidos = estado.admPedidos;
    }

    public Collection<Estructura> consultarEstructuras() {
        return admEstructuras.getPuntosDelCampus();
    }
}

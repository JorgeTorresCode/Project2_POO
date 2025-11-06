package poo.grupo5.Logica;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.Estructuras.CentroVehiculos;
import poo.grupo5.Modelo.Estructuras.Edificio;
import poo.grupo5.Modelo.Estructuras.Estructura;
import poo.grupo5.Modelo.Vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.Collection;


public class AdmEstructuras {
    private ArrayList<Estructura> listaEstructuras;
    private CentroVehiculos centroVehiculos;

    public AdmEstructuras() {
        this.listaEstructuras = new ArrayList<>();
    }

    public String crearEdificio() {
        Estructura nueva = new Edificio();
        this.listaEstructuras.add(nueva);
        return nueva.getId();
    }
    public Estructura encontrarEdificio(String id){
        for (Estructura e: this.listaEstructuras) {
            if (e.getId().equals(id)) { return e;}
        }
        return null;
    }

    public String crearCentro() {
        CentroVehiculos nueva = new CentroVehiculos();
        centroVehiculos = nueva;
        listaEstructuras.add(nueva);
        return nueva.getId();
    }

    public void crearVehiculos(int tipo, int cantidad, double pesoMax, double pesoMin, int consumoBateria) {
        centroVehiculos.crearVehiculos(tipo, cantidad, pesoMax, pesoMin, consumoBateria);
    }

    // Delegar la búsqueda de un vehículo según el peso y la distancia
    public Vehiculo buscarVehiculoIndicado(int distancia, double peso) throws MiExcepcion {
        return centroVehiculos.buscarVehiculoIndicado(distancia, peso);
    }

    // Delegar la liberación de un vehículo por su ID
    public void liberarVehiculo(String id) {
        centroVehiculos.liberarVehiculo(id);
    }

    // Delegar la obtención de la batería por tipo de vehículo
    public Collection<Integer> bateriaPorTipo(ArrayList<Integer> lista1) {
        return centroVehiculos.bateriaPorTipo(lista1);
    }

    // Delegar la batería consumida
    public int bateriaConsumida(ArrayList<Integer> lista1) {
        return centroVehiculos.bateriaConsumida(lista1);
    }

    public String mostrarEstructuras() {
        StringBuilder sb = new StringBuilder();
        for (Estructura e : listaEstructuras) {
            sb.append(e.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public Collection<Estructura> getEstructuras() {
        return listaEstructuras;
    }

    public Collection<Edificio> getEdificios() {
        Collection<Edificio> edificios = new ArrayList<>();
        for (Estructura e: listaEstructuras) {
            if (!e.esCentro()) {
                Edificio edificio = (Edificio) e;
                edificios.add(edificio);
            }
        }
        return edificios;
    }

    public Collection<Vehiculo> getVehiculosCentros() {
        Collection<Vehiculo> vehiculos = new ArrayList<>();
        for (Estructura e: listaEstructuras) {
            if (e.esCentro()) {
                CentroVehiculos c = (CentroVehiculos) e;
                vehiculos.addAll(c.getListaVehiculos());
            }
        }
        return vehiculos;
    }

    public String mostrarVehiculos() {
        StringBuilder sb = new StringBuilder();
        for (Vehiculo v : centroVehiculos.getListaVehiculos()) {
            sb.append(v.toString())
                    .append("\n");
        }
        return sb.toString();
    }
}

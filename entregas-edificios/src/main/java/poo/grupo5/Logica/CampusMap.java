package poo.grupo5.Logica;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.*; // Incluye Estructura, Arista, CentroVehiculos
import poo.grupo5.Modelo.Estructuras.CentroVehiculos;
import poo.grupo5.Modelo.Estructuras.Edificio;
import poo.grupo5.Modelo.Estructuras.Estructura;
import poo.grupo5.Modelo.Vehiculos.Vehiculo;

import java.io.Serializable;
import java.util.*;

public class CampusMap implements Serializable {
    private Map<String, ArrayList<Arista>> adyacencia;
    private CentroVehiculos centroVehiculos;

    public CampusMap () {
        this.adyacencia = new HashMap<>();
        this.centroVehiculos = new CentroVehiculos(1024);
    }

    public void crearEdificio(String id) {
        adyacencia.put(id, new ArrayList<>());
    }

    public void crearCentro() {
        Estructura nueva =  new CentroVehiculos(1000);

        adyacencia.put(nueva.getId(), new ArrayList<>());
    }

    public void agregarAristaDoble(String origenId, String destinoId, int distancia, Estructura  origen, Estructura destino) {

        adyacencia.get(origenId).add(new Arista(destino, distancia));
        adyacencia.get(destinoId).add(new Arista(origen, distancia));
    }


    public String mostrarAristas() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ArrayList<Arista>> entry : adyacencia.entrySet()) {
            String id = entry.getKey();
            ArrayList<Arista> aristas = entry.getValue();
            for (Arista arista : aristas) {
                String destinoId = arista.getDestino().getId();
                sb.append(id)
                        .append(" -> ")
                        .append(destinoId)
                        .append("\n");
            }
        }
        return sb.toString();
    }

    public void crearVehiculo(int tipo, int cantidad, double pesoMax, double pesoMin, int consumoBateria) {
        centroVehiculos.crearVehiculo(tipo, cantidad, pesoMax, pesoMin, consumoBateria);
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
}


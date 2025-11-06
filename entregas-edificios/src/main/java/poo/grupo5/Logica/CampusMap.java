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

    public CampusMap() {
        this.adyacencia = new HashMap<>();
    }

    public void agregarNodo(String id) {
        adyacencia.put(id, new ArrayList<>());
    }

    public void agregarArista(String origen, String destino, int distancia) {
        if (adyacencia.containsKey(origen) && adyacencia.containsKey(destino)) {
            adyacencia.get(origen).add(new Arista(destino, distancia));
            adyacencia.get(destino).add(new Arista(origen, distancia));
        } else {
            System.out.println("ERROR: Nodos no existen - " + origen + " o " + destino);
        }
    }

    public int obtenerDistancia(String origen, String destino) {
        if (adyacencia.containsKey(origen) && adyacencia.containsKey(destino)) {
            ArrayList<Arista> arr = adyacencia.get(origen);
            for (Arista a : arr) {
                if (a.tieneDestino(destino)) {
                    return a.getDistancia();
                }
            }
        }
        return -1;
    }

    public String mostrarAristas() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ArrayList<Arista>> entry : adyacencia.entrySet()) {
            String nodo = entry.getKey();
            ArrayList<Arista> aristas = entry.getValue();
            for (Arista a : aristas) {
                sb.append(nodo)
                        .append("  → ")
                        .append(a.toString())
                        .append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
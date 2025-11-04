package poo.grupo5.Logica;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.*; // Incluye Estructura, Arista, CentroVehiculos
import poo.grupo5.Excepciones.ICODIGOS;
import java.util.*;

public class CampusMap {
    private Map<String, Estructura> puntos;
    private Map<String, ArrayList<Arista>> adyacencia;
    private CentroVehiculos centroVehiculos;

    public CampusMap() {
        this.puntos = new HashMap<>();
        this.adyacencia = new HashMap<>();
        this.centroVehiculos = new CentroVehiculos(1024);
    }

    public void crearEstructura(boolean esCentro) {
        Estructura nueva;
        if (esCentro) nueva = new CentroVehiculos(1000);
        else nueva = new Edificio();
        puntos.put(nueva.getId(), nueva);
        adyacencia.put(nueva.getId(), new ArrayList<>());
    }

    public boolean agregarAristaDoble(String origenId, String destinoId, double distancia) {
        if (!puntos.containsKey(origenId) || !puntos.containsKey(destinoId) || distancia <= 0) return false;
        Estructura destino = puntos.get(destinoId);
        Estructura origen = puntos.get(origenId);
        adyacencia.get(origenId).add(new Arista(destino, distancia));
        adyacencia.get(destinoId).add(new Arista(origen, distancia));
        return true;
    }

    public Estructura obtenerEstructura(String id) {
        return puntos.get(id);
    }

    public double getDistanciaDirecta(String origenId, String destinoId) {
        ArrayList<Arista> conexiones = adyacencia.get(origenId);
        if (conexiones == null) return -1;
        for (Arista a : conexiones) {
            if (a.getDestino().getId().equals(destinoId))
                return a.getDistancia();
        }
        return -1;
    }

    public CentroVehiculos getCentro() {
        for (Estructura e : puntos.values()) {
            if (e.esCentro()) {
                return (CentroVehiculos) e;
            }
        }
        return null;
    }

    public Vehiculo despacharVehiculoDesde(String centroId, double peso) throws MiExcepcion {
        Estructura estructura = encontrarEstructura(centroId);
        if (estructura == null || !estructura.esCentro()) {
            throw new MiExcepcion(ICODIGOS.NoVehicleAvailableException);
        }

        CentroVehiculos centro = (CentroVehiculos) estructura;
        return centro.buscarVehiculoIndicado();
    }

    public Collection<Estructura> getPuntosDelCampus() {
        return puntos.values();
    }


}
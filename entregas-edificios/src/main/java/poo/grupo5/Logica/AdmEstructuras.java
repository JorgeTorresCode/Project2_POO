package poo.grupo5.Logica;

import poo.grupo5.Modelo.Estructuras.CentroVehiculos;
import poo.grupo5.Modelo.Estructuras.Edificio;
import poo.grupo5.Modelo.Estructuras.Estructura;

import java.util.ArrayList;
import java.util.Collection;


public class AdmEstructuras {
    private ArrayList<Estructura> listaEstructuras;

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
        Estructura nueva =  new CentroVehiculos(1000);
        listaEstructuras.add(nueva);
        return nueva.getId();
    }

    public String mostrarEstructuras() {
        StringBuilder sb = new StringBuilder();
        for (Estructura e : listaEstructuras) {
            sb.append(e.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public Collection<Estructura> getPuntosDelCampus() {
        return listaEstructuras;
    }
}

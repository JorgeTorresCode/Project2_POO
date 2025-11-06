package poo.grupo5.Modelo;

import poo.grupo5.Logica.AdmEstructuras;
import poo.grupo5.Logica.AdmPedidos;
import poo.grupo5.Logica.CampusMap;

import java.io.Serializable;

public class EstadoCampus implements Serializable {
    public CampusMap campusMap;
    public AdmPedidos admPedidos;
    public AdmEstructuras admEstructuras;

    public EstadoCampus(CampusMap campus, AdmPedidos admPedidos, AdmEstructuras admEstructuras) {
        this.campusMap = campus;
        this.admPedidos = admPedidos;
        this.admEstructuras = admEstructuras;
    }
}

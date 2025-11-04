package poo.grupo5.Modelo;

import poo.grupo5.Logica.AdmPedidos;
import poo.grupo5.Logica.AdmVehiculos;
import poo.grupo5.Logica.CampusMap;

import java.io.Serializable;

public class EstadoCampus implements Serializable {
    public CampusMap campusMap;
    public AdmVehiculos admVehiculos;
    public AdmPedidos admPedidos;

    public EstadoCampus(CampusMap campus, AdmVehiculos admVehiculos, AdmPedidos admPedidos) {
        this.campusMap = campus;
        this.admVehiculos = admVehiculos;
        this.admPedidos = admPedidos;
    }
}

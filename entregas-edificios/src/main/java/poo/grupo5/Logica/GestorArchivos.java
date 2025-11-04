package poo.grupo5.Logica;

import poo.grupo5.Modelo.EstadoCampus;

import java.io.*;

public class GestorArchivos {
    public static void guardarEstado(EstadoCampus campus, String nombreArchivo) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(campus);
        }
    }
    public static EstadoCampus cargarEstado(String nombreArchivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(nombreArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (EstadoCampus) ois.readObject();
        }
    }
}

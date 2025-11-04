package poo.grupo5.Modelo.Estructuras;

import java.io.Serializable;

public class Edificio extends Estructura implements Serializable {
    private static int ultimoId = 0;

    public Edificio() {
        this.id = String.format("E-%03d", ultimoId++);
    }

    public boolean esCentro() { return false; }

    @Override
    public String toString() {
        return "Edificio{" + "id=" + id + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edificio otroEdificio = (Edificio) obj;
        return id != null && id.equals(otroEdificio.id);
    }
}

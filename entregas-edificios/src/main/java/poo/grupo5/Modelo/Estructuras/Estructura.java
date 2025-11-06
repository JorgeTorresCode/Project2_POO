package poo.grupo5.Modelo.Estructuras;

import java.io.Serializable;

public abstract class Estructura implements Serializable {
    protected String id;

    public String getId() { return id; }
    public abstract boolean esCentro();

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edificio otroEdificio = (Edificio) obj;
        return id != null && id.equals(otroEdificio.id);
    }
}

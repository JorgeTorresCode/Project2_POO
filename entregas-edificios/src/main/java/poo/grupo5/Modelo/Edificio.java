package poo.grupo5.Modelo;

public class Edificio extends Estructura {
    private static int ultimoId = 0;

    public Edificio() {
        this.id = String.format("E-%03d", ultimoId++);
    }

    public boolean esCentro() { return false; }

}

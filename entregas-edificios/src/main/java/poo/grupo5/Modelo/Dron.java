package poo.grupo5.Modelo;

public class Dron extends Vehiculo {
    private static int consumoDistancia;
    private static double volumenMin;
    private static double volumenMax;
    private static int velocidad;
    private static int ultimoId = 0;

    public Dron() {
        super();
        this.setId(String.format("D-%03d", ultimoId++));
    }

    public static void setConsumoDistancia(int consumoDistancia) {Dron.consumoDistancia = consumoDistancia;}
    public static void setVolumenMin (double volumenMin) {Dron.volumenMin = volumenMin;}
    public static void setVolumenMax(double volumenMax) {Dron.volumenMax = volumenMax;}


    @Override
    public double getVolumenMin(){return volumenMin;}
    public double getVolumenMax(){return volumenMax;}
    public static int estimatedEnergyCost(int distancia){return distancia/10 *consumoDistancia;}
}

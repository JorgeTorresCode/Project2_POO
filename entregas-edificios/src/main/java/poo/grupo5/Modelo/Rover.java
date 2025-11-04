package poo.grupo5.Modelo;

public class Rover extends Vehiculo {
    private static int consumoDistancia;
    private static double volumenMin;
    private static double volumenMax;
    private static int ultimoId = 0;

    public Rover(){
        super();
        this.setId(String.format("R-%03d", ultimoId++));
    }
    public static void setConsumoDistancia(int consumoDistancia) {Rover.consumoDistancia = consumoDistancia;}
    public static void setVolumenMin (double volumenMin) {Rover.volumenMin = volumenMin;}
    public static void setVolumenMax(double volumenMax) {Rover.volumenMax = volumenMax;}

    @Override
    public double getVolumenMin(){return volumenMin;}
    public double getVolumenMax(){return volumenMax;}
    public static int estimatedEnergyCost(int distancia){return distancia/10 *consumoDistancia;}
}

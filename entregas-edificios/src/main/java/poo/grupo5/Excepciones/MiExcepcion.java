package poo.grupo5.Excepciones;

public class MiExcepcion  extends Exception {
    private final int codigoError;

    public MiExcepcion(int codigoError) {
        this.codigoError = codigoError;
    }

    @Override
    public String getMessage() {
        String mensaje;
        switch (codigoError) {
            case ICODIGOS.InsufficientBatteryException -> mensaje = "Insufficient battery.";
            case ICODIGOS.NoVehicleAvailableException -> mensaje = "No vehicle available.";
            case ICODIGOS.ExcesiveVolumeException -> mensaje = "Excesive volume.";
            case ICODIGOS.LowVolumeException -> mensaje = "to low volume.";
            default -> mensaje = "Unkown Error.";
        }
        return mensaje;
    }
}
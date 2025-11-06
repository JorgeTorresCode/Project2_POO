package poo.grupo5.Logica;

import poo.grupo5.Enumerates.EstadoPedido;
import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.Estructuras.Edificio;
import poo.grupo5.Modelo.Pedido;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class AdmPedidos implements Serializable {
    private Queue<Pedido> listaPedidos;
    private Queue<Pedido> listaPedidosEnProgreso;
    private ArrayList<Pedido> listaPedidosRealizados;

    public AdmPedidos() {
        this.listaPedidos = new LinkedList<Pedido>();
        this.listaPedidosEnProgreso = new LinkedList<Pedido>();
        this.listaPedidosRealizados = new ArrayList<>();
    }

    public boolean crearPedido(String descripcion, double peso, Edificio origen, Edificio destino,  String vehiculoID, int distancia, LocalDateTime fechaInicio) {
        return listaPedidos.add(new Pedido(descripcion, peso, origen, destino, vehiculoID, distancia, fechaInicio));
    }

    public void realizarPedido() throws MiExcepcion {
        if (listaPedidosRealizados.isEmpty()){throw new MiExcepcion(4);}
        Pedido pedido = listaPedidos.poll();
        assert pedido != null;
        pedido.asignarEstado(EstadoPedido.PREPARACION);
        listaPedidosEnProgreso.add(pedido);
    }

    public String terminarPedidos() {
        Pedido pedido = listaPedidosEnProgreso.poll();
        assert pedido != null;
        pedido.setFechaFin(LocalDateTime.now());
        pedido.setEstadoPedido(EstadoPedido.ENTREGADO);
        listaPedidosRealizados.add(pedido);
        return pedido.getVehiculoID();
    }
    public ArrayList<Integer> distanciaPorVehiculo(){
        int cuentaDrones = 0;
        int cuentaRover = 0;
        int cuentaEBikes = 0;
        for(Pedido pedido : listaPedidosRealizados){
            switch (pedido.getVehiculoID().charAt(0)){
            case 'D':
                cuentaDrones += pedido.getDistanciaTotal();
                break;
                case 'R':
                    cuentaRover += pedido.getDistanciaTotal();
                    break;
                    case 'E':
                        cuentaEBikes+= pedido.getDistanciaTotal();
                        break;
                        default:
                            break;
            }
        }
        ArrayList<Integer> respuestas = new ArrayList<>();
        respuestas.add(cuentaDrones);
        respuestas.add(cuentaRover);
        respuestas.add(cuentaEBikes);
        return respuestas;
    }

    public Collection<Integer> tiempoPorVehiculo() {
        int cuentaDrones = 0;
        int cuentaRover = 0;
        int cuentaEBikes = 0;
        int cuentaDronesT = 0;
        int cuentaRoversT = 0;
        int cuentaBikesT = 0;
        for(Pedido pedido : listaPedidosRealizados){
            switch (pedido.getVehiculoID().charAt(0)){
                case 'D':
                    cuentaDrones++;
                    cuentaDronesT += (int) Duration.between(pedido.getFechaInicio(), LocalDateTime.now()).toSeconds();
                    break;
                case 'R':
                    cuentaRover++;
                    cuentaRoversT += (int) Duration.between(pedido.getFechaInicio(), LocalDateTime.now()).toSeconds();
                    break;
                case 'E':
                    cuentaEBikes++;
                    cuentaBikesT += (int) Duration.between(pedido.getFechaInicio(), LocalDateTime.now()).toSeconds();
                    break;
                default:
                    break;
            }
        }
        ArrayList<Integer> respuestas = new ArrayList<>();
        respuestas.add(cuentaDronesT / cuentaDrones);
        respuestas.add(cuentaRoversT / cuentaRover);
        respuestas.add(cuentaBikesT / cuentaEBikes);
        return respuestas;
    }

    public int tiempoGeneral(){
        int cuentaDrones = 0;
        int cuentaRover = 0;
        int cuentaEBikes = 0;
        int cuentaDronesT = 0;
        int cuentaRoversT = 0;
        int cuentaBikesT = 0;
        for(Pedido pedido : listaPedidosRealizados){
            switch (pedido.getVehiculoID().charAt(0)){
                case 'D':
                    cuentaDrones++;
                    cuentaDronesT += (int) Duration.between(pedido.getFechaInicio(), LocalDateTime.now()).toSeconds();
                    break;
                case 'R':
                    cuentaRover++;
                    cuentaRoversT += (int) Duration.between(pedido.getFechaInicio(), LocalDateTime.now()).toSeconds();
                    break;
                case 'E':
                    cuentaEBikes++;
                    cuentaBikesT += (int) Duration.between(pedido.getFechaInicio(), LocalDateTime.now()).toSeconds();
                    break;
                default:
                    break;
            }
        }
        int resp = 0;
        resp += cuentaDronesT / cuentaDrones;
        resp +=  cuentaRoversT / cuentaRover;
        resp += cuentaBikesT / cuentaEBikes;
        return resp;
    }

    public String mostrarPedidos() {
        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : listaPedidos) {
            sb.append(pedido.toString())
                    .append("\n");
        }
        return sb.toString();
    }
}

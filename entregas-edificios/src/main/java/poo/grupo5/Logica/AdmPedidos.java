package poo.grupo5.Logica;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Modelo.Edificio;
import poo.grupo5.Modelo.Pedido;
import poo.grupo5.Modelo.Vehiculo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class AdmPedidos {
    private Queue<Pedido> listaPedidos;
    private Queue<Pedido> listaPedidosEnProgreso;
    private ArrayList<Pedido> listaPedidosRealizados;

    public AdmPedidos() {
        this.listaPedidos = new LinkedList<Pedido>();
        this.listaPedidosEnProgreso = new LinkedList<Pedido>();
        this.listaPedidosRealizados = new ArrayList<>();
    }

    public boolean crearPedido(String desc, double volume, Edificio edificioOrigen, Edificio edificioDestino, String vehiculoID, int distancia) {
        Pedido pedido = new Pedido(desc,volume,edificioOrigen,edificioDestino,vehiculoID, distancia);
        listaPedidos.add(pedido);
        return true;
    }

    public void realizarPedido() throws MiExcepcion {
        if (listaPedidosRealizados.isEmpty()){throw new MiExcepcion(4);}
        Pedido pedido = listaPedidos.poll();
        pedido.setFechaInicio(LocalDateTime.now());
        pedido.CambiarEtapa();
        }

    public String TerminarPedido() {
        Pedido pedido = listaPedidosEnProgreso.poll();
        pedido.setFechaFin(LocalDateTime.now());
        pedido.CambiarEtapa();
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
                        cuentaEBikes+= pedido.getDistanciaTotal();;
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
}

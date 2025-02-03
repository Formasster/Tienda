/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package es.educastur.tienda2025;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author alu28d
 */
public class Tienda2025 implements Serializable{
    Scanner sc = new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;

    public Tienda2025() {
        this.pedidos = new ArrayList<>();
        this.articulos = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    public HashMap<String, Articulo> getArticulos() {
        return articulos;
    }
    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }


    public static void main(String[] args) {

        

        
    }

    public void stock (int unidadesPed, String id) throws StockAgotado, StockInsuficiente{
        int n=articulos.get(id).getExistencias();
        if (n==0){
            throw new StockAgotado("Stock AGOTADO para el artículo " + articulos.get(id).getDescripcion());
        }else if (n<unidadesPed){        
        
            throw new StockInsuficiente("Stock INSUFICIENTE para el artículo " + articulos.get(id).getDescripcion() + " (sólo quedan " + n + " unidades)");
        }

    }

    public String generaIdPedido (String idCliente){
        int contador = 0;
        String nuevoId;
        for (Pedido p: pedidos){
            if (p.getClientePedido().getDni().equalsIgnoreCase(idCliente)){
                contador++;
            }
        }
        contador++;
        nuevoId = idCliente + "-" + String.format("%03d", contador) + "/" + LocalDate.now().getYear();

        return nuevoId;
    } 

    public void nuevoPedido (String idCliente){

        //ARRAYLIST AUXLIAR PARA CREAR LAS LINEAS DE PEDIDO
        ArrayList <LineaPedido> CestaCompraAux = new ArrayList<>();
        String dniT, idT, opc, pedidasS;
        int pedidas=0;

        sc.nextLine();
        do{
            System.out.println("CLIENTE PEDIDO (DNI):");
            dniT = sc.nextLine().toUpperCase();
            if (condition) {
                
            }
        }

        if()
    }
}

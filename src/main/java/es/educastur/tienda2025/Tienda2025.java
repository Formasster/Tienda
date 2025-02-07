/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package es.educastur.tienda2025;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            if (dniT.isBlank()) break;
            if (!MetodosAux.validarDni(dniT)){
                System.out.println("DNI NO VÁLIDO");
            };
            
        }while (!clientes.containsKey(dniT));

        if (!dniT.isBlank()){ 
            System.out.println("INTRODUZCA LOS ARTÚICULOS QUE DESEA PEDIR UNO A UNO: ");
            do {
                System.out.println("INTRODUZCA EL CÓDIGO DEL ARTÍCULO (99 para terminar):");
                idT = sc.nextLine().toUpperCase();
                if (!idT.isBlank() && articulos.containsKey(idT)) {
                    System.out.println(" (" + articulos.get(idT).getDescripcion() + ") - UNIDADES?");
                    //Entrada de un int sobre un String - metodo esInt
                    do{
                        pedidasS = sc.next();
                    }while(!MetodosAux.esInt(pedidasS));
                    //Conversión de String a int
                    pedidas = Integer.parseInt(pedidasS);

                    try{
                        stock(pedidas, idT);
                        CestaCompraAux.add(new LineaPedido(idT, pedidas));
                    } catch (StockAgotado | StockInsuficiente ex) {
                        System.out.println(ex.getMessage());
                        int disponibles = articulos.get(idT).getExistencias();
                        System.out.println("¿DESEA PEDIR LAS " + disponibles + " UNIDADES DISPONIBLES? (S/N)");
                        opc=sc.next();
                        if (opc.equalsIgnoreCase("S")){
                            CestaCompraAux.add(new LineaPedido(idT, disponibles));
                            
                        }
                    }
                }
            } while (!idT.isBlank());
        }
    }

    public void cargaDatos(){
        
        clientes.put("80580845T",new Cliente("80580845T","ANA ","658111111","ana@gmail.com"));
        clientes.put("36347775R",new Cliente("36347775R","LOLA","649222222","lola@gmail.com"));
        clientes.put("63921307Y",new Cliente("63921307Y","JUAN","652333333","juan@gmail.com"));
        clientes.put("02337565Y",new Cliente("02337565Y","EDU","634567890","edu@gmail.com"));
               
        articulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
        articulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
        articulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
        articulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",9,70));
        articulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",0,200));
        articulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
        articulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",5,100));
        articulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
        articulos.put("4-33",new Articulo("4-33","SAMSUNG ODISSEY G5",12,580));
        
        LocalDate hoy = LocalDate.now();
       pedidos.add(new Pedido("80580845T-001/2024",clientes.get("80580845T"),hoy.minusDays(1), new ArrayList<>
        (List.of(new LineaPedido("1-11",3),new LineaPedido("4-22",3)))));                                                                                                                                                               
       pedidos.add(new Pedido("80580845T-002/2024",clientes.get("80580845T"),hoy.minusDays(2), new ArrayList<>
        (List.of(new LineaPedido("4-11",3),new LineaPedido("4-22",2),new LineaPedido("4-33",4)))));
       pedidos.add(new Pedido("36347775R-001/2024",clientes.get("36347775R"),hoy.minusDays(3), new ArrayList<>
        (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3)))));
       pedidos.add(new Pedido("36347775R-002/2024",clientes.get("36347775R"),hoy.minusDays(5), new ArrayList<>
        (List.of(new LineaPedido("4-33",3),new LineaPedido("2-11",3)))));
       pedidos.add(new Pedido("63921307Y-001/2024",clientes.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
        (List.of(new LineaPedido("2-11",5),new LineaPedido("2-33",3),new LineaPedido("4-33",2)))));
    } 
}

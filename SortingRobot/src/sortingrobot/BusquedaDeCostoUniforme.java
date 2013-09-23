package sortingrobot;

import java.util.PriorityQueue;
import java.util.Vector;

public class BusquedaDeCostoUniforme extends Algoritmo{
    private PriorityQueue<Nodo> nodos=new PriorityQueue<Nodo>(100,new ComparadorBusquedaDeCostoUniforme());

    public BusquedaDeCostoUniforme(Problema problema){
        super(problema);
    }

    public Vector<Operador> aplicarAlgoritmo(){
        Nodo nodo;
        nodos.add(hacerNodoRaiz(problema.getEstadoInicial()));
        while(true){            
             cantidadDeNodosExpandidos++;
             if(nodos.isEmpty()){
                 System.out.println("la lista esta Vacia");
                 return rutaSolucion=null;
             }
             nodo=nodos.poll();
             if(problema.pruebaMeta(nodo.getEstado())){
                 System.out.println("He encontrado una solucion"); 
                 construirSolucion(nodo);
                 profundidadDelArbol=nodo.getProfundidad();
                 imprimirVectorSolucion();
                 nodo.imprimirDatosNodo();
                 return rutaSolucion;
             }
             Vector<Nodo> nodosHijos=expandirNodo(nodo);
             for(int i=0;i<nodosHijos.size();i++)
                   nodos.add(nodosHijos.elementAt(i)); 
        }
    }
}






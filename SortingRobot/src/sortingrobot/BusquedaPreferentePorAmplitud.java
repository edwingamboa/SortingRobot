package sortingrobot;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

public class BusquedaPreferentePorAmplitud extends Algoritmo{
    private Queue<Nodo> nodos = new LinkedBlockingQueue<Nodo>();

    public BusquedaPreferentePorAmplitud(Problema _problema){
        super(_problema);        
    }
    
    public Vector<Operador> aplicarAlgoritmo(){
        Nodo nodo;
        nodos.add(hacerNodoRaiz(problema.getEstadoInicial()));
        while(true)
        {       
           cantidadDeNodosExpandidos++;
             if(nodos.isEmpty())
             {
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
             Vector<Nodo> nodosHijos = expandirNodo(nodo);
             for(int i=0;i<nodosHijos.size();i++){
                 nodos.add(nodosHijos.elementAt(i));
             }             
        }        
    } 
}

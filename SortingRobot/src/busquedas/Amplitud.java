/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Búsqueda Preferente por Amplitud
* Clase encargada del algoritmo de búsqueda preferente por amplitud.
******************************************************************************/

package busquedas;

import sortingrobot.Problema;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import sortingrobot.Nodo;
import sortingrobot.Nodo;
import sortingrobot.Operador;
import sortingrobot.Operador;
import sortingrobot.Problema;

public class Amplitud extends Algoritmo{
    private Queue<Nodo> nodos = new LinkedBlockingQueue<Nodo>();

    public Amplitud(Problema _problema){
        super(_problema);        
    }
    
    public Vector<Nodo> aplicarAlgoritmo(){
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

/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Búsqueda Avara
* Clase encargada del algoritmo de búsqueda Avara.
******************************************************************************/

package busquedas;

import busquedas.comparadores.ComparadorAvara;
import java.util.PriorityQueue;
import java.util.Vector;
import sortingrobot.Nodo;
import sortingrobot.Problema;

public class Avara extends Algoritmo{
    private PriorityQueue<Nodo> nodos=new PriorityQueue<Nodo>(100,new ComparadorAvara());

    public Avara(Problema problema){
        super(problema, "Avaro");
    }

    public Vector<Nodo> aplicarAlgoritmo(){
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
                 return rutaSolucion;
             }
             Vector<Nodo> nodosHijos=expandirNodo(nodo);
             for(int i=0;i<nodosHijos.size();i++)
                 nodos.add(nodosHijos.elementAt(i));
        }

    }    
}







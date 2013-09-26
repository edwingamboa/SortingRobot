/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Búsqueda Preferente por Profunidad
* Clase encargada del algoritmo de búsqueda preferente por profundidad.
******************************************************************************/

package busquedas;

import java.util.Stack;
import java.util.Vector;
import sortingrobot.Nodo;
import sortingrobot.Operador;
import sortingrobot.Problema;
 
public class Profundidad extends Algoritmo{
    private Stack<Nodo> nodos=new Stack<Nodo>();

    public Profundidad(Problema problema){
        super(problema);
    }

    public Vector<Nodo> aplicarAlgoritmo(){
        Nodo nodo;
        nodos.push(hacerNodoRaiz(problema.getEstadoInicial()));
        while(true)
        {
            cantidadDeNodosExpandidos++;
             if(nodos.isEmpty())
             {
                 System.out.println("la lista esta vacia");
                 return rutaSolucion=null;
             }
             nodo=nodos.pop();             
             if(problema.pruebaMeta(nodo.getEstado()))
             {
                 System.out.println("He encontrado una solucion");
                 construirSolucion(nodo);
                 profundidadDelArbol=nodo.getProfundidad();
                 imprimirVectorSolucion();
                 nodo.imprimirDatosNodo();
                 return rutaSolucion;
             }
             Vector<Nodo> nodosHijos=expandirNodo(nodo);
             for(int i=0;i<nodosHijos.size();i++)
                 nodos.push(nodosHijos.elementAt(i));
        }

    }  
} 
package sortingrobot;

import java.util.Stack;
import java.util.Vector;
 
public class BusquedaPreferentePorProfundidad extends Algoritmo{
    private Stack<Nodo> nodos=new Stack<Nodo>();

    public BusquedaPreferentePorProfundidad(Problema problema){
        super(problema);
    }

    public Vector<Operador> aplicarAlgoritmo(){
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
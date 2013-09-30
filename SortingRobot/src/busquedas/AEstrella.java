<<<<<<< HEAD
/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Búsqueda A Estrella
* Clase encargada del algoritmo de búsqueda A*.
******************************************************************************/



package busquedas;


import busquedas.comparadores.ComparadorAEstrella;
import java.util.PriorityQueue;
import java.util.Vector;
import sortingrobot.*;

public class AEstrella extends Algoritmo{
    private PriorityQueue<Nodo> nodos=new PriorityQueue<Nodo>(100,new ComparadorAEstrella());

    public AEstrella(Problema problema){
        super(problema);
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
                 nodos.add(nodosHijos.elementAt(i));
        }

    }    
}






=======
/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Búsqueda A Estrella
* Clase encargada del algoritmo de búsqueda A*.
******************************************************************************/



package busquedas;


import busquedas.comparadores.ComparadorAEstrella;
import java.util.PriorityQueue;
import java.util.Vector;
import sortingrobot.*;

public class AEstrella extends Algoritmo{
    private PriorityQueue<Nodo> nodos=new PriorityQueue<Nodo>(100,new ComparadorAEstrella());

    public AEstrella(Problema problema){
        super(problema);
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
                 nodos.add(nodosHijos.elementAt(i));
        }

    }    
}






>>>>>>> 1fe6564f601de30443eed57566ed9912182d009b

package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: BusquedaAEstrella.java                   *
 * **************************************************/

import java.util.PriorityQueue;
import java.util.Vector;

public class BusquedaAEstrella
{
    private int cantidadDeNodosExpandidos=0;
    private int profundidadDelArbol=0;    
    private PriorityQueue<Nodo> nodos=new PriorityQueue<Nodo>(100,new ComparadorBusquedaAEstrella());
    private Problema problema;
    private Vector<Operador> rutaSolucion=new Vector<Operador>();
    private int costoDustCargado=0, costoTotal=0, costoNodoPadre=0;

    public BusquedaAEstrella(Problema problema)
    {
        this.problema=problema;
    }

    public Vector<Operador> aplicarAlgoritmo()
    {
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
                 return rutaSolucion;
             }
             Vector<Nodo> nodosHijos=expandirNodo(nodo);             
             for(int i=0;i<nodosHijos.size();i++)
                 nodos.add(nodosHijos.elementAt(i));
        }

    }

    public Nodo hacerNodoRaiz(Estado estado)
    {
        Nodo retorno=new Nodo(estado,null,null,0,0);
        return retorno;
    }

    public Vector<Nodo> expandirNodo(Nodo nodo)
    {
       Vector<ParOperadorEstado> hijos=problema.funcionSucesor(nodo.getEstado());
       Vector<Nodo> nodosHijos=new Vector();
       for(int i=0;i<hijos.size();i++)
       {
           ParOperadorEstado hijo=hijos.elementAt(i);
           Nodo nodoHijo;

           int costoDustVacio=1;
           if(this.costoDustCargado!=0)
           {
               costoTotal=nodo.getCostoDeRuta()+costoDustCargado;
               costoNodoPadre=costoTotal-nodo.getCostoDeRuta();
           }
           else
           {
               costoTotal=nodo.getCostoDeRuta()+costoDustVacio;
               costoNodoPadre=1;
           }
           nodoHijo=new Nodo(hijo.getEstado(),nodo,hijo.getOperador(),(nodo.getProfundidad()+1),costoTotal);
           if((nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('2')==null)&&(nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('3')!=null))
               this.costoDustCargado=2;
           if((nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('2')!=null)&&(nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('3')==null))
               this.costoDustCargado=3;
           if((nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('2')==null)&&(nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('3')==null))
               this.costoDustCargado=5;
           if((estaEnCamino(nodo,nodoHijo)==false)&&((costoTotal-nodo.getCostoDeRuta()==costoNodoPadre)))
           {   
               nodosHijos.add(nodoHijo);
           }

       }
       return nodosHijos;
    }

    public void construirSolucion(Nodo nodo) 
    {
        if(!nodo.esNodoRaiz())
        {
            rutaSolucion.add(nodo.getOperador());
            construirSolucion(nodo.getNodoPadre());
        }

    }

    public boolean estaEnCamino(Nodo nodoDeCamino, Nodo nodoHijo)
    {
        if(nodoDeCamino.esNodoRaiz())
                return nodoDeCamino.equals(nodoHijo);
        else
        {
            if(nodoDeCamino.equals(nodoHijo))
                return true;
            else
            {
                return estaEnCamino(nodoDeCamino.getNodoPadre(), nodoHijo);
            }
        }
    }

    public void imprimirVectorSolucion()
    {
        System.out.println("Esta es la ruta solucion");
        for(int i=(rutaSolucion.size()-1);i>=0;i--)
            rutaSolucion.elementAt(i).imprimirOperador();
    }

    public int getcantidadDeNodosExpandidos()
    {
        return cantidadDeNodosExpandidos;
    }

    public int getProfundidadDelArbol()
    {
        return profundidadDelArbol;
    }
}







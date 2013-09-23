package sortingrobot;

import java.util.PriorityQueue;
import java.util.Vector;

public class BusquedaAEstrella extends Algoritmo{
    private PriorityQueue<Nodo> nodos=new PriorityQueue<Nodo>(100,new ComparadorBusquedaAEstrella());

    public BusquedaAEstrella(Problema problema){
        super(problema);
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

    public Nodo hacerNodoRaiz(Estado estado){
        Nodo retorno=new Nodo(estado,null,null,0,0);
        return retorno;
    }

    public Vector<Nodo> expandirNodo(Nodo nodo){
       Vector<ParOperadorEstado> hijos=problema.funcionSucesor(nodo.getEstado());
       Vector<Nodo> nodosHijos=new Vector();
       for(int i=0;i<hijos.size();i++)
       {
           ParOperadorEstado hijo=hijos.elementAt(i);
           Nodo nodoHijo;

           int costoMovimiento=hijo.getEstado().getCostoMovimiento();
           costoTotal=nodo.getCostoDeRuta()+costoMovimiento;
           costoNodoPadre=costoTotal-nodo.getCostoDeRuta();
           nodoHijo=new Nodo(hijo.getEstado(),nodo,hijo.getOperador(),(nodo.getProfundidad()+1),costoTotal);
           
           if((estaEnCamino(nodo,nodoHijo)==false)
                   &&((costoTotal-nodo.getCostoDeRuta()==costoNodoPadre))){   
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







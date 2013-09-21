package sortingrobot;

import java.util.Stack;
import java.util.Vector;
 
public class BusquedaPreferentePorProfundidad
{
    private int cantidadDeNodosExpandidos=0;
    private int profundidadDelArbol=0;
    private Stack<Nodo> nodos=new Stack<Nodo>();
    private Problema problema;
    private Vector<Operador> rutaSolucion=new Vector<Operador>();
    private int costoRobotCargado=0, costoTotal=0, costoNodoPadre=0;

    public BusquedaPreferentePorProfundidad(Problema problema)
    {
        this.problema=problema;
    }

    public Vector<Operador> aplicarAlgoritmo()
    {
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
                 return rutaSolucion;
             }
             Vector<Nodo> nodosHijos=expandirNodo(nodo);
             for(int i=0;i<nodosHijos.size();i++)
                 nodos.push(nodosHijos.elementAt(i));
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
          
           //--Edwin este se debe revisar porque en nuestro caso no todos los nodos valen 1
           int costoRobotVacio=1;
           if(this.costoRobotCargado!=0)
           {
               costoTotal=nodo.getCostoDeRuta()+costoRobotCargado;
               costoNodoPadre=costoTotal-nodo.getCostoDeRuta();
           }
           else
           {
               costoTotal=nodo.getCostoDeRuta()+costoRobotVacio;
               costoNodoPadre=1;
           }
           nodoHijo=new Nodo(hijo.getEstado(),nodo,hijo.getOperador(),(nodo.getProfundidad()+1),costoTotal);
           if((nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('2')==null)&&(nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('3')!=null))
               this.costoRobotCargado=2;
           if((nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('2')!=null)&&(nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('3')==null))
               this.costoRobotCargado=3;
           if((nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('2')==null)&&(nodoHijo.getEstado().getMatriz().retornarCoordenadaDeObjetos('3')==null))
               this.costoRobotCargado=5;
           //Evitar ciclos
           if((estaEnCamino(nodo,nodoHijo)==false)&&((costoTotal-nodo.getCostoDeRuta()==costoNodoPadre)))
           {   
               nodosHijos.add(nodoHijo);
           }
       }
       return nodosHijos;   
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

    
    public void construirSolucion(Nodo nodo)
    {
        if(!nodo.esNodoRaiz())
        {
            rutaSolucion.add(nodo.getOperador());
            construirSolucion(nodo.getNodoPadre());
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




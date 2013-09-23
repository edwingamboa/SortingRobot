/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingrobot;

import java.util.Vector;

/**
 *
 * @author edwin
 */
public class Algoritmo implements IdsObjetos{
    int cantidadDeNodosExpandidos=0;
    int profundidadDelArbol=0;
    Vector<Operador> rutaSolucion=new Vector<Operador>();
    Problema problema;
    int pesoObjetoUno, pesoObjetoDos;
    int costoRobotCargado=0, costoTotal=0, costoNodoPadre=0;
   
    public Algoritmo(Problema _problema){
        this.problema = _problema;
        this.pesoObjetoUno = problema.getEstadoInicial().getMatriz().getPesoObjetoUno();
        this.pesoObjetoDos = problema.getEstadoInicial().getMatriz().getPesoObjetoDos();        
    }
    
    public Nodo hacerNodoRaiz(Estado estado){
        Nodo retorno=new Nodo(estado,null,null,0,0);
        return retorno;
    }
    
    public Vector<Nodo> expandirNodo(Nodo nodo){
       Vector<ParOperadorEstado> hijos=problema.funcionSucesor(nodo.getEstado());       
       Vector<Nodo> nodosHijos=new Vector();
       for(int i=0;i<hijos.size();i++){
           ParOperadorEstado hijo=hijos.elementAt(i);
           Nodo nodoHijo;
           
           int costoMovimiento=hijo.getEstado().getCostoMovimiento();
           costoTotal=nodo.getCostoDeRuta()+costoMovimiento;
           costoNodoPadre=costoTotal-nodo.getCostoDeRuta();
               
           nodoHijo=new Nodo(hijo.getEstado(),nodo,hijo.getOperador(),(nodo.getProfundidad()+1),costoTotal);
           
           //Evitar ciclos
           if((estaEnCamino(nodo,nodoHijo)==false)
                   &&((costoTotal-nodo.getCostoDeRuta()==costoNodoPadre))){   
               nodosHijos.add(nodoHijo);
           }
       }
       return nodosHijos;   
    }  
    
    public boolean estaEnCamino(Nodo nodoDeCamino, Nodo nodoHijo){
        if(nodoDeCamino.esNodoRaiz())
                return nodoDeCamino.equals(nodoHijo);
        else{
            if(nodoDeCamino.equals(nodoHijo))
                return true; 
            else{
                return estaEnCamino(nodoDeCamino.getNodoPadre(), nodoHijo);
            }
        }
    }
    
    public void construirSolucion(Nodo nodo){
        if(!nodo.esNodoRaiz()){            
            rutaSolucion.add(nodo.getOperador());
            construirSolucion(nodo.getNodoPadre());
            /*nodo.imprimirNodo();
            System.out.println("____________________");*/
        }

    }

    public void imprimirVectorSolucion(){
        System.out.println("Esta es la ruta solucion");
        int tamVectorSolucion = rutaSolucion.size();
        for(int i=(tamVectorSolucion-1);i>=0;i--)
            rutaSolucion.elementAt(i).imprimirOperador();
    }

    public int getcantidadDeNodosExpandidos(){
        return cantidadDeNodosExpandidos;
    }

    public int getProfundidadDelArbol(){       
        return profundidadDelArbol;
    }
    
}

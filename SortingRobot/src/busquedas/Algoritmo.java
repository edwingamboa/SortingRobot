/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Algoritmo
******************************************************************************/


package busquedas;

import sortingrobot.*;
import java.util.Vector;

public class Algoritmo implements IdObjetos {

    int cantidadDeNodosExpandidos = 0;
    int profundidadDelArbol = 0;
    Vector<Nodo> rutaSolucion = new Vector<Nodo>();
    Problema problema;
    int pesoObjetoUno, pesoObjetoDos;
    int costoRobotCargado = 0, costoTotal = 0, costoNodoPadre = 0;
    String nombre;

    public Algoritmo(Problema _problema, String _nombre) {
        this.problema = _problema;
        this.pesoObjetoUno = problema.getEstadoInicial().getMatriz().getPesoObjetoUno();
        this.pesoObjetoDos = problema.getEstadoInicial().getMatriz().getPesoObjetoDos();
        this.nombre = _nombre;
    }
    //Se define en cada Algoritmo
    public Vector<Nodo> aplicarAlgoritmo(){
        return null;
    }

    public Nodo hacerNodoRaiz(Estado estado) {
        Nodo retorno = new Nodo(estado, null, null, 0, 0);
        return retorno;
    }

    public Vector<Nodo> expandirNodo(Nodo nodo) {
        Vector<OperadorEstado> hijos = problema.funcionSucesor(nodo.getEstado());
        Vector<Nodo> nodosHijos = new Vector();
        for (int i = 0; i < hijos.size(); i++) {
            OperadorEstado hijo = hijos.elementAt(i);
            Nodo nodoHijo;

            int costoMovimiento = hijo.getEstado().getCostoMovimiento();
            costoTotal = nodo.getCostoDeRuta() + costoMovimiento;
            costoNodoPadre = costoTotal - nodo.getCostoDeRuta();

            nodoHijo = new Nodo(hijo.getEstado(), nodo, hijo.getOperador(), (nodo.getProfundidad() + 1), costoTotal);

            //Evitar ciclos
            if ((estaEnCamino(nodo, nodoHijo) == false)
                    && ((costoTotal - nodo.getCostoDeRuta() == costoNodoPadre))) {
                nodosHijos.add(nodoHijo);
            }
        }
        return nodosHijos;
    }

    public boolean estaEnCamino(Nodo nodoDeCamino, Nodo nodoHijo) {
        if (nodoDeCamino.esNodoRaiz()) {
            return nodoDeCamino.equals(nodoHijo);
        } else {
            if (nodoDeCamino.equals(nodoHijo)) {
                return true;
            } else {
                return estaEnCamino(nodoDeCamino.getNodoPadre(), nodoHijo);
            }
        }
    }

    public void construirSolucion(Nodo nodo) {
        if (!nodo.esNodoRaiz()) {
            rutaSolucion.add(nodo);
            construirSolucion(nodo.getNodoPadre());
            /*nodo.imprimirNodo();
             System.out.println("____________________");*/
        }

    }

    public void imprimirVectorSolucion() {
        System.out.println("Esta es la ruta solucion");
        int tamVectorSolucion = rutaSolucion.size();
        for (int i = (tamVectorSolucion - 1); i >= 0; i--) {
            rutaSolucion.elementAt(i).getOperador().imprimirOperador();
        }
        System.out.println("El costo de la Solución es: "
                + rutaSolucion.elementAt(0).getCostoDeRuta());
    }

    public int getcantidadDeNodosExpandidos() {
        return cantidadDeNodosExpandidos;
    }

    public int getProfundidadDelArbol() {
        return profundidadDelArbol;
    }

    public String getNombre() {
        return nombre;
    }
}

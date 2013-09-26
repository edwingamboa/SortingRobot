/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Comparador Busqueda Avara
* Clase encargada de las comparaciones realizadas en el algoritmo de búsqueda
* avara.
******************************************************************************/

package busquedas.comparadores;

import java.util.Comparator;
import sortingrobot.Nodo;
import sortingrobot.Nodo;

public class ComparadorAvara implements Comparator<Nodo>
{
    public int compare(Nodo nodo1, Nodo nodo2)
    {
        Heuristica heuristica = new Heuristica();        
        return  (((Integer) (heuristica.calcularH(nodo1))).compareTo(heuristica.calcularH(nodo2)));
    }
}


package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: ComparadorBusquedaAvara.java             *
 * **************************************************/

import java.util.Comparator;

public class ComparadorBusquedaAvara implements Comparator<Nodo>
{
    public int compare(Nodo nodo1, Nodo nodo2)
    {
        Heuristica heuristica = new Heuristica();        
        return  (((Integer) (heuristica.calcularH(nodo1))).compareTo(heuristica.calcularH(nodo2)));
    }
}


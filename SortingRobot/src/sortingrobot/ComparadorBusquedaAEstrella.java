package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: ComparadorBusquedaAEstrella.java         *
 * **************************************************/

import java.util.Comparator;

public class ComparadorBusquedaAEstrella implements Comparator<Nodo>
{
    public int compare(Nodo nodo1, Nodo nodo2)
    {
        Heuristica heuristica=new Heuristica();

        int valor1=heuristica.calcularH(nodo1)+nodo1.getCostoDeRuta();
        int valor2=heuristica.calcularH(nodo2)+nodo2.getCostoDeRuta();

        return  (((Integer) valor1).compareTo(valor2));
    }
} 
package sortingrobot;

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
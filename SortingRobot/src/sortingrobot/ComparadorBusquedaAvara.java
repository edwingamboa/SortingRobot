package sortingrobot;

import java.util.Comparator;

public class ComparadorBusquedaAvara implements Comparator<Nodo>
{
    public int compare(Nodo nodo1, Nodo nodo2)
    {
        Heuristica heuristica = new Heuristica();        
        return  (((Integer) (heuristica.calcularH(nodo1))).compareTo(heuristica.calcularH(nodo2)));
    }
}


/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Heuristica
* Clase en donde se organiza la heuristica a manejar en los algoritmos de 
* búsqueda Avara y A*.
******************************************************************************/

package busquedas.comparadores;

import sortingrobot.Estado;
import sortingrobot.Matriz;
import sortingrobot.Nodo;

public class Heuristica
{   
    public void Heuristica(){}

    public int calcularH(Nodo nodo)
    {
        Estado estado=nodo.getEstado();
        Matriz matriz=estado.getMatriz();
        int heuristica = 0, heuristicapr = 0, cargafaltante = 0;        
       
        int[] posicion4 = matriz.retornarCoordenadaDe('4');
        int[] posicion5 = matriz.retornarCoordenadaDe('5');        
        
        //Carga que el DusCart no ha recojido
        if((matriz.retornarCoordenadaDe('2')==null)&&(matriz.retornarCoordenadaDe('3')!=null))
            cargafaltante=3;
        if((matriz.retornarCoordenadaDe('2')!=null)&&(matriz.retornarCoordenadaDe('3')==null))
            cargafaltante=2;
        if((matriz.retornarCoordenadaDe('2')==null)&&(matriz.retornarCoordenadaDe('3')==null))
            cargafaltante=0;
        
        //Distancia de Manhattan desde el DustCart hasta el Punto de Reciclaje
        if(posicion5!=null)
        {    
            if((posicion4[0]<posicion5[0])&&(posicion4[1]<posicion5[1]))
                heuristicapr = (posicion5[0]-posicion4[0])+(posicion5[1]-posicion4[1]);
            if((posicion5[0]<posicion4[0])&&(posicion5[1]<posicion4[1]))
                heuristicapr = (posicion4[0]-posicion5[0])+(posicion4[1]-posicion5[1]);
            if((posicion5[0]<posicion4[0])&&(posicion4[1]<posicion5[1]))
                heuristicapr = (posicion4[0]-posicion5[0])+(posicion5[1]-posicion4[1]);
            if((posicion4[0]<posicion5[0])&&(posicion5[1]<posicion4[1]))
                heuristicapr = (posicion5[0]-posicion4[0])+(posicion4[1]-posicion5[1]);
            if((posicion4[0]==posicion5[0])&&(posicion4[1]<posicion5[1]))
                heuristicapr = (posicion5[1]-posicion4[1]);
            if((posicion4[0]==posicion5[0])&&(posicion5[1]<posicion4[1]))
                heuristicapr = (posicion4[1]-posicion5[1]);            
            if((posicion4[1]==posicion5[1])&&(posicion4[0]<posicion5[0]))
                heuristicapr = (posicion5[0]-posicion4[0]); 
            if((posicion4[1]==posicion5[1])&&(posicion5[0]<posicion4[0]))
                heuristicapr = (posicion4[0]-posicion5[0]); 
        }
        
        heuristica = heuristicapr + cargafaltante;
        return heuristica;
    }
}

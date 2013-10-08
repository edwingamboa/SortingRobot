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
import sortingrobot.IdObjetos;

public class Heuristica implements IdObjetos{   
    public void Heuristica(){}

    public int calcularH(Nodo nodo){
        Estado estado=nodo.getEstado();
        Matriz matriz=estado.getMatriz();
        int heuristicaUno = 0, heuristicaDos = 0, cargafaltante = 0;        
       
        int[] posicionRobot = matriz.retornarCoordenadaDe(ID_ROBOT);
        int[] posicionSitioUno = matriz.retornarCoordenadaDe(ID_SITIO_UNO);        
        int[] posicionSitioDos = matriz.retornarCoordenadaDe(ID_SITIO_DOS);        
        int[] posicionObjetoUno = matriz.retornarCoordenadaDe(ID_OBJETO_UNO);        
        int[] posicionObjetoDos = matriz.retornarCoordenadaDe(ID_OBJETO_DOS); 
                
        //Distancia de Manhattan desde el SortingRobot hasta el Sitio Uno
        if(posicionSitioUno!=null){
            if(posicionObjetoUno != null){
                heuristicaUno += heuristicaDePosUnoAPosDos(posicionRobot, posicionObjetoUno);
                heuristicaUno += heuristicaDePosUnoAPosDos(posicionObjetoUno, posicionSitioUno) 
                    + matriz.getPesoObjetoUno();
            }
            else
                heuristicaUno += heuristicaDePosUnoAPosDos(posicionRobot, posicionSitioUno) 
                    + matriz.getPesoObjetoUno();                
            //Distancia de Manhattan desde el Sitio Uno hasta el Sitio Dos
            if(posicionSitioDos!=null)
                heuristicaUno += heuristicaDePosUnoAPosDos(posicionSitioUno, posicionSitioDos) 
                        + matriz.getPesoObjetoDos();            
        }
        //Distancia de Manhattan desde el SortingRobot hasta el Sitio Dos
        if(posicionSitioDos!=null){
            if(posicionObjetoDos != null){
                heuristicaDos += heuristicaDePosUnoAPosDos(posicionRobot, posicionObjetoDos); 
                heuristicaDos += heuristicaDePosUnoAPosDos(posicionObjetoDos, posicionSitioDos) 
                        + matriz.getPesoObjetoDos();
            }
            else
                heuristicaDos += heuristicaDePosUnoAPosDos(posicionRobot, posicionSitioDos) + matriz.getPesoObjetoDos();
            //Distancia de Manhattan desde el Sitio Dos hasta el Sitio Uno
            if(posicionSitioUno!=null)
                heuristicaDos += heuristicaDePosUnoAPosDos(posicionSitioDos, posicionSitioUno) 
                        + matriz.getPesoObjetoUno();
        }
        if((heuristicaUno < heuristicaDos && heuristicaUno > 0) || heuristicaDos <= 0){
            return heuristicaUno;
        }
        else{
            return heuristicaDos;
        }
    }
    
    
    public int heuristicaDePosUnoAPosDos(int[] posUno, int[]posDos){
        if((posUno[0]<posDos[0])&&(posUno[1]<posDos[1]))
            return (posDos[0]-posUno[0])+(posDos[1]-posUno[1]);
        else if((posDos[0]<posUno[0])&&(posDos[1]<posUno[1]))
            return (posUno[0]-posDos[0])+(posUno[1]-posDos[1]);
        else if((posDos[0]<posUno[0])&&(posUno[1]<posDos[1]))
            return (posUno[0]-posDos[0])+(posDos[1]-posUno[1]);
        else if((posUno[0]<posDos[0])&&(posDos[1]<posUno[1]))
            return (posDos[0]-posUno[0])+(posUno[1]-posDos[1]);
        else if((posUno[0]==posDos[0])&&(posUno[1]<posDos[1]))
            return (posDos[1]-posUno[1]);
        else if((posUno[0]==posDos[0])&&(posDos[1]<posUno[1]))
            return (posUno[1]-posDos[1]);            
        else if((posUno[1]==posDos[1])&&(posUno[0]<posDos[0]))
            return (posDos[0]-posUno[0]); 
        else if((posUno[1]==posDos[1])&&(posDos[0]<posUno[0]))
            return (posUno[0]-posDos[0]); 
        return 0;
    }
}
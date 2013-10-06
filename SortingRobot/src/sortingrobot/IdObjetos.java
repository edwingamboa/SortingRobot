/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: ID Objetos
* Clase encargada del almacenamiento de las IDs basicas.
******************************************************************************/
package sortingrobot;

public interface IdObjetos {
    final int ID_VACIA=0;
    final int ID_ROBOT=-1;
    final int ID_OBJETO_UNO=-2;
    final int ID_OBJETO_DOS=-3;
    final int ID_SITIO_UNO=-4;
    final int ID_SITIO_DOS=-5;
    final int ID_SITIO_UNO_CON_ROBOT=-6;
    final int ID_SITIO_DOS_CON_ROBOT=-7; 
    final int ID_CELDA_INVALIDA= Integer.MIN_VALUE;
    final int VALOR_POR_DEFECTO_CASILLAS = 1;
}

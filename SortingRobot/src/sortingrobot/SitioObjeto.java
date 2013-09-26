/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Sitio del Objeto
******************************************************************************/

package sortingrobot;

public class SitioObjeto 
{
    private boolean ocupado; 
    private int objetoAsociado;
    private int fila, columna;
    
    public SitioObjeto(){}    
    
    public void ubicarObjeto()
    {
        this.ocupado=true;        
    } 
       
    public void setCoordenadas(int fila, int columna)
    {
        this.fila = fila;
        this.columna = columna;
    }        
    
    public int getFila()
    {
        return this.fila;
    }        
    
    public int getColumna()
    {
        return this.columna;
    }  

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getObjetoAsociado() {
        return objetoAsociado;
    }

    /**
     * Esteblece el código del objeto asociado a este stio.
     * @param objetoAsociado código que identifica al objeto asociado.
     */
    public void setObjetoAsociado(int objetoAsociado) {
        this.objetoAsociado = objetoAsociado;
    }
    
    
}

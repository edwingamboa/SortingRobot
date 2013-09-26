/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Operador
******************************************************************************/

package sortingrobot;

public class Operador
{    
    private char direccion;
    
    public Operador()
    {}   

    public Operador(char direccion)
    {        
        this.direccion=direccion;        
    }

    public char getDireccion()
    {
        return this.direccion;
    }

    public void setDireccion(char nuevaDireccion)
    {
        this.direccion=nuevaDireccion;
    }

    public void imprimirOperador()
    {
        String movimiento = null;
        if(direccion=='u')            
            movimiento = "Arriba";
        if(direccion=='d')            
            movimiento = "Abajo";
        if(direccion=='l')            
            movimiento = "Izquierda";
        if(direccion=='r')            
            movimiento = "Derecha";
        System.out.println(" Direccion: "+movimiento);
    }
    
    public String toStringOperador()
    {
        String movimiento = null;
        if(direccion=='u')            
            movimiento = "Arriba";
        if(direccion=='d')            
            movimiento = "Abajo";
        if(direccion=='l')            
            movimiento = "Izquierda";
        if(direccion=='r')            
            movimiento = "Derecha";
        String operador=" Direccion: "+movimiento;
        return operador;
    }   
}



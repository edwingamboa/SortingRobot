package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: Operador.java                            *  
 * **************************************************/

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



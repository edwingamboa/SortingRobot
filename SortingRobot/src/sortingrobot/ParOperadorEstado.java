package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: ParOperadorEstado.java                   *  
 * **************************************************/

//--Edwin-- Esta clase creo que no se debe modificar
public class ParOperadorEstado
{
    private Operador operador;
    private Estado estado;

    public ParOperadorEstado(){} 

    public ParOperadorEstado(Operador operador,Estado estado)
    {
        this.operador=operador;
        this.estado=estado;
    }

    public Operador getOperador()
    {
        return this.operador;
    }

    public void setOperador(Operador nuevoOperador)
    {
        this.operador=nuevoOperador;
    }

    public Estado getEstado()
    {
        return this.estado;
    }

    public void setEstado(Estado nuevoEstado)
    {
        this.estado=nuevoEstado;
    }
    
    public void imprimirpareja()
    {
        operador.imprimirOperador();
        estado.imprimirEstado();
        System.out.println("\n");
    } 
}

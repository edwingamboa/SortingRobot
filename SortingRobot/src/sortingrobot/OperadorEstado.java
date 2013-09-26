/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Operador Estado
******************************************************************************/

package sortingrobot;

public class OperadorEstado
{
    private Operador operador;
    private Estado estado;

    public OperadorEstado(){} 

    public OperadorEstado(Operador operador,Estado estado)
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

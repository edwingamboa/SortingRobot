package sortingrobot;

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

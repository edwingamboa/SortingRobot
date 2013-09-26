/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Nodo
******************************************************************************/

package sortingrobot;

public class Nodo 
{
    private Estado estado;
    private Nodo nodoPadre;
    private Operador operador;
    private int profundidad;
    private int costoDeRuta; 
    public int costoMov, costoRobCargado;

    public Nodo(){}

    public Nodo(Estado estado, Nodo nodoPadre,Operador operador,int profundidad, int costoDeRuta){
        this.estado=estado;
        this.nodoPadre=nodoPadre;
        this.operador=operador;
        this.profundidad=profundidad;
        this.costoDeRuta=costoDeRuta;
    }
    
    public Estado getEstado(){
        return this.estado;
    }

    public void setEstado(Estado nuevoEstado){
        this.estado=nuevoEstado;
    }


    public Nodo getNodoPadre(){
        return this.nodoPadre;
    }

    public void setNodoPadre(Nodo nuevoPadre){
        this.nodoPadre=nuevoPadre;
    }


    public Operador getOperador(){
        return this.operador;
    }

    public void setOperador(Operador nuevoOperador){
        this.operador=nuevoOperador;
    }


    public int getProfundidad(){
        return this.profundidad;
    }

    public void setProfundidad(int nuevaProfundidad){
        this.profundidad=nuevaProfundidad;
    }


    public int getCostoDeRuta(){
        return this.costoDeRuta;
    }

    public void setCostoDeRuta(int nuevoCosto){
        this.costoDeRuta=nuevoCosto;
    }

    public void imprimirNodo(){
        if(operador!=null)
            operador.imprimirOperador();
        estado.imprimirEstado();        
        System.out.println("profundidad: "+profundidad+" costoDeRuta: "+costoDeRuta);
    } 
    
    public void imprimirDatosNodo(){
        System.out.println("profundidad: "+profundidad+" costoDeRuta: "+costoDeRuta);
    }

    public boolean equals(Nodo nodo)
    {
        return estado.equals(nodo.getEstado());
    }  

    public boolean esNodoRaiz()
    {
        return nodoPadre==null;
    }
}

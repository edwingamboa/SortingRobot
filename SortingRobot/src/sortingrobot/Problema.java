/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Problema
* Clase encargada del analisis del problema a resolver, con sus sitios, pesos, 
* etc.
******************************************************************************/

package sortingrobot;

import java.util.Arrays;
import java.util.Vector;

public class Problema implements IdObjetos{ 
    private Estado estadoInicial; //Archivo ingresado
    private int carga, filaSitioUno, columnaSitioUno, filaSitioDos, columnaSitioDos,
            pesoObjetoUno, pesoObjetoDos;
    private int[][] matrizPenalizaciones;
    
    public Problema(Estado estado){
        this.estadoInicial=estado;
        pesoObjetoUno = estadoInicial.getMatriz().getPesoObjetoUno();
        pesoObjetoDos = estadoInicial.getMatriz().getPesoObjetoDos();
        filaSitioUno = estadoInicial.getMatriz().getSitioUno().getFila();
        columnaSitioUno = estadoInicial.getMatriz().getSitioUno().getColumna();
        filaSitioDos = estadoInicial.getMatriz().getSitioDos().getFila();
        columnaSitioDos = estadoInicial.getMatriz().getSitioDos().getColumna();        
        matrizPenalizaciones = estadoInicial.getMatriz().getMatrizPenalizaciones();
    }

    public Estado getEstadoInicial(){
        return this.estadoInicial;
    }

    public Vector<OperadorEstado> funcionSucesor(Estado nodoPadre){ 
        Vector<OperadorEstado> retorno=new Vector();
        Vector<Operador> todosLosMovimientosPosibles=generarOperadores();
        
        for(int i=0;i<todosLosMovimientosPosibles.size();i++){
            Matriz referenciada=nodoPadre.getMatriz();
            Matriz nueva=new Matriz();
            nueva.setSortingRobot(referenciada.getSortingRobot());
            nueva.setMatrizPenalizaciones(matrizPenalizaciones);
            nueva.setPesoObjetoUno(pesoObjetoUno);
            nueva.setPesoObjetoDos(pesoObjetoDos);
            
            int dimension=referenciada.getDimension();
            int[][] matrizNueva = new int[dimension][dimension];            
            for (int j=0;j<dimension;j++){
                matrizNueva[j] = Arrays.copyOf(referenciada.getMatriz()[j],dimension);
            }            
            nueva.setDimension(dimension);
            nueva.setMatriz(matrizNueva);
            nueva.setCoordenadasSitioUno(filaSitioUno, columnaSitioUno);
            nueva.setCoordenadasSitioDos(filaSitioDos, columnaSitioDos);
            Estado estadoPrueba=new Estado(nueva);

            if( estadoPrueba.verificarMovimientoValido(todosLosMovimientosPosibles.elementAt(i))){                
                Operador operador=todosLosMovimientosPosibles.elementAt(i);
                estadoPrueba.moverRobot(operador);
                estadoPrueba.getMatriz().verificarSitios();
                OperadorEstado pareja=new OperadorEstado(operador,estadoPrueba);
                retorno.add(pareja);
                estadoPrueba=null; 
                carga = nueva.getSortingRobot().getCarga();
            }
        }
        return retorno;
    }

    public boolean pruebaMeta(Estado estado){
        Matriz matriz=estado.getMatriz();
        int[] posObjetoUno = matriz.retornarCoordenadaDe(ID_OBJETO_UNO);
        int[] posObjetoDos = matriz.retornarCoordenadaDe(ID_OBJETO_DOS);
        int[] posRobot = matriz.retornarCoordenadaDe(ID_ROBOT);
        int[] posSitioUno = matriz.retornarCoordenadaDe(ID_SITIO_UNO);        
        int[] posSitioDos = matriz.retornarCoordenadaDe(ID_SITIO_DOS);        
        if(posObjetoUno==null && posObjetoDos==null && posSitioUno==null && posSitioDos==null
                &&((posRobot[0]==filaSitioUno && posRobot[1]==columnaSitioUno)
                || (posRobot[0]==filaSitioDos && posRobot[1]==columnaSitioDos) ))    
        {
            this.carga=0;
            return true;        
        }    
        return false;
    }

    public Vector<Operador> generarOperadores(){
        Vector<Operador> operaciones=new Vector();      
        char[] direcciones={'u','d','l','r'};         
        for(int j=0;j<direcciones.length;j++){
            Operador operador=new Operador(direcciones[j]);
            operaciones.add(operador);                
        }
        return operaciones;
    }
    
    public int getCarga(){
        return carga;
    }     
    
    /*Revisar
     public int getFila()
    {
        return filaSitioUno;
    }        
    
    public int getColumna()
    {
        return columnaSitioUno;
    }*/
}

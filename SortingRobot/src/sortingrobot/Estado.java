/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Estado
******************************************************************************/

package sortingrobot;

public class Estado{
    private Matriz matriz = new Matriz(); 
    private int costoMovimiento=0;

    public Estado(Matriz matriz){
        this.matriz=matriz;        
    }

    public int getCostoMovimiento() {
        return costoMovimiento;
    }

    public void setCostoMovimiento(int costoMovimiento) {
        this.costoMovimiento = costoMovimiento;
    }

    public Matriz getMatriz(){
        return this.matriz;
    }

    public boolean verificarMovimientoValido(Operador operador){  
        char direccion=operador.getDireccion();
        int[] coordenada=matriz.retornarCoordenadaDe(matriz.getSortingRobot().getId());
         if(coordenada!=null){
            //Izquierda                       
            if(direccion=='l'){       
               if(matriz.estaDentroDeMatriz(coordenada[0],coordenada[1]-1)==false){
                   return false;
               }      
                return true; 
            }
            //Derecha
            if(direccion=='r'){
                if(matriz.estaDentroDeMatriz(coordenada[0],coordenada[1]+1)==false){
                    return false;
                }
                return true;
            } 

           //Arriba
           if(direccion=='u'){
               if(matriz.estaDentroDeMatriz(coordenada[0]-1,coordenada[1])==false){
                   return false;
               }
               return true;
           }
           //Abajo
           if(direccion=='d'){
               if(matriz.estaDentroDeMatriz(coordenada[0]+1,coordenada[1])==false){
                   return false;
               }
               return true; 
           }
         }
       return false;
       
    }
    
    public boolean moverRobot(Operador operador){         
        char direccion=operador.getDireccion(); 
        int[] coordenada=matriz.retornarCoordenadaDe(matriz.getSortingRobot().getId());
         if(coordenada!=null){      
               costoMovimiento += matriz.moverFicha(coordenada[0],coordenada[1], direccion);
               return true;
         }
         return false; 
    }

    public boolean equals(Estado otroEstado){
        int[][] matrizObj1=matriz.getMatriz();
        int[][] matrizObj2=otroEstado.getMatriz().getMatriz();

        for(int i=0;i<matriz.getDimension();i++)
        {
            for(int j=0;j<matriz.getDimension();j++)
            {
                if(matrizObj1[i][j]!=matrizObj2[i][j])
                    return false;
            }
        }        
        return true; 
    }  

    public void imprimirEstado(){
        matriz.imprimirMatriz();
    } 
}

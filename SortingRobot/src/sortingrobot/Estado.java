package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: Estado.java                              *  
 * **************************************************/

public class Estado
{
    private Matriz matriz = new Matriz();  

    public Estado(Matriz matriz)
    {
        this.matriz=matriz;        
    }

    public Matriz getMatriz()
    {
        return this.matriz;
    }
    
    //--Edwin-- Se hace uso de la función verificarSiEstaLibre(es de la clase Matriz), esta debe ser revisada para que se adpate a lo nuestro
    public boolean verificarMovimientoValido(Operador operador)
    {  
        char direccion=operador.getDireccion();
        int[] coordenada=matriz.retornarCoordenadaDeObjetos(matriz.getDustCart().getNombre());
         if(coordenada!=null){
            //Izquierda                       
            if(direccion=='l'){       
               if(matriz.verificarSiEstaLibre(coordenada[0],coordenada[1]-1)==false)
                    return false;      
                return true; 
            }
            //Derecha
            if(direccion=='r'){
                if(matriz.verificarSiEstaLibre(coordenada[0],coordenada[1]+1)==false)
                    return false;
                return true;
            } 

           //Arriba
           if(direccion=='u'){
               if(matriz.verificarSiEstaLibre(coordenada[0]-1,coordenada[1])==false)
                   return false;
               return true;
           }
           //Abajo
           if(direccion=='d'){
               if(matriz.verificarSiEstaLibre(coordenada[0]+1,coordenada[1])==false)
                   return false;
               return true; 
           }
         }
       return false;
       
    }
    
    public boolean moverDustCart(Operador operador)
    {         
        char direccion=operador.getDireccion(); 
        int[] coordenada=matriz.retornarCoordenadaDeObjetos(matriz.getDustCart().getNombre());
         if(coordenada!=null){
            //Izquierda                       
            if(direccion=='l'){       
               matriz.moverFicha(coordenada[0],coordenada[1], direccion);
               return true;
            }
            //Derecha
            if(direccion=='r'){
                matriz.moverFicha(coordenada[0],coordenada[1], direccion);
                return true;
            } 

           //Arriba
           if(direccion=='u'){
               matriz.moverFicha(coordenada[0],coordenada[1], direccion);
               return true;
           }
           //Abajo
           if(direccion=='d'){
               matriz.moverFicha(coordenada[0],coordenada[1], direccion);
               return true;
           }
         }
         return false; 
    }

    public boolean equals(Estado otroEstado)
    {
        char[][] matrizObj1=matriz.getMatriz();
        char[][] matrizObj2=otroEstado.getMatriz().getMatriz();

        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(matrizObj1[i][j]!=matrizObj2[i][j])
                    return false;
            }
        }        
        return true; 
    }  

    public void imprimirEstado()
    {
        matriz.imprimirMatriz();
    } 
}

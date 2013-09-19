package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: LectorDeArchivo.java                     *  
 * **************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;


public class LectorDeArchivo
{
    private File ambiente;  
    private Vector<String> vectorLineas=new Vector();


    public LectorDeArchivo(File archivo)
    {
        this.ambiente=archivo;           
    }
    
    public Vector<String> leerArchivo()
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader (ambiente);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null)
            {
                String fila="";
                for(int i=0;i<linea.length();i++)
                {
                    if(linea.charAt(i)!=' ')
                       fila+=linea.charAt(i);
                }
                
                vectorLineas.add(fila);              
            }
               
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
       }
       return vectorLineas;
   }
      

   


   
}

    



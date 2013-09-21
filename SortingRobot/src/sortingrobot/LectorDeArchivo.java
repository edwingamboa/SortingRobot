package sortingrobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;


public class LectorDeArchivo
{
    private File ambiente;  
    private Vector<int[]> vectorLineas=new Vector();


    public LectorDeArchivo(File archivo)
    {
        this.ambiente=archivo;           
    }
    
    public Vector<int[]> leerArchivo()
    {
        FileReader fr = null;
        BufferedReader br = null;
        try
        {
            fr = new FileReader (ambiente);
            br = new BufferedReader(fr);
            String linea;
            String filaStrings[];
            int filaInt[];
            while((linea=br.readLine())!=null)
            {
                filaStrings = linea.split(" ");
                filaInt = new int[filaStrings.length];
                for(int i=0; i<filaStrings.length; i++){
                    filaInt[i]= Integer.valueOf(filaStrings[i]);
                }
                vectorLineas.add(filaInt);              
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

    



package sortingrobot;


public class SortingRobot 
{
    private int carga;
    private char nombre;
    private int valorDeposito;
    
    public SortingRobot()
    {
        this.nombre='4';
    }
     
    public void setCarga(int nuevaCarga)
    {
        this.carga=nuevaCarga;
    }
    
    public int getCarga()
    {
        return this.carga;
    }
    
    public int getValorDeposito()
    {
        return this.valorDeposito;
    }        
    
    public char getNombre()
    {
        return this.nombre;        
    }
    
    public void montarCarga(int valorDeposito)
    {
        this.carga+=valorDeposito;
        this.valorDeposito = valorDeposito;
    } 
    
    public int desMontarontarCarga()
    {
        int cargaDesmontar=this.carga;
        this.carga=0;
        return cargaDesmontar;
    } 
}

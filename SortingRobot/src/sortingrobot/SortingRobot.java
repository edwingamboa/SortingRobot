package sortingrobot;


public class SortingRobot 
{
    private int carga;
    private int id;
    private int valorDeposito;
    
    public SortingRobot()
    {
        this.id=-1;
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
    
    public int getId()
    {
        return this.id;        
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

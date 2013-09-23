package sortingrobot;


public class SortingRobot implements IdsObjetos{
    private int carga=0;
    private int id;
    private int valorDeposito;
    
    public SortingRobot(){
        this.id=ID_ROBOT;
    }
     
    public void setCarga(int nuevaCarga){
        this.carga=nuevaCarga;
    }
    
    public int getCarga(){
        return this.carga;
    }
    
    public int getValorDeposito(){
        return this.valorDeposito;
    }        
    
    public int getId(){
        return this.id;        
    }
    
    public void montarCarga(int valorDeposito){
        this.carga+=valorDeposito;
        this.valorDeposito = valorDeposito;
    } 
    
    public void desMontarCarga(int _carga){
        this.carga-=_carga;
    } 
}

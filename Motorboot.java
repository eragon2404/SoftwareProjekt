import ea.*;
public class Motorboot extends SPIELER
{
    public Motorboot(MODEL newm,CONTROLLER newc)
    {
        super(newm,newc);
        textur = new Figur(PosX,PosY,"Recources/motorboot.eaf");
        breite = 15 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
    
}

        
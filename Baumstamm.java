import ea.*;
public class Baumstamm extends SPIELER
{
    public Baumstamm(MODEL newm,CONTROLLER newc)
    {
        super(newm,newc);
        textur = new Figur(PosX,PosY,"Recources/baumstamm.eaf");
        breite = 15 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

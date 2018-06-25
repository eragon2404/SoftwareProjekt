import ea.*;
public class Kanu extends SPIELER
{
    public Kanu(MODEL newm,CONTROLLER newc)
    {
        super(newm,newc);
        textur = new Figur(PosX,PosY,"Recources/kanu.eaf");
        breite = 15 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

        
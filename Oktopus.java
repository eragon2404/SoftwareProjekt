import ea.*;
public class Oktopus extends SPIELER
{
    public Oktopus(MODEL newm,CONTROLLER newc)
    {
        super(newm,newc);
        textur = new Figur(PosX,PosY,"Recources/oktopus.eaf");
        breite = 15 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

        
import ea.*;
public class Jetski extends SPIELER
{
    public Jetski(MODEL newm,CONTROLLER newc)
    {
        super(newm,newc);
        textur = new Figur(PosX,PosY,"Recources/kanu.eaf"); //WRONG
        breite = 15 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

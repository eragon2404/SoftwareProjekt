import ea.*;
public class Schildkroete extends SPIELER
{
    public Schildkroete(MODEL newm,CONTROLLER newc)
    {
        super(newm,newc);
        textur = new Figur(PosX,PosY,"Recources/kanu.eaf"); //WRONG
        breite = 15 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

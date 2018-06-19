import ea.*;
public class Kanu extends SPIELER
{
    public Kanu(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/kanu.eaf");
        breite = 20 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

        
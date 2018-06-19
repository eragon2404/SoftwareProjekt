import ea.*;
public class Baumstamm extends SPIELER
{
    public Baumstamm(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/baumstamm.eaf");
        breite = 20 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

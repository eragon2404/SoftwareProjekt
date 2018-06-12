import ea.*;
public class Baumstamm extends SPIELER
{
    public Baumstamm(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/baumstamm.eaf");
        textur.faktorSetzen(breite/20);
    }
}

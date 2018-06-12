import ea.*;
public class Kanu extends SPIELER
{
    public Kanu(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/kanu.eaf");
        textur.faktorSetzen(breite/25);
    }
}

        
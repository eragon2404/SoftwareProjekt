import ea.*;
public class Schildkroete extends SPIELER
{
    public Schildkroete(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/schildkroete.eaf");
        textur.faktorSetzen(breite/25);
    }
}

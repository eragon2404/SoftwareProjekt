import ea.*;
public class Schildkroete extends SPIELER
{
    public Schildkroete(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/schildkroete.eaf");
        breite = 20 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

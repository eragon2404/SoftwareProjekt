import ea.*;
public class Oktopus extends SPIELER
{
    public Oktopus(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/Oktopus.eaf");
        breite = 20 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

        
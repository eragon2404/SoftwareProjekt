import ea.*;
public class Schnorchler extends SPIELER
{
    public Schnorchler(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/schnorchler.eaf");
        breite = 20 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

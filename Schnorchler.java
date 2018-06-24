import ea.*;
public class Schnorchler extends SPIELER
{
    public Schnorchler(MODEL newm,CONTROLLER newc)
    {
        super(newm,newc);
        textur = new Figur(PosX,PosY,"Recources/schnorchler.eaf");
        breite = 20 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
}

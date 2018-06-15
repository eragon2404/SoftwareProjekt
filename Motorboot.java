import ea.*;
public class Motorboot extends SPIELER
{
    public Motorboot(MODEL newm)
    {
        super(newm);
        textur = new Figur(PosX,PosY,"Recources/motorboot.eaf");
        breite = 20 * calcFaktor();  
        textur.faktorSetzen(calcFaktor());
    }
    
}

        
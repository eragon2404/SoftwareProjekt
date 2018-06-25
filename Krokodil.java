import ea.*;
public class Krokodil extends HINDERNISS
{
    public Krokodil(int newPosX,int newBreite)
    {
        super(newPosX,newBreite);
        textur = new Figur(PosX,PosY,"Recources/Krokodil.eaf");
        textur.faktorSetzen(breite/20);
    }
}

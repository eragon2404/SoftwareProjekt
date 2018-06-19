import ea.*;
public class Treibholz extends HINDERNISS
{
    public Treibholz(int newPosX,int newBreite)
    {
        super(newPosX,newBreite);
        textur = new Figur(PosX,PosY,"Recources/Treibholz.eaf");
        //textur.faktorSetzen(breite);
    }
}

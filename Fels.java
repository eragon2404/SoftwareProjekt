import ea.*;
public class Fels extends HINDERNISS
{
    public Fels(int newPosX,int newBreite)
    {
        super(newPosX,newBreite);
        textur = new Figur(PosX,PosY,"Recources/Fels.eaf");
        //textur.faktorSetzen(breite);
    }
}

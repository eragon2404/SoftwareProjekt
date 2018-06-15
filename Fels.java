import ea.*;
public class Fels extends HINDERNISS
{
    public Fels()
    {
        textur = new Figur(PosX,PosY,"Recources/Fels.eaf");
        textur.faktorSetzen(breite/25);
    }
}

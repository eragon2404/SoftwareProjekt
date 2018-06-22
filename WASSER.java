import ea.*;
public class WASSER extends OBJECT
{
    public WASSER(int bahn, int newbreite)
    {
        breite = newbreite;
        PosX = (bahn*breite) - (float)(0.5*breite);
        PosY = -breite;
        textur = new Figur(PosX,PosY,"Recources/DasperfekteWasser.eaf");
        textur.faktorSetzen(breite/16);
        setChanged = true;
    }
    
    public void tick()
    {
        PosY +=10;
        setChanged = true;
    }
}

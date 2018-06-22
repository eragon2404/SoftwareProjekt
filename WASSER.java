import ea.*;
public class WASSER extends OBJECT
{
    public WASSER(int bahn, int newbreite)
    {
        breite = newbreite;
        PosX = (bahn*breite) - (float)(0.5*breite);
        PosY = -breite;
        textur = new Figur(PosX,PosY,"Recources/Treibholz.eaf");
        textur.faktorSetzen(breite/20);
        setChanged = true;
    }
    
    public void tick()
    {
        PosY +=10;
        setChanged = true;
    }
}

import ea.*;
public class GRAS extends OBJECT  //Grastextur
{
    public GRAS(int bahn, int newbreite)
    {
        breite = newbreite;
        PosX = (bahn*breite) - (float)(0.5*breite);
        PosY = -breite;
        textur = new Figur(PosX,PosY,"Recources/Gras1.eaf");
        textur.faktorSetzen(breite/16);
        setChanged = true;
    }
    
    public void tick()  //Bewegung
    {
        PosY += 10;
        setChanged = true;
    }
}
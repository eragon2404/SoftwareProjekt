import ea.*;
public abstract class HINDERNISS extends OBJECT
{
    public HINDERNISS(int newPosX,int newBreite)
    {
        PosX = newPosX;
        breite = newBreite/2;
        PosY = -(breite);
        setChanged = true;
    }
    
    public void tick(SPIELER spieler)
    {
        if(textur.schneidet(spieler.gettextur())==true)
        {
            spieler.collision();
        }
        PosY += 10;
        setChanged = true;
    }
    
}

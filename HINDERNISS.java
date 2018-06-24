import ea.*;
public abstract class HINDERNISS extends OBJECT
{
    public HINDERNISS(int newPosX,int newBreite)
    {
        PosX = newPosX;
        breite = (int)newBreite/40;
        PosY = -(breite*20);
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

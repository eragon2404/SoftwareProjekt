import ea.*;
public abstract class HINDERNISS extends OBJECT
{
    public HINDERNISS(int newPosX,int newBreite)
    {
        PosX = newPosX;
        breite = (int)newBreite/60;
        PosY = -(breite*20);
        setChanged = true;
    }
    
    public void tick(SPIELER spieler)
    {
        PosY += 10;
        setChanged = true;
    }
    
}

import ea.*;
public abstract class SPIELER extends OBJECT
{
    public int bahn;
    public MODEL m;
    public float goToX;
    
    public SPIELER(MODEL newm)
    {
        m = newm;
        bahn = (m.bahnen.length+1)/2;
        PosX = m.bahnen[bahn-1].getMitte();
        goToX = PosX;
        PosY = m.y - m.y/4;
        setChanged = true;
    }
    
    public void links()
    {
        if(bahn > 1)
        {
            bahn --;
            goToX = m.bahnen[bahn-1].getMitte();
        }
    }
    
    public void rechts()
    {
        if(bahn < m.bahnen.length)
        {
            bahn ++;
            goToX = m.bahnen[bahn-1].getMitte();
        }
    }
    
    public void tick()
    {
        if(PosX - goToX > 10)
        {
            PosX -= 20;
            setChanged = true;
        }
        else if(PosX - goToX < -10)
        {
            PosX += 20;
            setChanged = true;
        }
    }
    
    public int calcFaktor()
    {
        return (int)(m.getBreite()/60);
    }
    
    public int getbahn()
    {
        return bahn;
    }
    
}

    

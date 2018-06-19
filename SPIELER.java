import ea.*;
public abstract class SPIELER extends OBJECT
{
    public int bahn;
    public MODEL m;
    
    public SPIELER(MODEL newm)
    {
        m = newm;
        bahn = (m.bahnen.length+1)/2;
        PosX = m.bahnen[bahn-1].getMitte();
        PosY = m.y - m.y/4;
        setChanged = true;
    }
    
    public void links()
    {
        if(bahn > 1)
        {
            bahn --;
            PosX = m.bahnen[bahn-1].getMitte();
            setChanged = true;
        }
    }
    
    public void rechts()
    {
        if(bahn < m.bahnen.length)
        {
            bahn ++;
            PosX = m.bahnen[bahn-1].getMitte();
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

    

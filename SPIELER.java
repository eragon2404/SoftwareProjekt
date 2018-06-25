import ea.*;
public abstract class SPIELER extends OBJECT
{
    public int bahn;
    public MODEL m;
    public float goToX;
    public CONTROLLER c;
    
    public SPIELER(MODEL newm,CONTROLLER newc)
    {
        m = newm;
        bahn = (m.bahnen.length+1)/2;
        PosX = -m.getBreite();
        goToX = m.bahnen[bahn-1].getMitte();
        PosY = m.y - m.y/4;
        setChanged = true;
        c = newc;
    }
    
    public void getOut()
    {
        while(PosX < m.getAbahnen() * m.getBreite())
        {
            PosX += 10;
            textur.positionSetzen(PosX,PosY);
        }
    }
    
    public void collision()
    {
        c.collision();
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
        return (int)(m.getBreite()/40);
    }
    
    public int getbahn()
    {
        return bahn;
    }
    
}

    

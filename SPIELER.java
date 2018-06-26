import ea.*;
public abstract class SPIELER extends OBJECT
{
    public int bahn;
    public MODEL m;
    public float goToX;
    public CONTROLLER c;
    boolean running;
    
    public SPIELER(MODEL newm,CONTROLLER newc)
    {
        running = false;
        m = newm;
        bahn = (m.bahnen.length+1)/2;
        PosX = -m.getBreite();
        goToX = m.bahnen[bahn-1].getMitte();
        PosY = m.y - m.y/4;
        setChanged = false;
        c = newc;        
    }
    
    public void getOut()
    throws InterruptedException {
        while(PosX+0 < (m.getAbahnen()+2) * m.getBreite())
        {
            this.PosX += 5;
            textur.positionSetzen(PosX+0,PosY+0);
            Thread.sleep(5);
        }
        System.out.println("finished");
    }
       
    public void getIn()
    throws InterruptedException
    {
        PosX = -(m.getBreite());
        textur.positionSetzen(PosX,PosY);
        while(PosX < goToX)
        {
            PosX += 5;
            textur.positionSetzen(PosX,PosY);
            Thread.sleep(5);
        }
        System.out.println("finished");
    }
    
    
    public void start()
    {
        running = true;
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
        if(running == true)
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

    

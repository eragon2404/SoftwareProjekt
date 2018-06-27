import ea.*;
public class CLOCK implements Ticker
{
    boolean Allrunning;
    boolean Backrunning;
    MODEL m;
    VIEW v;
    CONTROLLER c;
    BACKGROUND b;
    
    public CLOCK(MODEL newm,VIEW newv,CONTROLLER newc,BACKGROUND newb)
    {
        m = newm;
        v = newv;
        c = newc;
        b = newb;
        Allrunning = false;
        Backrunning = true;
    }
    
    public void tick()
    {
        if(Allrunning == true)
        {
            v.update();
            c.tick();
            m.tick();
            b.tick();
        }
        else if(Backrunning == true)
        {
            b.tick();
            v.update();
            c.tick();
        }
   }
    
    public void start()
    {
        Allrunning = true;
    }
    
    public void stop()
    {
        Allrunning = false;
    }
    
    public void startBack()
    {
        Backrunning = true;
        Allrunning = false;
    }
    
    public void stopall()
    {
        Allrunning = false;
        Backrunning = false;
    }
    
    public boolean getRunning()
    {
        return Allrunning;
    }
}

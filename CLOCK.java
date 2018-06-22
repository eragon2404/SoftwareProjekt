import ea.*;
public class CLOCK implements Ticker
{
    boolean running;
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
        running = true;
    }
    
    public void tick()
    {
        if(running == true)
        {
            v.update();
            c.tick();
            m.tick();
            b.tick();
        }
   }
    
    public void start()
    {
        running = true;
    }
    
    public void stop()
    {
        running = false;
    }
    
    public boolean getRunning()
    {
        return running;
    }
}

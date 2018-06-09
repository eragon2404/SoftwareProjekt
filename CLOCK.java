import ea.*;
public class CLOCK implements Ticker
{
    boolean running;
    MODEL m;
    VIEW v;
    
    public CLOCK(MODEL newm,VIEW newv)
    {
        m = newm;
        v = newv;
        running = true;
    }
    
    public void tick()
    {
        if(running == true)
        {
            v.update();
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

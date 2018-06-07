import ea.*;
public class CONTROLLER
{
    MODEL m;
    VIEW v;
    CLOCK t;
    
    public CONTROLLER(MODEL newm, VIEW newv)
    {
        m = newm;
        v = newv;
        t = new CLOCK();
        v.manager.anmelden(t,1);
    }
    
    public void taste(int code)
    {
        switch(code) {
            case Taste.RECHTS: m.spieler.rechts(); break;
            case Taste.LINKS:  m.spieler.links(); break;
        }
    }
}
    
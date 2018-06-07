import ea.*;
public class CONTROLLER
{
    MODEL m;
    VIEW v;
    public CONTROLLER(MODEL newm, VIEW newv)
    {
        m = newm;
        v = newv;
    }
    
    public void taste(int code)
    {
        switch(code) {
            case Taste.RECHTS: m.spieler.rechts(); break;
            case Taste.LINKS:  m.spieler.links(); break;
        }
    }
}
    
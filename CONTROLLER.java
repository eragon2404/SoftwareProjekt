import ea.*;
import java.util.*;
public class CONTROLLER
{
    public static void main(String[] args)
    {
        new CONTROLLER();
    }
    MODEL m;
    VIEW v;
    CLOCK t;
    BACKGROUND b;
    SPIELER s;
    float chance;
    float score;
    
    public CONTROLLER()
    {
        v = new VIEW(5,this);
        m = new MODEL(5,v.getSX(),v.getSY());
        b = new BACKGROUND(5,v.getSX(),v,v.getSY());
        t = new CLOCK(m,v,this,b);
        v.manager.anmelden(t,50);
        chance = 0;
        score = 0;
        s = choose();
        v.addObservable(s);
        v.newMittelgrund(s.gettextur());
        m.newSpieler(s);
    }
    
    public void restart(int aBahn)
    {
        v.terminate();
        v = new VIEW(aBahn,this);
        m = new MODEL(5,v.getSX(),v.getSY());
    }
        
    public SPIELER choose()
    {
        switch(new Random().nextInt(7))
        {
            case 0:
                return new Baumstamm(m);
            case 1:
                return new Motorboot(m);
            case 2:
                return new Jetski(m);
            case 3:
                return new Oktopus(m);
            case 4:
                return new Kanu(m);
            case 5:
                return new Schildkroete(m);
            case 6:
                return new Schnorchler(m);
            default:
                return null;
        }
    }
    
    public void newSpieler(SPIELER s)
    {
        v.clearMittelgrund();
        v.addObservable(s);
        v.newMittelgrund(s.gettextur());
        m.newSpieler(s);
    }
    
    public void taste(int code)
    {
        switch(code) {
            case Taste.RECHTS: m.spieler.rechts(); break;
            case Taste.LINKS:  m.spieler.links(); break;
        }
    }
    
    public void tick()
    {
        s.tick();
        score += 0.1;
        v.setScore(score);
        if(calcTrue(chance) == true)
        {
            chance = 0;
            calcObjects();
        }
        else
        {
            chance += 0.0008;
        }
        System.out.println(chance);
    }
    
    private boolean calcTrue(float ch)
    {
        if(new Random().nextFloat() <= ch)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private void calcObjects()
    {
        LinkedList result = new LinkedList();
        for(int i = 0; i < m.getAbahnen(); i++)
        {
            result.add(i);
        }
        float delCH = 1;
        for(int i = 0; i < result.size();i++)
        {
            if(calcTrue(delCH) == true)
            {
                result.remove(new Random().nextInt(result.size()));
            }
            delCH /= 1.5;
        }
        
        for(int i = 0; i < result.size();i++)
        {
            generateObject((int)result.get(i));
        }
    }
    
    private void generateObject(int bahn)
    {
        int pX = (int)m.bahnen[bahn].getMitte();
        int br = m.bahnen[bahn].getBreite();
        switch(new Random().nextInt(2))
        {
            case 0:
                HINDERNISS obj = new Fels(pX,br);
                m.addHind(bahn,obj);
                v.newMittelgrund(obj.gettextur());
                v.addObservable(obj);
                System.out.println("Fels Added");
                System.out.println(bahn);
                break;
            case 2:
                HINDERNISS obj2 = new Krokodil(pX,br);
                m.addHind(bahn,obj2);
                v.newMittelgrund(obj2.gettextur());
                v.addObservable(obj2);
                System.out.println("Krokodil Added");
                System.out.println(bahn);
                break;
            case 1:
                HINDERNISS obj3 = new Treibholz(pX,br);
                m.addHind(bahn,obj3);
                v.newMittelgrund(obj3.gettextur());
                v.addObservable(obj3);
                System.out.println("Treibholz Added");
                System.out.println(bahn);
                break;
        }
    }
}
    
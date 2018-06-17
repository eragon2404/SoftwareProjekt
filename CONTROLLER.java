import ea.*;
import java.util.*;
public class CONTROLLER
{
    MODEL m;
    VIEW v;
    CLOCK t;
    float chance;
    
    public CONTROLLER(MODEL newm, VIEW newv)
    {
        m = newm;
        v = newv;
        t = new CLOCK(m,v,this);
        v.manager.anmelden(t,50);
        chance = 0;
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
        if(calcTrue(chance) == true)
        {
            chance = 0;
            calcObjects();
        }
        else
        {
            chance += 0.0005;
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
            delCH /= 2;
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
        switch(new Random().nextInt(1))
        {
            case 0:
                HINDERNISS obj = new Fels(pX,br);
                m.addHind(bahn,obj);
                v.hinzufuegen(obj.gettextur());
                v.addObservable(obj);
                System.out.println("Fels Added");
                System.out.println(bahn);
                break;
        }
    }
}
    
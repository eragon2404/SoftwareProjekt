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
        v.manager.anmelden(t,100);
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
        }
        else
        {
            chance += 0.0001;
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
        }
        
        for(int i = 0; i < result.size();i++)
        {
        }
    }
    
    private void generateObject(int bahn)
    {
        try{
            HINDERNISS obj = m.getHinderniss(new Random().nextInt(m.getLenHindernisse())).getClass().newInstance();
            m.addHind(bahn,obj);
            v.hinzufuegen(obj.gettextur());
            v.addObservable(obj);
        } catch(Exception e){}
        System.out.println("Added");
    }
}
    
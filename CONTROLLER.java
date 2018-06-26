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
    double chance;
    float score;
    int player;
    
    public CONTROLLER()
    {
        v = new VIEW(this);
        int aB = Integer.parseInt(v.eingabeFordern("Anzahl Bahnen"));
        m = new MODEL(aB,v.getSX(),v.getSY());
        b = new BACKGROUND(aB,v.getSX(),v,v.getSY());
        t = new CLOCK(m,v,this,b);
        v.manager.anmelden(t,50);
        chance = 0.0001;
        score = 0;
        player = new Random().nextInt(7);
        s = choose(player);
        try{
            s.getIn();
            } 
        catch (InterruptedException e)
            {System.out.println("ERROR");}
        v.addObservable(s);
        v.newMittelgrund(s.gettextur());
        m.newSpieler(s);
    }
    
    public void restart(int aBahn)
    {
        v.terminate();
        v = new VIEW(this);
        m = new MODEL(aBahn,v.getSX(),v.getSY());
    }
        
    public SPIELER choose(int p)
    {
        switch(p)
        {
            case 0:
                return new Baumstamm(m,this);
            case 1:
                return new Motorboot(m,this);
            case 2:
                return new Jetski(m,this);
            case 3:
                return new Oktopus(m,this);
            case 4:
                return new Kanu(m,this);
            case 5:
                return new Schildkroete(m,this);
            case 6:
                return new Schnorchler(m,this);
            default:
                return new Baumstamm(m,this);
        }
    }
    
    public void newSpieler(SPIELER ss)
    {
        System.out.println(player);
        v.clearMittelgrund();
        System.out.println("Mittelgrund clear");
        v.addObservable(ss);
        System.out.println("added");
        v.newMittelgrund(ss.gettextur());
        System.out.println("Mittelgrund added");
        m.newSpieler(ss);
        s = ss;
        try{
            ss.getIn();
            } 
        catch (InterruptedException e)
            {System.out.println("ERROR");}
        System.out.println("added in Model");
    }
    
    public void taste(int code)
    {
        if(t.getRunning() == true)
        {
            switch(code) {
                case Taste.RECHTS: m.spieler.rechts();
                System.out.println("rechts"); break;
                case Taste.LINKS:  m.spieler.links();
                System.out.println("links"); break;
            }
        }
        else
        {           
            switch(code) {
                case Taste.RECHTS: 
                    player += 1;
                    if(player >= 7)
                    {
                        player = 0;
                    }
                    try{
                        s.getOut();
                        newSpieler(choose(player));
                        } 
                    catch (InterruptedException e)
                        {System.out.println("ERROR");}                    
                    break;
                case Taste.ENTER:
                    StartGame();
                    break;
            }           
        }
    }
    
    public void StartGame()
    {
        t.start();
        s.start();
    }
    
    public void tick()
    {
        s.tick();
        if(t.getRunning() == true)
        {
            score += 0.1;
            v.setScore(score);
            if(calcTrue(chance) == true)
            {
                chance = 0.0005;
                calcObjects();
            }
            else
            {
                chance *= 1.2;
            }
            System.out.println(chance);
        }
    }
    
    public void collision()
    {
        t.stopall();
    }
    
    private boolean calcTrue(double ch)
    {
        if(new Random().nextDouble() <= ch)
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
        switch(new Random().nextInt(3))
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
    
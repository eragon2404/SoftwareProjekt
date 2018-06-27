import ea.*;
import java.util.LinkedList;

public class VIEW extends Game 
{
    private CONTROLLER c;
    public MODEL m;
    public LinkedList <OBJECT>observables1;
    public LinkedList <OBJECT>observables0;
    public Knoten Hintergrund;
    public Knoten Mittelgrund;
    public Knoten Vordergrund;
    int sx;
    int sy;
    Text SC;
    Maus fake;
    Maus real;
    Rechteck blende;
    Rechteck blende2;
    boolean isBlend;
    Sound click;
    Sound crash;
    Sound m1;
    Sound m2;

    public VIEW(CONTROLLER newc) {
        super(1000,1100,"Flippility");
        BoundingRechteck Fenster = fensterGroesse();
        sx = (int)Fenster.breite;
        sy = (int)Fenster.hoehe;
        blende = new Rechteck(0,0,sx,sy);
        blende.farbeSetzen(new Farbe(0,0,0,255));
        blende2 = new Rechteck(0,0,sx,sy);
        blende2.farbeSetzen(new Farbe(0,0,0,0));
        observables1 = new LinkedList();
        observables0 = new LinkedList();   
        Hintergrund = new Knoten();
        Mittelgrund = new Knoten();
        Vordergrund = new Knoten();        
        wurzel.add(Hintergrund);
        wurzel.add(Mittelgrund);
        wurzel.add(blende2);
        wurzel.add(Vordergrund); 
        wurzel.add(blende);
        click = new Sound("Recources/Click.wav");
        crash = new Sound("Recources/crash.wav");
        m1 = new Sound("Recources/Music1.wav");
        m2 = new Sound("Recources/Music2.wav");
        isBlend = true;
        c = newc; 
        SC = new Text("0",sx-sx/5,sy/5);       
    }
    
    public void startScreen()
    {
        Text t = new Text("Flippility",(int)(sx/2)-sx/4,(int)(sy/12),"LittleLordFontleroy",sx/4,0,"white");
        t.farbeSetzen(new Farbe(20,70,220));
        Figur startB = new Figur((sx/2)-(sx/12),(int)(sy/2.5),"Recources/Play.eaf");
        startB.faktorSetzen(sx/100);
        Figur switchB = new Figur(c.initPos-sx/60,sy-sy/7,"Recources/switch.eaf");
        switchB.faktorSetzen(sx/250);
        newVordergrund(t);
        newVordergrund(startB);
        newVordergrund(switchB);
        real = new Maus(new Figur(0,0,"Recources/maus.eaf"),new Punkt(3,3),false,false);        
        real.anmelden(c,startB,1);
        real.anmelden(c,switchB,2);
        fake = new Maus(new Figur(0,0,"Recources/clear.eaf"),new Punkt(0,0),false,false);
    }
    
    public void startMaus()
    {
        mausAnmelden(real);
    }
    
    public void endScreen(int score)
    {
        clearVordergrund();
        Text t = new Text("Game Over",(sx/2)-sx/(float)4.4,(sy/24),"LittleLordFontleroy",sx/6,0,"white");
        t.farbeSetzen(new Farbe(20,70,220));
        Text sco = new Text(Integer.toString(score),((sx/2)-sx/100)-(sx/33)*(String.valueOf(score).length()-1),(sy/5),"LittleLordFontleroy",sx/6,0,"white");
        sco.farbeSetzen(new Farbe(20,70,220));
        Figur restart = new Figur((sx/3)-sx/12,(int)(sy/2.5),"Recources/Restart.eaf");
        restart.faktorSetzen(sx/100);
        Figur save = new Figur((sx)-(sx/3)-sx/12,(int)(sy/2.5),"Recources/Save.eaf");
        save.faktorSetzen(sx/100);
        newVordergrund(t);
        newVordergrund(sco);
        newVordergrund(restart);
        newVordergrund(save);
        mausAnmelden(real);
        real.anmelden(c,restart,3);
        real.anmelden(c,save,4);
        try
        {
            blendOut();
        }
        catch(InterruptedException e) {}        
    }
    
    public void terminateEndScreen()
    {
        clearVordergrund();
        clearMittelgrund();
        blende2.farbeSetzen(new Farbe(0,0,0,0));
        mausAnmelden(fake);
    }
    
    public void blendIn() throws InterruptedException
    {
        isBlend = false;
        for(int i = 255; i > 0; i--)
        {
            blende.farbeSetzen(new Farbe(0,0,255-i,i));
            Thread.sleep(20);
        }
        startMaus();
    }
    
    public void blendOut() throws InterruptedException
    {
        for(int i = 0; i < 120; i++)
        {
            blende2.farbeSetzen(new Farbe(0,0,i/8,i));
            Thread.sleep(10);
        }
    }
    
    public void terminateScreen()
    {
        clearVordergrund();
        newVordergrund(SC);
        mausAnmelden(fake);
    }
    
    public void sClick()
    {
        click.play();
    }
    
    public void sCrash()
    {
        crash.play();
    }
    
    public void sM1()
    {
        m1.loop();
    }
    
    public void sM2()
    {
        m2.play();
    }
    
    public void stopM1()
    {
        m1.stop();
    }
    
    public void stopM2()
    {
        m2.stop();
    }
    
    public void setScore(float wert)
    {
        SC.inhaltSetzen(Integer.toString((int)wert));
    }
    
    public void setModel(MODEL newM)
    {
        m = newM;
    }
    
    public int getSX()
    {
        return sx;
    }
    
    public int getSY()
    {
        return sy;
    }
    
    public void newHintergrund(Raum obj)
    {
        Hintergrund.add(obj);
    }
    
    public void newMittelgrund(Raum obj)
    {
        Mittelgrund.add(obj);
    }
    
    public void newVordergrund(Raum obj)
    {
        Vordergrund.add(obj);
    }
    
    public void delHintergrund(OBJECT obj)
    {
        Hintergrund.entfernen(obj.gettextur());
        observables0.remove(obj);
    }
    
    public void delMittelgrund(OBJECT obj)
    {
        Mittelgrund.entfernen(obj.gettextur());
        observables1.remove(obj);
    }
    
    public void delVordergrund(Raum obj)
    {
        Vordergrund.entfernen(obj);
    }
    
    public void clearHintergrund()
    {
        observables0.clear();
        Hintergrund.leeren();
    }
    
    public void clearMittelgrund()
    {
        observables1.clear();
        Mittelgrund.leeren();
        //m.terminate();
    }
    
    public void clearVordergrund()
    {
        Vordergrund.leeren();
    }


    @Override
    public void tasteReagieren(int code) 
    {
        c.taste(code);
    }
    
    public void addObservable0(OBJECT obj)
    {
        observables0.add(obj);
    }
    
    public void addObservable(OBJECT obj)
    {
        observables1.add(obj);
    }
    
    public void update()
    {
        for(int i = 0; i < observables1.size(); i++)
        {
            OBJECT obj = observables1.get(i);
            if(obj.setChanged == true)
            {               
                obj.gettextur().positionSetzen(obj.getPosX()-obj.getBreite()/2,obj.getPosY());
                obj.actionPerformed();
            }
            if(obj.getPosY() > sy)
            {
                delMittelgrund(obj);
            }
        }
        
        for(int i = 0; i < observables0.size(); i++)
        {
            OBJECT obj = observables0.get(i);
            if(obj.setChanged == true)
            {               
                obj.gettextur().positionSetzen(obj.getPosX()-obj.getBreite()/2,obj.getPosY());
                obj.actionPerformed();
            }           
        }
    }
    
    public void terminate()
    {
        this.schliessen();
    }
}
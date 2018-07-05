import ea.*;
import java.util.LinkedList;

public class VIEW extends Game  //User-Interface fuer graphische und Audio ausgaben / Verbindungsstelle zur Engine Alpha fuer wichtige Aufgaben
{
    private CONTROLLER c;
    public MODEL m;
    public LinkedList <OBJECT>observables1;  //Liste aller zu Beobachtenden Objekte im Mittelgrund
    public LinkedList <OBJECT>observables0;  //Liste aller zu Beobachtenden Objekte im Vordergrund
    public Knoten Hintergrund;  //Hintere Zeichenebene
    public Knoten Mittelgrund;  //Mittlere Zeichenebene
    public Knoten Vordergrund;  //Vordere Zeichenebene
    int sx;  //Fenstergroesse x
    int sy;  //Fenstergroesse y
    Text SC;  //Score-Text
    Maus fake;  //Unsichtbare Maus
    Maus real;  //Richtige Maus
    Rechteck blende;  //Blende die alles ueberdeckt
    Rechteck blende2;  //Blende die Mittel und Hintergrund ueberdeckt
    boolean isBlend;  //Zustand der ersten Blende (zu/offen)
    Sound click;  //Sound fuer Klick
    Sound crash;  //Sound fuer Crash
    Sound m1;  //Normale Hintergrundmusik
    Sound m2;  //Traurige Hintergrundmusik

    public VIEW(CONTROLLER newc) {
        super(1000,1000,"Flippility");  //Fenstergroesse und Fenstername, Initialiesierung des Fensters
        BoundingRechteck Fenster = fensterGroesse();  //Gibt ein Rechteck mit der Fenstergroesse zurueck, notwendig bei Vollbild
        sx = (int)Fenster.breite;  //Uebernimmt die Werte vom Rechteck
        sy = (int)Fenster.hoehe;
        blende = new Rechteck(0,0,sx,sy);  //Erstellt eine Blende mit Fenstergroesse
        blende.farbeSetzen(new Farbe(0,0,0,255));  //Farbe = Schwarz, sichtbar
        blende2 = new Rechteck(0,0,sx,sy);  //Erstellt eine Blende mit Fenstergroesse
        blende2.farbeSetzen(new Farbe(0,0,0,0));  //Farbe = Schwarz, unsichtbar
        observables1 = new LinkedList();
        observables0 = new LinkedList();   
        Hintergrund = new Knoten();
        Mittelgrund = new Knoten();
        Vordergrund = new Knoten();        
        wurzel.add(Hintergrund);  //Fuegt die Ebenen und Blenden in richtiger Reinfolge der Wurzel hinzu
        wurzel.add(Mittelgrund);
        wurzel.add(blende2);
        wurzel.add(Vordergrund); 
        wurzel.add(blende);
        click = new Sound("Recources/Click.wav");  //Laed die Sounddateien
        crash = new Sound("Recources/crash.wav");
        m1 = new Sound("Recources/Music1.wav");
        m2 = new Sound("Recources/Music2.wav");
        isBlend = true;
        c = newc; 
        SC = new Text("0",sx-sx/5,sy/5);     
        real = new Maus(new Figur(0,0,"Recources/maus.eaf"),new Punkt(3,3),false,false); 
        fake = new Maus(new Figur(0,0,"Recources/clear.eaf"),new Punkt(0,0),false,false);
    }
    
    public void startScreen()  //Start Screen mit allen Inhalten die dort angezeigt werden sollen
    {
        Text t = new Text("Flippility",(int)(sx/2)-sx/4,(int)(sy/12),"LittleLordFontleroy",sx/4,0,"white");  //Titel
        t.farbeSetzen(new Farbe(20,70,220));  //kind of Blau
        Figur startB = new Figur((sx/2)-(sx/12),(int)(sy/2.5),"Recources/Play.eaf");  //Startknopf
        startB.faktorSetzen(sx/100);
        Figur switchB = new Figur(c.initPos-sx/60,sy-sy/7,"Recources/switch.eaf");  //Spielfigurwechselknopf
        switchB.faktorSetzen(sx/250);
        newVordergrund(t);  //Anmelden der Inhalte
        newVordergrund(startB);
        newVordergrund(switchB);        
        real.anmelden(c,startB,1);  //Anmelden der Knoepfe bei der Maus
        real.anmelden(c,switchB,2);
    }
    
    public void startMaus()  //Maus wird sichtbar
    {
        mausAnmelden(real);
    }
    
    public void endScreen(int score)  //End Screen mit allen Inhalten die dort angezeigt werden sollen
    {
        clearVordergrund(); //Entfernen der Scoreanzeige
        Text t = new Text("Game Over",(sx/2)-sx/(float)4.4,(sy/24),"LittleLordFontleroy",sx/6,0,"white");  //Game Over
        t.farbeSetzen(new Farbe(20,70,220));  //kind of Blau
        Text sco = new Text(Integer.toString(score),((sx/2)-sx/100)-(sx/33)*(String.valueOf(score).length()-1),(sy/5),"LittleLordFontleroy",sx/6,0,"white");
        //Score Anzeige mit uebertrieben kompliezierter Berechnung fuer PosX
        sco.farbeSetzen(new Farbe(20,70,220));  //kind of Blau
        Figur restart = new Figur((sx/3)-sx/12,(int)(sy/2.5),"Recources/Restart.eaf");  //Restartknopf
        restart.faktorSetzen(sx/100);
        Figur save = new Figur((sx)-(sx/3)-sx/12,(int)(sy/2.5),"Recources/Save.eaf");  //Score speichern Knopf
        save.faktorSetzen(sx/100);
        newVordergrund(t);  //Anmelden der Inhalte
        newVordergrund(sco);
        newVordergrund(restart);
        newVordergrund(save);
        mausAnmelden(real);  //Maus sichtbar machen
        real.anmelden(c,restart,3);  //Anmelden der Knoepfe bei der Maus
        real.anmelden(c,save,4);
        try
        {
            blendOut();  //Verdunkelt die Spielebene
        }
        catch(InterruptedException e) {}  //noetig fuer sleep()        
    }
    
    public void terminateEndScreen()  //Beendet den Endscreen
    {
        clearVordergrund();  //Entfernen der Inhalte
        clearMittelgrund();  //Entfernen der Hindernisse und Spieler
        blende2.farbeSetzen(new Farbe(0,0,0,0));  //Blende wird unsichtbar
        mausAnmelden(fake);  //Maus wird unsichtbar
    }
    
    public void blendIn() throws InterruptedException  //Blend-Animation von schwarz zu sichtbar bei Start des Spiels
    {
        isBlend = false;
        for(int i = 255; i > 0; i--)  //Alphawert der Blende von 255(max) bis 0 (und blauwert von 0 bis 255 (warum nicht?))
        {
            blende.farbeSetzen(new Farbe(0,0,255-i,i));
            Thread.sleep(20);  //Warte
        }
        startMaus();  //Maus wird sichtbar
    }
    
    public void blendOut() throws InterruptedException  //Blend-Animation zur Verdunkelung der Spielebene
    {
        for(int i = 0; i < 120; i++)  //Alphawert der Blende von 0 bis 120 (und blauwert von 0 bis 15 (warum nicht?))
        {
            blende2.farbeSetzen(new Farbe(0,0,i/8,i));
            Thread.sleep(10); //Warte
        }
    }
    
    public void terminateScreen()  //Beendet den Endscreen
    {
        clearVordergrund();  //Entfernen der Inhalte
        newVordergrund(SC);  //Fuegt die Scoreanzeige hinzu
        mausAnmelden(fake);  //Maus wird unsichtbar
    }
    
    public void sClick()
    {
        //click.play();
    }
    
    public void sCrash()
    {        
        //crash.play();        
    }
    
    public void sM1()
    {
        m1.loop();
    }
    
    public void sM2()
    {
        //m2.play();
    }
    
    public void stopM1()
    {
        m1.stop();
    }
    
    public void stopM2()
    {
        m2.stop();
    }
    
    public void setScore(float wert)  //aendern der Scoreanzeige
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
    
    public void newHintergrund(Raum obj)  //fuegt ein Objekt der Zeichenebene 0 hinzu
    {
        Hintergrund.add(obj);
    }
    
    public void newMittelgrund(Raum obj)  //fuegt ein Objekt der Zeichenebene 1 hinzu
    {
        Mittelgrund.add(obj);
    }
    
    public void newVordergrund(Raum obj)  //fuegt ein Objekt der Zeichenebene 2 hinzu
    {
        Vordergrund.add(obj);
    }
    
    public void delHintergrund(OBJECT obj)  //entfernt ein Object von der Zeichenebene 0 und der Beobachtungsliste 0
    {
        Hintergrund.entfernen(obj.gettextur());
        observables0.remove(obj);
    }
    
    public void delMittelgrund(OBJECT obj)  //entfernt ein Object von der Zeichenebene 1 und der Beobachtungsliste 1 
    {
        Mittelgrund.entfernen(obj.gettextur());
        observables1.remove(obj);
    }
    
    public void delVordergrund(Raum obj)  //entfernt ein Object von der Zeichenebene 2 und der Beobachtungsliste
    {
        Vordergrund.entfernen(obj);
    }
    
    public void clearHintergrund()  //entfernt alles von der Zeichenebene 0 und der Beobachtungsliste 0
    {
        observables0.clear();
        Hintergrund.leeren();
    }
    
    public void clearMittelgrund()  //entfernt alles von der Zeichenebene 1 und der Beobachtungsliste 1
    {
        observables1.clear();
        Mittelgrund.leeren();
        //m.terminate();
    }
    
    public void clearVordergrund()  //entfernt alles von der Zeichenebene 2 und der Beobachtungsliste 2
    {
        Vordergrund.leeren();
    }


    @Override
    public void tasteReagieren(int code)  //Nicht Aufgabe des Views (lol) wird an den Controller weitergegeben
    {
        c.taste(code);
    }
    
    public void addObservable0(OBJECT obj)  //Fuegt ein Objekt der Beobachtungsliste 0 hinzu
    {
        observables0.add(obj);
    }
    
    public void addObservable(OBJECT obj)  //Fuegt ein Objekt der Boebachtungsliste 1 hinzu
    {
        observables1.add(obj);
    }
    
    public void update()  //Prueft alle Objekte der Beobachtungsliste auf Veraenderung und setzt diese um
    {
        for(int i = 0; i < observables1.size(); i++)  //Liste 1
        {
            OBJECT obj = observables1.get(i);
            if(obj.setChanged == true)  //Bei Veraenderung
            {               
                obj.gettextur().positionSetzen(obj.getPosX()-obj.getBreite()/2,obj.getPosY());  //Umsetzen
                obj.actionPerformed();
            }
            if(obj.getPosY() > sy)  //Wenn Objekt auﬂerhalb des Fensters ist wird es entfernt
            {
                delMittelgrund(obj);
            }
        }
        
        for(int i = 0; i < observables0.size(); i++)  //Liste 0
        {
            OBJECT obj = observables0.get(i);
            if(obj.setChanged == true)  //Bei Veraenderung
            {               
                obj.gettextur().positionSetzen(obj.getPosX()-obj.getBreite()/2,obj.getPosY());  //Umsetzen
                obj.actionPerformed();
            }           
        }
    }
    
    public void terminate()  //Beendet das Fenster
    {
        this.schliessen();
    }
}
import ea.*;
import java.util.*;
public class CONTROLLER implements MausReagierbar  //Spielsteuernde Klasse
{
    public static void main(String[] args)  //Wird ausgefuehrt beim Starten der .jar Datei
    {
        new CONTROLLER();  //Erstellt einen Controller der den Rest auch erstellt
    }
    
    MODEL m;
    VIEW v;
    CLOCK t;
    BACKGROUND b;
    SPIELER s;
    SQL sql;
    double chance;  //Wahrscheinlichkeit dass neue Hindernisse gespawnt werden
    float score;  //Score des Nutzers
    int player;  //Index der derzeitigen Spielfigur
    float initPos;  //Anfaengliche SollXPosition fuer neue Spielfigur 
    
    public CONTROLLER()
    {
        v = new VIEW(this);  //View wird die graphische Oberflaeche starten
        int aB = Integer.parseInt(v.eingabeFordern("Anzahl Bahnen"));  //fraegt den Nutzer in einem Fenster nach der Anzahl der Bahnen
        m = new MODEL(aB,v.getSX(),v.getSY());  //Model wird die Bahnen etc erstellen
        b = new BACKGROUND(aB,v.getSX(),v,v.getSY());
        s = choose(player);  //erstellt den Spieler
        t = new CLOCK(m,v,this,b);
        v.manager.anmelden(t,50);  //Meldet die Clock bei der Engine Alpha an
        chance = 0.0001;
        score = 0;
        sql = new SQL();  //Datenbank
        player = new Random().nextInt(7);  //Zufaelliger Index fuer Start-Spielfigur        
        try{
            s.getIn();  //nicht wirklich noetig wegen der Blende
            } 
        catch (InterruptedException e)
            {System.out.println("ERROR");}  //noetig fuer sleep()
        v.addObservable(s);  //fuegt den Spieler der Beobachtungsliste hinzu
        v.newMittelgrund(s.gettextur());  //fuegt den Spieler zur graphischen Oberflaeche hinzu
        m.newSpieler(s);  //fuegt den Spieler dem Model hinzu
        initPos = s.getPosX();
        v.startScreen();  //Startet die Anfangs-Oberflaeche
        v.sM1();  //Startet die Musik
    }
    
    public void mausReagieren(int code)  //Reaktion auf Mausklicks auf Knoepfe
    {
        try{
        v.sClick();  //Klicksound
        }
        catch(Exception e) {}
        switch(code)
        {
            case 2:  //Der [SPIELFIGURSWITCH] -Knopf
                player += 1;  //rotiert die Spielfigur durch
                    if(player >= 7)
                    {
                        player = 0;
                    }
                    try{
                        s.getOut();  //laesst den alten Spieler elegant abtreten
                        newSpieler(choose(player));  //erstellt den neuen Spieler
                        } 
                    catch (InterruptedException e)  //noetig fuer sleep()
                        {System.out.println("ERROR");} 
                break;
                
            case 1:  //Der [PLAY] -Knopf
                v.terminateScreen();  //Beendet den Startscreen
                StartGame();  //Startet das Spiel
                break;
                
            case 3:  //Der [RESTART] -Knopf
                v.stopM2();  //stoppt die traurige Musik
                v.terminateEndScreen();  //Beendet den Endscreen
                score = 0;  //Resetet den Score     
                m.terminate();  //loescht alle Hinderniss
                v.startScreen();  //startet den Startscreen (wieder)
                v.startMaus();  //macht die Maus sichtbar
                newSpieler(choose(player));  //erstellt einen neuen (aber gleichen) Spieler 
                t.startBack();  //Startet die Clock damit der Hintergrund 
                v.sM1();  //Startet die Musik
                break;
                
            case 4:  //Der [SAVE] -Knopf
                sql.neu(v.eingabeFordern("Name:"),(int)score);
                break;  //Stehe die heilige Winnie uns bei  #SQL
        }
    }
   
    
    public void restart(int aBahn)  //Restartmethode - Zurzeit nicht zu benutzen
    {
        v.terminate();
        v = new VIEW(this);
        m = new MODEL(aBahn,v.getSX(),v.getSY());
    }
        
    public SPIELER choose(int p)  //Waehlt eine Spielfigur abhaengig vom Index aus
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
    
    public void newSpieler(SPIELER ss)  //Schritte fuer einen neuen Spieler
    {
        v.clearMittelgrund();  //Entfernt alles von der Spielebene - also auch den alten Spieler
        v.addObservable(ss);  //Fuegt den Spieler der Beobachtungsliste hinzu
        v.newMittelgrund(ss.gettextur());  //Fuegt den Spieler der graphischen Oberflaeche hinzu
        m.newSpieler(ss);  //Ersetzt den alten Spieler im Model durch den neuen
        s = ss;  //Ersetzt den alten Spieler durch den neuen
        try{
            ss.getIn();  //Reinfahranimation
            } 
        catch (InterruptedException e)
            {System.out.println("ERROR");}  //noetig fuer sleep()
    }
    
    public void taste(int code)  //Reagiert auf Tastendruecke
    {
        if(t.getRunning() == true)  //wenn das Spiel laeuft
        {
            switch(code) {
                case Taste.RECHTS: m.spieler.rechts();  //spieler nach rechts
                System.out.println("rechts"); break;
                case Taste.LINKS:  m.spieler.links();  //spieler nach links
                System.out.println("links"); break;
            }
        }
    }
    
    public void StartGame()  //Startet das Spiel
    {
        t.start();  //Startet alle Ticks in der Clock
        s.start();  //Startet Bewegungen des Spielers durch den Nutzer
    }
    
    public void tick()  //Zaehlt den Score mit und entscheidet ob Hindernisse gespawnt werden sollen
    {
        s.tick();  //gibt den Tick an den Spieler weiter
        if(t.getRunning() == true)  //Wenn das Spiel laeuft
        {
            score += 0.1;  //Score erhoehen
            v.setScore(score);  //Score dem View uebergeben
            if(calcTrue(chance) == true)  //Wenn neue Hindernisse gespawnt werden sollen
            {
                chance = 0.0005;  //chance zuruecksetzen
                calcObjects();  //Naechster Schritt 
            }
            else  //Wenn nicht
            {
                chance *= 1.2;  //Multipliziert die chance mit 1.2 #Exponentieller Anstieg -> gleichmaeﬂiger
            }
            System.out.println(chance);  //sieht cool aus
        }
    }
    
    public void collision()  //Schritte bei einer Kollision
    {
        t.stopall();  //Stoppt die Clock
        v.stopM1();  //Stoppt die Musik
        v.sCrash();  //Crashsound
        v.endScreen((int)score);  //Startet Endscreen
        v.sM2();  //Traurige Musik
    }
    
    private boolean calcTrue(double ch)  //Gibt mit einer Wahrscheinlichkeit von ch "WAHR" zurueck
    {
        if(new Random().nextDouble() <= ch)  //Erstellt einen Zuffalswert, wenn dieser unter ch liegt wird true returned
        {
            return true;
        }
        else  //sonst false
        {
            return false;
        }
    }
    
    private void calcObjects()  //Sucht sich zufaellige Bahnen raus, bei denen Hindernisse gespawnt werden sollen
    {
        LinkedList result = new LinkedList();  //Liste aller Bahnenindexe
        for(int i = 0; i < m.getAbahnen(); i++)  //Fuellt die Liste
        {
            result.add(i);
        }
        float delCH = 1;  //Wahrscheinlichkeit, dass eine weitere Bahn aus der Liste entfernt wird. Anfangs 1, damit auf jeden Fall eine entfernt wird.
        for(int i = 0; i < result.size();i++)  //Wiederholt so oft wie es Bahnen gibt
        {
            if(calcTrue(delCH) == true) 
            {
                result.remove(new Random().nextInt(result.size()));  //Entfernt eine zufaellige Bahn aus der Liste
            }
            delCH /= 1.5;  //Verkleinert delCH
        }
        
        for(int i = 0; i < result.size();i++)  //Fuer alle verbliebenen Bahnen
        {
            generateObject((int)result.get(i));  //Naechster Schritt
        }
    }
    
    private void generateObject(int bahn)  //Waehlt ein zufaelliges Hindeniss aus und spawnt es auf der Bahn mit index bahn
    {
        int pX = (int)m.bahnen[bahn].getMitte();  //Mitte der Bahn
        int br = m.bahnen[bahn].getBreite();  //Breite der Bahn
        switch(new Random().nextInt(3))  //Zufalls wert (Ganzzahl)
        {
            case 0:
                HINDERNISS obj = new Fels(pX,br);  //Erstellt das Hinderniss
                m.addHind(bahn,obj);  //Fuegt es dem Model hinzu
                v.newMittelgrund(obj.gettextur());  //Fuegt das Hinderniss dem View hinzu
                v.addObservable(obj);  //Fuegt das Hinderniss der Beobachtungsliste hinzu
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
    
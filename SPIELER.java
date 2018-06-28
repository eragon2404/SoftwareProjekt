import ea.*;
public abstract class SPIELER extends OBJECT  //SuperKlasse aller Spielfiguren
{
    public int bahn;  //Index der Bahn auf der sich der Spieler befindet
    public MODEL m;
    public float goToX;  //X-Position, die erreicht werden soll
    public CONTROLLER c;
    boolean running;  //Bewegung durch Nutzer nur bei "WAHR" moeglich
    
    public SPIELER(MODEL newm,CONTROLLER newc)
    {
        running = false;
        m = newm;
        bahn = (m.bahnen.length+1)/2;  //Anfangsbahn moeglichst in der Mitte
        PosX = -m.getBreite();  //Anfangsposition links außen vom Fenster
        goToX = m.bahnen[bahn-1].getMitte();  //Sollposition passend zur Bahn
        PosY = m.y - m.y/4;  //Y-Position auf 3/4 der Hoehe von oben
        setChanged = false;
        c = newc;        
    }
    
    public void getOut()  //Animation zum rechts-raus-Bewegen
    throws InterruptedException {  //noetig für sleep()
        while(PosX < (m.getAbahnen()+2) * m.getBreite())  //bis rechts außerhalb des Fensters
        {
            PosX += 5;  //PosX erhoehen
            textur.positionSetzen(PosX,PosY);  //Umsetzen
            Thread.sleep(5);  //Warten
        }
        System.out.println("finished");
    }
       
    public void getIn()  //Animation zum rechts-rein-Bewegen
    throws InterruptedException  //noetig für sleep()
    {
        PosX = -(m.getBreite());  //PosX links außerhalb des Fensters
        textur.positionSetzen(PosX,PosY);  //Umsetzen
        while(PosX < goToX)  //bis auf der richtigen Bahn
        {
            PosX += 5;  //PosX erhoehen
            textur.positionSetzen(PosX,PosY);  //Umsetzen
            Thread.sleep(5);  //Warten
        }
        System.out.println("finished");
    }
    
    
    public void start()  //Starte Bewegung durch Nutzer
    {
        running = true;
    }
    
    public void collision()  //Gebe Befehl an Controller weiter
    {
        c.collision();
    }
    
    public void links()  //Eine Bahn nach links
    {
        if(bahn > 1)  //Wenn noch nicht ganz links
        {
            bahn --;
            goToX = m.bahnen[bahn-1].getMitte();  //SollPosition der neuen Bahn anpassen
        }
    }
    
    public void rechts()  //Eine Bahn nach rechts
    {
        if(bahn < m.bahnen.length)  //Wenn noch nicht ganz rechts
        {
            bahn ++;
            goToX = m.bahnen[bahn-1].getMitte();  //SollPosition der neuen Bahn anpassen
        }
    }
    
    public void tick()
    {
        if(running == true)
        {
            if(PosX - goToX > 10)  //Wenn Spieler rechts von SollPosition (Toleranz wegen Ungenauigkeit)
            {
                PosX -= 20;  //PosX nach links schieben
                setChanged = true;
            }
            else if(PosX - goToX < -10)  //Wenn Spieler links von SollPosition (Toleranz wegen Ungenauigkeit)
            {
                PosX += 20;  //PosX nach rechts schieben
                setChanged = true;
            }
        }
    }
    
    public int calcFaktor()  //Berechnung des Vergroeßerungsfaktors fuer die Textur
    {
        return (int)(m.getBreite()/40);  //1/40 von der Bahnbreite
    }
    
    public int getbahn()
    {
        return bahn;
    }
    
}

    

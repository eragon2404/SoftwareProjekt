import ea.*;
public class MODEL  //Verwaltet das Model
{
    public SPIELER spieler;  //Die Spielfigur
    public BAHN[] bahnen;  //Array aller Bahnen
    public int x;  //Fenstergroesse x
    public int y;  //Fenstergroesse y
    
    public MODEL(int aBahn,int nx,int ny)
    {
        bahnen = new BAHN[aBahn];
        x = nx;
        y = ny;
        for(int i = 0; i < aBahn; i++)  //Erzeugung der Bahnen
        {
            bahnen[i] = new BAHN(i+1,aBahn,x,y);
        }
    }
    
    public void tick()  //gibt tick an alle Bahnen weiter
    {
        for(int i = 0; i < bahnen.length; i++)
        {
            bahnen[i].check(spieler);
        }
    }
    
    public void terminate()  //gibt terminate an alle Bahnen weiter
    {
        for(int i = 0; i < bahnen.length; i++)
        {
            bahnen[i].terminate();
        }
    }
    
    public SPIELER getSpieler()
    {
        return spieler;
    }
    
    public int getBreite()  //breite einer Bahn
    {
        return x/(bahnen.length+2);
    }
    
    public int getAbahnen()
    {
        return bahnen.length;
    }    
    
    public void addHind(int bahn, HINDERNISS hind)  //gibt das Hinderniss der richtigen Bahn weiter
    {
        bahnen[bahn].addOBJ(hind);
    }
    
    public void newSpieler(SPIELER newSp)  //neusetzung der Spielerreferenz
    {
        spieler = newSp;
    }
        
}
    
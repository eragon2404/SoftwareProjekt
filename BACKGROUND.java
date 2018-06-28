import java.util.LinkedList;
public class BACKGROUND  //Verwaltet den Hintergrund
{
    int bahnen;
    int breite;
    int y;
    VIEW v;
    OBJECT lastY;  //Referenz auf die letzte erstellte Textur
    float velo;  //Geschwindigkeit
    LinkedList<OBJECT> objects;  //Liste aller Texturen
    FullWater w;  //Wasser
    
    public BACKGROUND(int newbahnen, int newbreite, VIEW newv, int newy)
    {
        bahnen = newbahnen +2;
        breite = newbreite/bahnen;
        v = newv;
        velo = (float)0.0008;
        objects = new LinkedList();
        newRow();
        y = newy;
        w = new FullWater(bahnen,breite,y,v);
    }
    
    public void tick()
    {
        for(int i = 0; i < objects.size(); i++)  //Geht alle Hintergrundtexturen durch
        {
            OBJECT obj = objects.get(i);
            obj.tick();  //Gibt den Tick weiter
            if(obj.getPosY() > y)  //Wenn Textur außerhalb des Fensters
            {
                v.delHintergrund(obj);  //Wird von der graphischen Oberflaeche entfernt
                objects.remove(obj);  //Und aus der Liste entfernt
                if(v.isBlend == true)  //Wenn die Blende im View noch aktiv ist
                {
                    try {
                    v.blendIn();  //Wird die ausblend Animation ausgefuerht 
                    }
                    catch(InterruptedException e) {}  //noetig fuer sleep()
                }
            }
        }
        if(lastY.getPosY() >= -4)  //Wenn die letzte Reihe vollstaendig im Bild ist
        {
            newRow();  //Wird eine neue Reihe erstellt
        }
        
    }
    
    private void newRow()  //Erstellt eine neue Reihe
    {
        for(int i = 0; i < bahnen; i++)  //Fuer alle Bahnen
        {
            OBJECT obj = null;
            if(i == 0 || i == bahnen-1)  //Wenn Bahn ganz links oder ganz rechts
            {
                obj = new GRAS(i+1,breite);  //neue Grastextur
                objects.add(obj);  //Zur Liste hinzufuegen 
                v.addObservable0(obj);  //Zur Beobachtungsliste von View hinzufuegen
                v.newHintergrund(obj.gettextur());  //Zur graphischen Oberflaeche hinzufuegen
            }
            else  //sonst 
            {
                //obj = new WASSER(i+1,breite);  //Wassertextur (ersetzt durch Fullwater wegen Lag)
            }
            
        }
        lastY = objects.getLast();  //lastY neu setzen
    }
}

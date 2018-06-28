import java.util.LinkedList;
public class BAHN  //Verwaltet die Hindernisse einer Bahn
{
    int index;  //Index der Bahn
    int breite;
    float hoehe;
    float mitte;
    LinkedList<HINDERNISS> aktiv;  //Liste aller aktiven Hindernisse
    
    public BAHN(int newindex, int anzahl, int gesbreite, int newhoehe)
    {
        index = newindex;
        hoehe = newhoehe;
        breite = gesbreite/(anzahl + 2);
        mitte = (((((float)index*2f)+1f)/2f) * (float)breite);  //Berechnung der Mitte der Bahn
        aktiv = new LinkedList();
    }
    
    public void addOBJ(HINDERNISS obj)  //fuegt ein Hinderniss der aktiv liste hinzu
    {
        aktiv.add(obj);
    }
    
    public void check(SPIELER spieler)  //Gibt den Tick an die Hindernisse weiter und loescht sie ggf
    {
        for(int i = 0; i < aktiv.size(); i++)  //fuer alle hindernisse in aktiv
        {
            HINDERNISS obj = aktiv.get(i);
            obj.tick(spieler);  //gibt Tick weiter
            if(obj.getPosY() > hoehe)  //wenn Hinderniss ausserhalb des Fensters
            {
                aktiv.remove(obj);  //wird es entfernt
            }
        }
    }
    
    public float getMitte()
    {
        return mitte;
    }
    
    public int getBreite()
    {
        return breite;
    }
    
    public void terminate()  //entfernt alle Objekte der Bahn
    {
        aktiv.clear();
    }
}

import java.util.LinkedList;
public class BAHN
{
    int index;
    int breite;
    float hoehe;
    float mitte;
    LinkedList<HINDERNISS> aktiv;
    
    public BAHN(int newindex, int anzahl, int gesbreite, int newhoehe)
    {
        index = newindex;
        hoehe = newhoehe;
        breite = gesbreite/(anzahl + 2);
        mitte = (((((float)index*2f)+1f)/2f) * (float)breite);
        aktiv = new LinkedList();
    }
    
    public void addOBJ(HINDERNISS obj)
    {
        aktiv.add(obj);
    }
    
    public void check(SPIELER spieler)
    {
        for(int i = 0; i < aktiv.size(); i++)
        {
            HINDERNISS obj = aktiv.get(i);
            obj.tick(spieler);
            if(obj.getPosY() > hoehe)
            {
                aktiv.remove(obj);
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
    
    public void terminate()
    {
        aktiv.clear();
    }
}

import java.util.LinkedList;
public class BAHN
{
    int index;
    float breite;
    float hoehe;
    float mitte;
    LinkedList<OBJECT> aktiv;
    
    public BAHN(int newindex, int anzahl, int gesbreite, int newhoehe)
    {
        index = newindex;
        hoehe = newhoehe;
        breite = gesbreite/(anzahl + 2);
        mitte = (((((float)index*2)+1)/2) * (float)breite);
    }
    
    public void addOBJ(OBJECT obj)
    {
        aktiv.add(obj);
    }
    
    public void check(SPIELER spieler)
    {
        for(int i = 0; i < aktiv.size(); i++)
        {
            aktiv.get(i).tick(spieler);
        }
    }
}

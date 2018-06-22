import java.util.LinkedList;
public class BACKGROUND
{
    int bahnen;
    int breite;
    int y;
    VIEW v;
    OBJECT lastY;
    float velo;
    LinkedList<OBJECT> objects;
    public BACKGROUND(int newbahnen, int newbreite, VIEW newv, int newy)
    {
        bahnen = newbahnen +2;
        breite = newbreite/bahnen;
        v = newv;
        velo = (float)0.0008;
        objects = new LinkedList();
        newRow();
        y = newy;
    }
    
    public void tick()
    {
        for(int i = 0; i < objects.size(); i++)
        {
            OBJECT obj = objects.get(i);
            obj.tick();
            if(obj.getPosY() > y)
            {
                v.delHintergrund(obj);
                objects.remove(obj);
            }
        }
        if(lastY.getPosY() >= 0)
        {
            newRow();
        }
        
    }
    
    private void newRow()
    {
        for(int i = 0; i < bahnen; i++)
        {
            OBJECT obj = null;
            if(i == 0 || i == bahnen-1)
            {
                obj = new GRAS(i+1,breite);
            }
            else
            {
                obj = new WASSER(i+1,breite);
            }
            objects.add(obj);
            v.addObservable0(obj);
            v.newHintergrund(obj.gettextur());
        }
        lastY = objects.getLast();
    }
}

import ea.*;
public class FullWater
{
    public Rechteck textur;
    
    public FullWater(int aBahn, int breite, int hoehe, VIEW v)
    {
        int x = breite;
        int y = 0;
        int sx = (aBahn -2)*breite;
        int sy = hoehe;
        textur = new Rechteck(x,y,sx,sy);
        textur.farbeSetzen(new Farbe(1,1,1));
        v.newHintergrund(textur);
    }
}

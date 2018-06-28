import ea.*;
public class FullWater  //Rechteck fuer das Wasser, alternative fuer die Wassertextur
{
    public Rechteck textur;
    
    public FullWater(int aBahn, int breite, int hoehe, VIEW v)
    {
        int x = breite;
        int y = 0;
        int sx = (aBahn -2)*breite;
        int sy = hoehe;
        textur = new Rechteck(x,y,sx,sy);
        textur.farbeSetzen(new Farbe((int)(0.336*255),(int)(0.66*255),(int)(1.0*255)));  //hellblau
        v.newHintergrund(textur);
    }
}

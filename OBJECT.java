import ea.*;
public abstract class OBJECT  //SuperKlasse aller Objekte
{
    public boolean setChanged;  //Wenn "WAHR" wird View die Veraenderung umsetzen
    public Figur textur;  //Eigentliche Textur (20*20 Pixel) im .eaf Format, im Recources Ordner abgelegt
    public float PosX;  //Position auf X-Achse von links
    public float PosY;  //Position auf Y-Achse von oben
    public int breite;  //Breite des Objekts
    
    public Figur gettextur()
    {
        return textur;
    }
    
    public float getPosX()
    {
        return PosX;
    }
    
    public float getPosY()
    {
        return PosY;
    }
    
    public void tick()  //Aktion die jeden Tick ausgefuehrt werden soll
    {
    }
    
    public void actionPerformed()  //Wird vom View aufgerufen nach Umsetzung der Veraenderung
    {
        setChanged = false;
    }
    
        public int getBreite()
    {
        return breite;
    }
}

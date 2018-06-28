import ea.*;
public abstract class HINDERNISS extends OBJECT  //SuperKlasse aller Hindernisse
{
    public HINDERNISS(int newPosX,int newBreite)
    {
        PosX = newPosX;
        breite = newBreite/2;  //Breite ist 1/2 der Bahnbreite
        PosY = -(breite);  //Anfangsposition ueber den oberen Fensterrand
        setChanged = true;
    }
    
    public void tick(SPIELER spieler)
    {
        if(textur.schneidet(spieler.gettextur())==true)  //Prueft auf Kollision mit Spieler
        {
            spieler.collision();  //Benachrichtigt den Spieler/Controller
        }
        PosY += 10;  //Bewegung nach unten
        setChanged = true;
    }
    
}

package sample;

/**
 * Initialisierung eines Point Objektes
 */
public class Point {
    private double x;
    private double y;
    private double z;

    /**
     * Constructor
     * @param x,y,z Koordinaten des Punktes
     */
    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Default Constructor
     */
    public Point(){
        this.x=0;
        this.y=0;
        this.z=0;
    }

    /**
     * Set Methode
     * @param x,y,z Koordinaten des Punktes
     */
    public void setPoint(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Get Methode
     * @return Array mit Koordinaten des Punktes
     */
    public double[] getPoint(){
        return new double[] {this.x,this.y,this.z};
    }
}

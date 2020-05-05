package sample;


/**
 * Initialisierung eines Tetraeder Objekts und
 * Erzeugung von Point Objekten
 */
public class Tetraeder {
    //private Point p1;
    //private Point p2;
    //private Point p3;
    //private Point p4;

    private Point p1 = new Point();
    private Point p2 = new Point();
    private Point p3 = new Point();
    private Point p4 = new Point();

    private Triangle A = new Triangle(p2,p3,p4);
    private Triangle B = new Triangle(p1,p3,p4);
    private Triangle C = new Triangle(p1,p2,p4);
    private Triangle D = new Triangle(p1,p2,p3);
    /**
     * Constructor
     * @param p1,p2,p3,p4 Pointobjekte
     */
    public Tetraeder(Point p1, Point p2, Point p3, Point p4){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;

    }

    /**
     * Default Constructor
     */
    public Tetraeder(){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Triangle[] getFlaechen(){
        return new Triangle[] {this.A,this.B,this.C,this.D};
    }

    /**
     * Get Methode
     * @return Array mit Point Objekten
     */
    public Point[] getTetraeder(){
        return new Point[] {this.p1, this.p2, this.p3,this.p4};
    }

}

package sample;


public class Triangle implements Comparable<Triangle>{
    private Point p1;
    private Point p2;
    private Point p3;
    private double z;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;


    }
    public double getz(){
        double[] zl={this.p1.getPoint()[1],this.p2.getPoint()[1],this.p3.getPoint()[1]};
        double zmin,zmax;
        if (zl[0]>zl[1] && zl[0]>zl[2]){zmax=zl[0];}
        else if (zl[1]>zl[0] && zl[1]>zl[2]){zmax=zl[1];}
        else                                {zmax=zl[2];}
        if (zl[0]<zl[1] && zl[0]<zl[2]){zmin=zl[0];}
        else if (zl[1]<zl[0] && zl[1]<zl[2]){zmin=zl[1];}
        else                                {zmin=zl[2];}

        this.z=(zmin+zmax)/2;
        //System.out.print(this.z+"allz= ");
        //System.out.print(this.p1.getPoint()[2]+" ");
        //System.out.print(this.p2.getPoint()[2]+" ");
        //System.out.println(this.p3.getPoint()[2]);
        return this.z;
    }
    public Triangle(){
        Point p1 = new Point();
        Point p2 = new Point();
        Point p3 = new Point();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;


    }

    public Point[] getTriangle(){
        return new Point[] {this.p1, this.p2, this.p3};
    }

    @Override
    public int compareTo(Triangle t){
        return new Double(getz()).compareTo(new Double(t.getz()));
    }

}

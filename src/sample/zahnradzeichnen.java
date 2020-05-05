package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class zahnradzeichnen {
    ///@cond
    public ArrayList<Triangle> triangleList = new ArrayList<Triangle>();
    public AnchorPane canvaspane;
    ///@endcond
    public void draw(AnchorPane canvaspane) throws IOException {
        this.canvaspane=canvaspane;
        this.triangleList = zahnrad.einlesen();
        redraw();
    }

    public void sortieren(){
        ArrayList<Triangle> tl=this.triangleList;
        Triangle[]tla=new Triangle[tl.size()];
        tl.toArray(tla);
        Arrays.sort(tla, Collections.reverseOrder());
        this.triangleList=new ArrayList<Triangle>(Arrays.asList(tla));

    }
    public void redraw(){

        canvaspane.getChildren().clear();
        double koord, koordxyz;
        sortieren();


        for (Triangle t1 : triangleList){
            //System.out.println(t1.getz());
            Polygon poly = new Polygon();
            ArrayList<Double> koordinaten =new ArrayList<Double>();
            ArrayList<Double> koordinatenxyz =new ArrayList<Double>();
            for (int i=0;i<3;i++) {
                for (int j = 0; j < 3; j++) {
                    koordinatenxyz.add(t1.getTriangle()[i].getPoint()[j]);
                    if (j == 0) {
                        koord = (9 + t1.getTriangle()[i].getPoint()[j]) * 50;
                        koordinaten.add(koord);
                    }
                    if (j == 1) {
                        koord = (t1.getTriangle()[i].getPoint()[j]) * 50;

                    }
                    if (j == 2) {
                        koord = (9 - t1.getTriangle()[i].getPoint()[j]) * 50;
                        koordinaten.add(koord);
                    }
                }
            }


            //double grad;
            //grad=getangleoftriangle(koordinatenxyz);
            Double grad=angle(t1);
            if (grad>180){System.out.println(grad);}
            //if (grad>90 && grad<270){
              //  poly.setStroke(null);
            //}
            poly.setStroke(null);
            /*
            grad=scale(grad);
            grad=grad/3;
            grad-=30;
            grad=Math.abs(grad);
            int gradint=(int) Math.round(grad);
            int c1=(gradint*8)+8;
            */

            //grad=grad/180*100;
            grad+=40;
            int c1=grad.intValue();
            String c2=String.valueOf(c1);
            String c3="rgb("+c2+","+c2+","+c2+")";
            Color c=Color.web(c3);
            poly.setFill(c);

            //System.out.println(grad);
            poly.getPoints().addAll(koordinaten);
            canvaspane.getChildren().add(poly);
        }

    }
    public static double angle(Triangle t1){
        double[] a,b,c;
        double vx,vy,vz;

        a=t1.getTriangle()[0].getPoint();
        b=t1.getTriangle()[1].getPoint();
        c=t1.getTriangle()[2].getPoint();

        //richtungsvektoren der ebene der Punkte a,b,c
        double[] rv1=new double[] {b[0]-a[0],b[1]-a[1],b[2]-a[2]};
        double[] rv2=new double[] {c[0]-a[0],c[1]-a[1],c[2]-a[2]};

        //kreuzprodukt von rv1 und rv2
        vx=(rv1[1]*rv2[2])-(rv1[2]*rv2[1]);
        vy=(rv1[2]*rv2[0])-(rv1[0]*rv2[2]);
        vz=(rv1[0]*rv2[1])-(rv1[1]*rv2[0]);

        double[] v= new double[] {vx,vy,vz};
        double[] u= new double[] {0,0,1};

        double skalarp=v[2]*u[2];
        double betragv=Math.sqrt((v[0]*v[0])+(v[1]*v[1])+(v[2]*v[2]));
        double betragu=Math.sqrt(u[2]);

        double cosalpha=skalarp/(betragu+betragv);
        return Math.toDegrees(Math.acos(cosalpha));
    }
    public double scale(double grad){
        while (grad>90){
            if (grad>180 && grad<=270){
                grad-=180;
            }
            if (grad>=90 && grad<=180){
                grad-=180;
                grad= Math.abs(grad);
            }
            if (grad>270 && grad<=360){
                grad-=360;
                grad=Math.abs(grad);
            }
        }

        return grad;
    }
    public ArrayList<Triangle> sort(ArrayList<Triangle> obj_arr){
        for (int i=0; i<obj_arr.size()-1;i++){
            for (int j=obj_arr.size()-1;j>i;j--) {
                Triangle[] comp={obj_arr.get(i),obj_arr.get(j)};
                comp=compare(comp);
                obj_arr.set(i,comp[0]);
                obj_arr.set(j,comp[1]);
            }
        }

        return obj_arr;
    }
    
    public Triangle[] compare(Triangle[] comp){
        Triangle ttemp;
        double ix1,iy1,iz1,ix2,iy2,iz2,ix3,iy3,iz3;
        double jx1,jy1,jz1,jx2,jy2,jz2,jx3,jy3,jz3;
        comp[0].getTriangle()[0].getPoint();
        ix1=comp[0].getTriangle()[0].getPoint()[0];
        iy1=comp[0].getTriangle()[0].getPoint()[2];
        iz1=comp[0].getTriangle()[0].getPoint()[1];
        ix2=comp[0].getTriangle()[1].getPoint()[0];
        iy2=comp[0].getTriangle()[1].getPoint()[2];
        iz2=comp[0].getTriangle()[1].getPoint()[1];
        ix3=comp[0].getTriangle()[2].getPoint()[0];
        iy3=comp[0].getTriangle()[2].getPoint()[2];
        iz3=comp[0].getTriangle()[2].getPoint()[1];
        jx1=comp[1].getTriangle()[0].getPoint()[0];
        jy1=comp[1].getTriangle()[0].getPoint()[2];
        jz1=comp[1].getTriangle()[0].getPoint()[1];
        jx2=comp[1].getTriangle()[1].getPoint()[0];
        jy2=comp[1].getTriangle()[1].getPoint()[2];
        jz2=comp[1].getTriangle()[1].getPoint()[1];
        jx3=comp[1].getTriangle()[2].getPoint()[0];
        jy3=comp[1].getTriangle()[2].getPoint()[2];
        jz3=comp[1].getTriangle()[2].getPoint()[1];

        if ((iz1+iz2+iz3)/3<(jz1+jz2+jz3)/3){
            ttemp=comp[0];
            comp[0]=comp[1];
            comp[1]=ttemp;
            return comp;
        }
        /*######Depth-Sort-Algorithmus########*/
        /*
        //1.
        if (max(iz1,iz2,iz3)<min(jz1,jz2,jz3)){
            ttemp=comp[0];
            comp[0]=comp[1];
            comp[1]=ttemp;
            return comp;
        }

        //2.

        else if (   überlappung(ix1,iy1,ix2,iy2,ix3,iy3,jx1,jy1) &&
                    überlappung(ix1,iy1,ix2,iy2,ix3,iy3,jx2,jy2) &&
                    überlappung(ix1,iy1,ix2,iy2,ix3,iy3,jx3,jy3)   ){
            return comp;
        }
        */
        //System.out.println("zyklisch überlappend");
        return comp;




    }
    public boolean überlappung(double x1,double x2,double x3,double x4,double y1,double y2,double y3,double y4){
        double r,s;
        s=(-x1*y2+x1*y4+x2*y1-x2*y4-x4*y1+x4*y2)/(-x1*y2+x1*y3+x2*y1-x2*y3-x3*y1+x3*y2);
        r=(s*(-x1+x3)+x1-x4)/(x1-x2);

        return !(0<=s && s<=1 && 0<=r && r<=1 && 0<=(s+r) && (s+r)<=1);
    }

    public double max(double n1,double n2,double n3){
        if (n1>=n2 & n1>=n3){return n1;}
        else if (n2>=n1& n2>=n3){return n2;}
        else {return n3;}
    }
    public double min(double n1,double n2,double n3){
        if (n1<=n2 && n1<=n3){return n1;}
        else if (n2<=n1 && n2<=n3){return n2;}
        else {return n3;}
    }


    public double getangleoftriangle(ArrayList<Double> k){
        ArrayList<Double> n= new ArrayList<>();
        double x1,x2,x3,y1,y2,y3,z1,z2,z3;
        x1=k.get(0); y1=k.get(1); z1=k.get(2);
        x2=k.get(3); y2=k.get(4); z2=k.get(5);
        x3=k.get(6); y3=k.get(7); z3=k.get(8);
        n.add((y2-y1)*(z3-z1)-(z2-z1)*(y3-y1));
        n.add((z2-z1)*(x3-x1)-(x2-x1)*(z3-z1));
        n.add((x2-x1)*(y3-y1)-(y2-y1)*(x3-x1));

        double alpha,a,b,c;
        a=n.get(0);b=n.get(1);c=n.get(2);
        alpha = Math.acos(b/(Math.sqrt(a*a+b*b+c*c)));
        alpha=360/2*Math.PI*alpha;
        while (alpha>360){alpha-=360;}
        //if (alpha>180){alpha-=180;}
        //if (alpha>90){alpha-=90;}
        return alpha;
    }

    private double  xgrad=0,ygrad=0,zgrad=0;

    public void rotieren(double x, double y, double z){
        double bmx,bmy,bmz;
        bmx=2*Math.PI/360*x;
        bmy=2*Math.PI/360*y;
        bmz=2*Math.PI/360*z;
        double[] A,B,C;
        for (Triangle t1 : triangleList){
            A=t1.getTriangle()[0].getPoint();
            B=t1.getTriangle()[1].getPoint();
            C=t1.getTriangle()[2].getPoint();
            double[][]punkte={A,B,C};

            for (int i=0; i<punkte.length; i++){
                double temp0,temp1,temp2;
                //Rotation um Parallele der X-Achse durch das Rotationszentrum
                if (bmx!=0){
                    temp1=punkte[i][1]*Math.cos(bmx)-punkte[i][2]*Math.sin(bmx);
                    temp2=punkte[i][1]*Math.sin(bmx)+punkte[i][2]*Math.cos(bmx);
                    punkte[i][1]=temp1;
                    punkte[i][2]=temp2;
                }

                //Rotation um Parallele der Y-Achse durch das Rotationszentrum
                if (bmy!=0){
                    temp0=punkte[i][0]*Math.cos(bmy)+punkte[i][2]*Math.sin(bmy);
                    temp2=-punkte[i][0]*Math.sin(bmy)+punkte[i][2]*Math.cos(bmy);
                    punkte[i][0]=temp0;
                    punkte[i][2]=temp2;
                }

                //Rotation um Parallele der Z-Achse durch das Rotationszentrum
                if (bmz!=0){
                    temp0=punkte[i][0]*Math.cos(bmz)-punkte[i][1]*Math.sin(bmz);
                    temp1=punkte[i][0]*Math.sin(bmz)+punkte[i][1]*Math.cos(bmz);
                    punkte[i][0]=temp0;
                    punkte[i][1]=temp1;
                }
            }
            A=punkte[0];
            B=punkte[1];
            C=punkte[2];
            t1.getTriangle()[0].setPoint(A[0],A[1],A[2]);
            t1.getTriangle()[1].setPoint(B[0],B[1],B[2]);
            t1.getTriangle()[2].setPoint(C[0],C[1],C[2]);
        }
        redraw();
    }


    public void printkoordinaten(ArrayList<Triangle> triangleList){
        Triangle t1;
        double x=triangleList.size();
        for (int i=0;i<x;i++){
            t1=triangleList.get(i);
            String dreieckkoordinaten="";
            for (int j=0;j<3;j++){
                dreieckkoordinaten+=String.valueOf(t1.getTriangle()[j].getPoint()[0])+",";
                dreieckkoordinaten+=String.valueOf(t1.getTriangle()[j].getPoint()[1])+",";
                dreieckkoordinaten+=String.valueOf(t1.getTriangle()[j].getPoint()[2])+" | ";
            }
            System.out.println(dreieckkoordinaten);
        }
    }
}
 /*
        Polygon poly = new Polygon();
        poly.getPoints().addAll(new Double[]{
                0.0,0.0,
                50.0,25.0,
                25.0,50.0});
        canvaspane.getChildren().add(poly);
        */
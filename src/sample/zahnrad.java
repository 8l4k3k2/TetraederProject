package sample;

//import sun.font.TrueTypeFont;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jonathan on 18.02.16.
 */
public class zahnrad {
    ///@cond
    static ArrayList<Triangle> triangleList = new ArrayList<Triangle>();
    static ArrayList<Double> koordinaten=new ArrayList<Double>();
    ///@endcond

    public static ArrayList<Triangle> einlesen() throws IOException{
        FileReader fr = new FileReader("TestRad.inc");
        BufferedReader br = new BufferedReader(fr);
        String z1="";

        while (z1!=null) {
            z1 =br.readLine();
            if (z1!=null)
                stringtoobject(z1);
        }
        br.close();
        //System.out.println();
        //System.out.println(Arrays.toString(triangleList.get(2).getTriangle()[1].getPoint()));
        return triangleList;

    }
    public static void stringtoobject(String zeile) {
        String punkt, koordinate;
        koordinaten.clear();
        for (int i = 0; i < 3; i++) {
            punkt = zeile.substring(zeile.indexOf("<") + 1, zeile.indexOf(">"));
            punkt = punkt + ",";
            zeile = zeile.substring(zeile.indexOf(">") + 1, zeile.length());
            for (int j = 0; j < 3; j++) {
                koordinate = punkt.substring(0, punkt.indexOf(","));
                punkt = punkt.substring(punkt.indexOf(",") + 1, punkt.length());
                double d_koordinate = Double.parseDouble(koordinate);
                koordinaten.add(d_koordinate);
            }


        }
        /*
        String asd="";
        for (int j0=0;j0<9;j0++){
            asd+=String.valueOf(koordinaten.get(j0));
            if (j0==3 || j0==6) {asd+="|";}
            else if(j0!=8){asd+=",";}
        }
        System.out.println(asd);
        */
        Triangle t1= new Triangle();
        for (int k=0;k<3;k++) {
            t1.getTriangle()[k].setPoint(koordinaten.get(3 * k), koordinaten.get(3 * k + 1), koordinaten.get(3 * k + 2));
        }
        triangleList.add(t1);

    }

}

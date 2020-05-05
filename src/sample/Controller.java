package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.io.IOException;

/**
 * Klasse zur Verwaltung der GUI, simple Berechnungen,
 * sowie Erzeugung der Tetraeder Objekte
 */
public class Controller implements Initializable{
    Tetraeder t1 = new Tetraeder();

    berechnungen cls_berechnen = new berechnungen();
    zahnradzeichnen cls_zahnrad = new zahnradzeichnen();

    ///@cond
    @FXML
        private Line l1,l2;

    @FXML
        private RadioButton radio01,radio02, radio11, radio12;

    @FXML
            private TextField
            text_Ax,text_Bx,text_Cx,text_Dx,
            text_Ay,text_By,text_Cy,text_Dy,
            text_Az,text_Bz,text_Cz,text_Dz,
            translation_x,translation_y,translation_z,
            skalierung_textfield,
            rotationx,rotationy,rotationz,
            rotationpktx, rotationpkty,rotationpktz;
    @FXML
            private AnchorPane canvaspane;

    ///@endcond

    /**
     * Ließt Koordinaten aus
     *
     * Ließt die Koordinaten aus den Textfeldern aus und weist den einzelnen
     * Point Objekten des Tetraeder Objekts die Koordinaten zu.
     */
    public void readcoordinates() {
        Double az =Double.parseDouble(text_Az.getText());
        Double bz =Double.parseDouble(text_Bz.getText());
        Double cz =Double.parseDouble(text_Cz.getText());
        Double dz =Double.parseDouble(text_Dz.getText());

        Double ax =Double.parseDouble(text_Ax.getText());
        Double bx =Double.parseDouble(text_Bx.getText());
        Double cx =Double.parseDouble(text_Cx.getText());
        Double dx =Double.parseDouble(text_Dx.getText());

        Double ay =Double.parseDouble(text_Ay.getText());
        Double by =Double.parseDouble(text_By.getText());
        Double cy =Double.parseDouble(text_Cy.getText());
        Double dy =Double.parseDouble(text_Dy.getText());

        t1.getTetraeder()[0].setPoint(ax,ay,az);
        t1.getTetraeder()[1].setPoint(bx,by,bz);
        t1.getTetraeder()[2].setPoint(cx,cy,cz);
        t1.getTetraeder()[3].setPoint(dx,dy,dz);

        redraw();
    }

    /**
     * Zeichnen Des Tetraeders
     *
     * Löschen aller Objekte von der Zeichenfläche.
     * Neu zeichnen_kavalier im Standart Schrägbildformat oder
     * mithilfe perspektivischer Projektion.
     */
    public void redraw(){
        canvaspane.getChildren().clear();
        if (radio01.isSelected()){
            zeichnen_kavalier();}
        else{PerspektivischeProjektion();}
    }

    /**
    public ArrayList<Triangle> depthsort(ArrayList<Triangle> dreiecke) {

            for (int i0=dreiecke.size();i0>0;i0--){
                for (int i=0;i<i0;i++) {
                    if (i!=i0){
                        
                        //z werte überschneidung
                        double [] a1=dreiecke.get(i0).getTriangle()[0].getPoint();
                        double [] a2=dreiecke.get(i0).getTriangle()[1].getPoint();
                        double [] a3=dreiecke.get(i0).getTriangle()[2].getPoint();
                        double [] b1=dreiecke.get(i).getTriangle()[0].getPoint();
                        double [] b2=dreiecke.get(i).getTriangle()[1].getPoint();
                        double [] b3=dreiecke.get(i).getTriangle()[2].getPoint();
                        
                        double [] ax={a1[0],a2[0],a3[0]};
                        double [] ay={a1[1],a2[1],a3[1]};
                        double [] az={a1[2],a2[2],a3[2]};
                        double [] bx={b1[0],b2[0],b3[0]};
                        double [] by={b1[1],b2[1],b3[1]};
                        double [] bz={b1[2],b2[2],b3[2]};

                        if (max(az)<=min(bz)); //wenn maximum der z von allen a vor allen b liegt

                        //else if (min(az)>=max(bz)){ //wenn maximum der z von allen a hinter allen b liegt
                        //    break;
                        //}
                        else{
                            if (min(ax)>=max(bx) || max(ax)<=min(bx) || min(ay)>=max(by) || max(ay)<=min(by) ); //wenn sich x und y werte von a und b nicht überschneiden

                            else if (min(az)>=max(bz)){break;} //wenn maximum der z von allen a hinter allen b liegt

                            else{ //nach schnittpunkten suchen
                                schnittpunkttriangles S = new schnittpunkttriangles(a1,a2,a3,b1,b2,b3);
                                if (!S.schnittcheck); //wenn es keinen schnittpunkt gibt
                                else {//wenn es einen schnittpunkt gibt
                                    if (S.AvorB); //wenn a vor b
                                    else {break;}
                                }


                            }
                        }
                        //if (i==dreiecke.length-1){

                        //}
                        
                        
                    }
                }
                
            }
        return dreiecke;
    }
     **/


    public double max(double[] arr){
        if (arr[0]>=arr[1] && arr[0]>=arr[2]) {return arr[0];}
        else if (arr[1]>=arr[0] && arr[1]>=arr[2]) {return arr[1];}
        else {return arr[2];}        
    }
    public double min(double[] arr){
        if (arr[0]<=arr[1] && arr[0]<=arr[2]) {return arr[0];}
        else if (arr[1]<=arr[0] && arr[1]<=arr[2]) {return arr[1];}
        else {return arr[2];}
    }

    /**
     *Modifiziert die Koordinaten des Tetraeder Objekts im Sinne der Kavaliersperspektive
     * und ruft die tetraederzeichnen() Funktion auf
     */
    public void zeichnen_kavalier() {
        /**
         * Abrufen der Koordinaten aus den einzelnen
         * Point Objekten des Objekts Tetraeder.
         */
        double[] A = t1.getTetraeder()[0].getPoint();
        double[] B = t1.getTetraeder()[1].getPoint();
        double[] C = t1.getTetraeder()[2].getPoint();
        double[] D = t1.getTetraeder()[3].getPoint();

        /**
         * Aufrufen der Methode sortieren
         */
        double[][] sP = cls_berechnen.sortieren(A, B, C, D);

        A = sP[0];
        B = sP[1];
        C = sP[2];
        D = sP[3];

        /**Wenn alle z Koordinaten gleich sind, ist es kein Tetraeder. */
        if (A[2] == D[2] || (A[2]==B[2] && C[2]==D[2])) {
            System.out.println("kein Tetraeder");
            return;
        }

        /** Transformiert x,y,z Koordinaten zu x,y Koordinaten */
        double ax, ay, bx, by, cx, cy, dx, dy;
        ax = (A[0] + (A[2] / 2));
        ay = (A[1] + (A[2] / 2));
        bx = (B[0] + (B[2] / 2));
        by = (B[1] + (B[2] / 2));
        cx = (C[0] + (C[2] / 2));
        cy = (C[1] + (C[2] / 2));
        dx = (D[0] + (D[2] / 2));
        dy = (D[1] + (D[2] / 2));

        tetraederzeichnen(ax, ay, bx, by, cx, cy, dx, dy);
    }

    /**
     * Zeichnet die (nach Kavaliersperspektive oder
     * perspektivischer Projektion) modifizierten Koordinaten des Tetraeder Objekts
     *
     * @param ax,...,dy Koordinaten (x|y-Format) der Punkte des Tetraeder Objekts
     */
     public void tetraederzeichnen(double ax,double ay,double bx,double by,double cx,double cy,double dx,double dy){
        double[][] arr_koordinaten_2d ={{ax,ay},{bx,by},{cx,cy},{dx,dy}};
        /**
         * Aufruf der Methode gestricheltodernicht
         *
         * Überprüft, welche Strecken von Punkt D sichtbar sind oder nicht.
         */

        boolean[] strichelnodernicht = cls_berechnen.gestricheltodernicht(arr_koordinaten_2d);

         /**Skaliert die Koordinaten zum Pixelformat mit Koordinatenursprung
          * ca in der Mitte der Zeichenfläche*/
         int sw=9; //skalierungswert
        ax= (sw+ax)*50;
        ay=(sw-ay)*50;
        bx= (sw+bx)*50;
        by=(sw-by)*50;
        cx= (sw+cx)*50;
        cy=(sw-cy)*50;
        dx= (sw+dx)*50;
        dy=(sw-dy)*50;

         /**
          * Erstellt Linienobjekte für die Kanten des Tetraeder Objekts...
          */
         Line line1 = new Line();
        Line line2 = new Line();
        Line line3 = new Line();
        Line line4 = new Line();
        Line line5 = new Line();
        Line line6 = new Line();

         /**
          * ... weißt ihnen die Koordinaten zu...
          */
         line1.setStartX(ax);
        line1.setStartY(ay);
        line1.setEndX(bx);
        line1.setEndY(by);

        line2.setStartX(ax);
        line2.setStartY(ay);
        line2.setEndX(cx);
        line2.setEndY(cy);

        line3.setStartX(bx);
        line3.setStartY(by);
        line3.setEndX(cx);
        line3.setEndY(cy);
        
        line4.setStartX(dx);
        line4.setStartY(dy);
        line4.setEndX(ax);
        line4.setEndY(ay);

        line5.setStartX(dx);
        line5.setStartY(dy);
        line5.setEndX(bx);
        line5.setEndY(by);

        line6.setStartX(dx);
        line6.setStartY(dy);
        line6.setEndX(cx);
        line6.setEndY(cy);
        
        if(!strichelnodernicht[0]){
            line4.getStrokeDashArray().addAll(5d,5d);}

        if(!strichelnodernicht[1]){
            line5.getStrokeDashArray().addAll(5d,5d);}

        if(!strichelnodernicht[2]){
            line6.getStrokeDashArray().addAll(5d,5d);}

         /**
          * ... und fügt die Linien Objekte der Zeichenfläche hinzu
          */
        canvaspane.getChildren().add(line1);
        canvaspane.getChildren().add(line2);
        canvaspane.getChildren().add(line3);
        canvaspane.getChildren().add(line4);
        canvaspane.getChildren().add(line5);
        canvaspane.getChildren().add(line6);

         //zeichnet den Koordinatenursprung neu
         canvaspane.getChildren().add(l1);
         canvaspane.getChildren().add(l2);
    }

    /**
     * Initialisierungsfunktion
     *
     * Setzt Startwerte für die Textfelder und Radiobuttons und
     * erstellt eine Togglegroup für die Radiobuttons.
     */
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){

        text_Ax.setText("-4");
        text_Ay.setText("-1");
        text_Az.setText("0");

        text_Bx.setText("2");
        text_By.setText("0");
        text_Bz.setText("1");

        text_Cx.setText("-2");
        text_Cy.setText("-1");
        text_Cz.setText("3");

        text_Dx.setText("-2");
        text_Dy.setText("3");
        text_Dz.setText("2");

        translation_x.setText("0");
        translation_y.setText("0");
        translation_z.setText("0");

        skalierung_textfield.setText("100");

        rotationx.setText("0");
        rotationy.setText("0");
        rotationz.setText("0");
        rotationpktx.setText("0");
        rotationpkty.setText("0");
        rotationpktz.setText("0");

        final ToggleGroup tg1 = new ToggleGroup();
        radio01.setSelected(true);
        radio01.setToggleGroup(tg1);
        radio02.setToggleGroup(tg1);

        final ToggleGroup tg2 = new ToggleGroup();
        radio11.setToggleGroup(tg2);
        radio12.setToggleGroup(tg2);
        radio11.setSelected(true);

        rotationpktx.setDisable(true);
        rotationpkty.setDisable(true);
        rotationpktz.setDisable(true);

        zahnradbutton.setDisable(true);
    }

    /**
     * Methode zum verschieben des Tetraeders
     *
     * Verschiebt in richtung des Vektors x|y|z dessen Koordinaten
     * aus dem entsprechenden Textfeldern ausgelesen werden.
     *
     * Weisst den einzelnen Point Objekten des Tetraeder Objekts
     * modifizierte Koordinaten im Sinne der Translation zu.
     *
     * Ruft die redraw() Funktion auf.
     */
    @FXML
    public void translation(){
        double x = Double.parseDouble(translation_x.getText());
        double y = Double.parseDouble(translation_y.getText());
        double z = Double.parseDouble(translation_z.getText());

        double[] A = t1.getTetraeder()[0].getPoint();
        double[] B = t1.getTetraeder()[1].getPoint();
        double[] C = t1.getTetraeder()[2].getPoint();
        double[] D = t1.getTetraeder()[3].getPoint();

        t1.getTetraeder()[0].setPoint(A[0]+x,A[1]+y,A[2]+z);
        t1.getTetraeder()[1].setPoint(B[0]+x,B[1]+y,B[2]+z);
        t1.getTetraeder()[2].setPoint(C[0]+x,C[1]+y,C[2]+z);
        t1.getTetraeder()[3].setPoint(D[0]+x,D[1]+y,D[2]+z);

        redraw();
    }

    /**
     * Methode zur Skalierung des Tetraeders
     *
     * Weisst den einzelnen Point Objekten des Tetraeder Objekts
     * modifizierte Koordinaten im Sinne der Skalierung zu.
     *
     * Ruft die tetraederzeichnen() Funktion auf
     */
    public void skalierung(){
        double scale = Double.parseDouble(skalierung_textfield.getText());
        scale = scale/100;

        double[] A = t1.getTetraeder()[0].getPoint();
        double[] B = t1.getTetraeder()[1].getPoint();
        double[] C = t1.getTetraeder()[2].getPoint();
        double[] D = t1.getTetraeder()[3].getPoint();

        t1.getTetraeder()[0].setPoint(A[0]*scale,A[1]*scale,A[2]*scale);
        t1.getTetraeder()[1].setPoint(B[0]*scale,B[1]*scale,B[2]*scale);
        t1.getTetraeder()[2].setPoint(C[0]*scale,C[1]*scale,C[2]*scale);
        t1.getTetraeder()[3].setPoint(D[0]*scale,D[1]*scale,D[2]*scale);

        redraw();
    }

    /**
     * Modifiziert die Koordinaten des Tetraeder Objekts im Sinne der perspektivischen Projektion
     * und ruft redraw() auf
     */
    public void PerspektivischeProjektion(){
        /**
         * Abrufen der Koordinaten aus den einzelnen
         * Point Objekten des Objekts Tetraeder.
         */
        double[] A = t1.getTetraeder()[0].getPoint();
        double[] B = t1.getTetraeder()[1].getPoint();
        double[] C = t1.getTetraeder()[2].getPoint();
        double[] D = t1.getTetraeder()[3].getPoint();

        /*
         * Aufrufen der Methode sortieren
         */
        double[][] sP = cls_berechnen.sortieren(A, B, C, D);

        A = sP[0];
        B = sP[1];
        C = sP[2];
        D = sP[3];

        /*Wenn alle z Koordinaten gleich sind, ist es kein Tetraeder. */
        if (A[2] == D[2]) {
            System.out.println("kein Tetraeder");
            return;
        }

        //Entfernung vom 'Center of projection' zur 'Projectoin plane'
        double d=5;

        /*
         * Transformiert x|y|z Koordinaten in x|y Koordinaten
         * mithilfer der perspektivischen Projektion
         */

        A[0]=A[0]/((A[2]+d)/d);
        A[1]=A[1]/((A[2]+d)/d);
        B[0]=B[0]/((B[2]+d)/d);
        B[1]=B[1]/((B[2]+d)/d);
        C[0]=C[0]/((C[2]+d)/d);
        C[1]=C[1]/((C[2]+d)/d);
        D[0]=D[0]/((D[2]+d)/d);
        D[1]=D[1]/((D[2]+d)/d);


        double ax, ay, bx, by, cx, cy, dx, dy;
        ax=A[0];
        ay=A[1];
        bx=B[0];
        by=B[1];
        cx=C[0];
        cy=C[1];
        dx=D[0];
        dy=D[1];

        tetraederzeichnen(ax, ay, bx, by, cx, cy, dx, dy);
    }

    /**
     * Deaktiviert Textfeld, wenn 'um den Ursprung' ausgewählt wird
     * und aktiviert es wieder wenn nicht.
     */
    public void radiolistener(){
        if (radio11.isSelected()){
            rotationpktx.setDisable(true);
            rotationpkty.setDisable(true);
            rotationpktz.setDisable(true);
        }
        if (radio12.isSelected()){
            rotationpktx.setDisable(false);
            rotationpkty.setDisable(false);
            rotationpktz.setDisable(false);
        }

        double[] A = t1.getTetraeder()[0].getPoint();
        double[] B = t1.getTetraeder()[1].getPoint();
        double[] C = t1.getTetraeder()[2].getPoint();
        double[] D = t1.getTetraeder()[3].getPoint();
    }

    /**
     * Rotiert den Tetraeder um seinen Mittelpunkt oder um einen beliebiegen Punkt
     *
     */
    public void rotation(){

        double x=Double.parseDouble(rotationx.getText());
        double y=Double.parseDouble(rotationy.getText());
        double z=Double.parseDouble(rotationz.getText());
        if (checkbox.isSelected()){
            cls_zahnrad.rotieren(x,y,z);
            return;
        }
        Triangle[] dreiecke = t1.getFlaechen();
        x=2*Math.PI/360*x;
        y=2*Math.PI/360*y;
        z=2*Math.PI/360*z;
        double[] A,B,C;

        double pktx=0,pkty=0,pktz=0;

        if (radio12.isSelected()){
            pktx = Double.parseDouble(rotationpktx.getText());
            pkty = Double.parseDouble(rotationpkty.getText());
            pktz = Double.parseDouble(rotationpktz.getText());
        }

        for (Triangle i :dreiecke){
            for (int j=0; j<3;j++){
                double xtemp,ytemp,ztemp;
                xtemp=i.getTriangle()[j].getPoint()[0]-pktx;
                ytemp=i.getTriangle()[j].getPoint()[1]-pkty;
                ztemp=i.getTriangle()[j].getPoint()[2]-pktz;
                i.getTriangle()[j].setPoint(xtemp,ytemp,ztemp);
            }
        }

        for (Triangle t1 : dreiecke){
            A=t1.getTriangle()[0].getPoint();
            B=t1.getTriangle()[1].getPoint();
            C=t1.getTriangle()[2].getPoint();
            double[][]punkte={A,B,C};

            for (int i=0; i<punkte.length; i++){
                double temp0,temp1,temp2;

                //Rotation um Parallele der X-Achse durch das Rotationszentrum
                if (x!=0){
                    temp1=punkte[i][1]*Math.cos(x)-punkte[i][2]*Math.sin(x);
                    temp2=punkte[i][1]*Math.sin(x)+punkte[i][2]*Math.cos(x);
                    punkte[i][1]=temp1;
                    punkte[i][2]=temp2;
                }

                //Rotation um Parallele der Y-Achse durch das Rotationszentrum
                if (y!=0){
                    temp0=punkte[i][0]*Math.cos(y)+punkte[i][2]*Math.sin(y);
                    temp2=-punkte[i][0]*Math.sin(y)+punkte[i][2]*Math.cos(y);
                    punkte[i][0]=temp0;
                    punkte[i][2]=temp2;
                }

                //Rotation um Parallele der Z-Achse durch das Rotationszentrum
                if (z!=0){
                    temp0=punkte[i][0]*Math.cos(z)-punkte[i][1]*Math.sin(z);
                    temp1=punkte[i][0]*Math.sin(z)+punkte[i][1]*Math.cos(z);
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
        for (Triangle i :dreiecke){
            for (int j=0; j<3;j++){
                double xtemp,ytemp,ztemp;
                xtemp=i.getTriangle()[j].getPoint()[0]+pktx;
                ytemp=i.getTriangle()[j].getPoint()[1]+pkty;
                ztemp=i.getTriangle()[j].getPoint()[2]+pktz;
                i.getTriangle()[j].setPoint(xtemp,ytemp,ztemp);
            }
        }
        redraw();
        /*
        System.out.println("A "+Arrays.toString(t1.getTetraeder()[0].getPoint()));
        System.out.println("B "+Arrays.toString(t1.getTetraeder()[1].getPoint()));
        System.out.println("C "+Arrays.toString(t1.getTetraeder()[2].getPoint()));
        System.out.println("D "+Arrays.toString(t1.getTetraeder()[3].getPoint()));
        System.out.println(Arrays.toString(Mittelpunkt));
        */
    }

    /*######################################################*/
    /*##Zahnrad#############################################*/
    /*######################################################*/

    @FXML
    private CheckBox checkbox;
    @FXML
    private Button zeichnenbutton,zahnradbutton,translation_button,skalierungsbutton;

    public void zahnradchecklistener(){
        canvaspane.getChildren().clear();
        if (checkbox.isSelected()){
            text_Ax.setDisable(true);
            text_Ay.setDisable(true);
            text_Az.setDisable(true);
            text_Bx.setDisable(true);
            text_By.setDisable(true);
            text_Bz.setDisable(true);
            text_Cx.setDisable(true);
            text_Cy.setDisable(true);
            text_Cz.setDisable(true);
            text_Dx.setDisable(true);
            text_Dy.setDisable(true);
            text_Dz.setDisable(true);
            zeichnenbutton.setDisable(true);
            zahnradbutton.setDisable(false);
            radio01.setDisable(true);
            radio02.setDisable(true);
            translation_x.setDisable(true);
            translation_y.setDisable(true);
            translation_z.setDisable(true);
            skalierung_textfield.setDisable(true);
            translation_button.setDisable(true);
            skalierungsbutton.setDisable(true);
            radio12.setDisable(true);
            if (radio12.isSelected()){radio11.setSelected(true);}
        }
        else{
            radio01.setDisable(false);
            radio02.setDisable(false);
            text_Ax.setDisable(false);
            text_Ay.setDisable(false);
            text_Az.setDisable(false);
            text_Bx.setDisable(false);
            text_By.setDisable(false);
            text_Bz.setDisable(false);
            text_Cx.setDisable(false);
            text_Cy.setDisable(false);
            text_Cz.setDisable(false);
            text_Dx.setDisable(false);
            text_Dy.setDisable(false);
            text_Dz.setDisable(false);
            zeichnenbutton.setDisable(false);
            zahnradbutton.setDisable(true);
            translation_x.setDisable(false);
            translation_y.setDisable(false);
            translation_z.setDisable(false);
            skalierung_textfield.setDisable(false);
            translation_button.setDisable(false);
            skalierungsbutton.setDisable(false);
            radio12.setDisable(false);

        }
    }
    public void drawzahnrad()throws IOException{
        cls_zahnrad.draw(canvaspane);

    }
}
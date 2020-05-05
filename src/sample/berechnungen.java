package sample;

/**
 * Klasse für mathematische Berechnungen/Algorithmen
 */
public class berechnungen {
    /**
     * Sortierfunktion
     *
     * Funktion sortiert die Punkte nach der z Koordinate,
     * also der Punkt am weitesten vorne zuerst und als letztes
     * der hinterste Punkt
     * @param A,B,C,D Arrays von Koordinaten
     * @return sortiertes Array mit Arrays von Koordinaten
     */
    public double[][] sortieren(double[] A,double[] B,double[] C,double[] D){

        double[][] sort={A,B,C,D};
        double[] help;
        
        for (int i=0;i<4;i++){
            for (int j=i;j<4;j++){
                if (sort[i][2]>sort[j][2]){
                    help = sort[i];
                    sort[i]=sort[j];
                    sort[j]=help;
                }}}
        return sort;

    }

    /**
     * Funktion gibt an ob die geraden zu dem hintersten Punkt durchgezogen oder gestrichelt
     * gezeichnet werden sollen
     * @param arr_koordinaten_2d Array mit Arrays von Koordinaten
     * @return Array mit bool'schen Variablen
     */
    public boolean[] gestricheltodernicht(double[][] arr_koordinaten_2d){
        //False=gestrichelt,True=durchgezogen
        boolean ad,bd,cd;
        //Punkte mit Koordinaten
        double[] A,B,C,D;
        A= arr_koordinaten_2d[0];
        B= arr_koordinaten_2d[1];
        C= arr_koordinaten_2d[2];
        D= arr_koordinaten_2d[3];

        //Koordinaten der Punkte
        double x1,y1,x2,y2,x3,y3,x4,y4;
        x1=A[0];
        y1=A[1];
        x2=B[0];
        y2=B[1];
        x3=C[0];
        y3=C[1];
        x4=D[0];
        y4=D[1];

        /**Überprüfen ob (der hinterste Punkt) D im Dreieck ABC liegt.
         * wenn ja, dann sind alle Strecken zu D nicht sichtbar
         * wenn nicht, dann wird schnittcheck() aufgerufen.
         */
        double s,r;
        s=(-x1*y2+x1*y4+x2*y1-x2*y4-x4*y1+x4*y2)/(-x1*y2+x1*y3+x2*y1-x2*y3-x3*y1+x3*y2);
        r=(s*(-x1+x3)+x1-x4)/(x1-x2);

        if (0<=s && s<=1 && 0<=r && r<=1 && 0<=(s+r) && (s+r)<=1){
            ad=false;bd=false;cd=false;
        }
        else {
            ad=schnittcheck(A,D,B,C);
            bd=schnittcheck(B,D,A,C);
            cd=schnittcheck(C,D,A,B);
        }
        return new boolean[] {ad,bd,cd} ;
    }

    /**
     * Funktion überprüft, ob sich die Strecken zwischen den Punkten p1-p2 und p3-p4 nicht schneiden
     * @param p1,p2,p3,p4 Arrays mit Koordinaten der Punkte
     * @return bool'sche Variable, ob Strecke p1-p2 sichtbar ist. False=Schneiden sich
     */
    public boolean schnittcheck(double[] p1,double[]p2, double[] p3, double p4[]){
        //wenn parallel
        if ((p1[0]==p2[0] && p3[0]==p4[0]) || (p1[1]==p2[1] && p3[1]==p4[1])){return true;}

        double sp;
        //wenn p1p2 senkrecht ist
        if (p1[0]==p2[0]){
            sp=linearegleichung(p3,p4)[0]*p1[0]+linearegleichung(p3,p4)[1]; //y berechnen mit y=mx+n
            if (p1[1]>p2[1]){
                if (p3[1]>p4[1])    {return ((sp>=p1[1] || sp<=p2[1]) || (sp>=p3[1] || sp<=p4[1]));}
                else                {return ((sp>=p1[1] || sp<=p2[1]) || (sp<=p3[1] || sp>=p4[1]));}
            }
            else{
                if (p3[1]>p4[1])    {return ((sp<=p1[1] || sp>=p2[1]) || (sp>=p3[1] || sp<=p4[1]));}
                else                {return ((sp<=p1[1] || sp>=p2[1]) || (sp<=p3[1] || sp>=p4[1]));}
            }
        }
        double[] mn1=linearegleichung(p1,p2);
        
        //wenn p2,p3 senkrecht ist
        if (p3[0]==p4[0]){
            sp=mn1[0]*p3[0]+mn1[1];
            if (p1[1]>p2[1]){
                if (p3[1]>p4[1])    {return ((sp>=p1[1] || sp<=p2[1]) || (sp>=p3[1] || sp<=p4[1]));}
                else                {return ((sp>=p1[1] || sp<=p2[1]) || (sp<=p3[1] || sp>=p4[1]));}
            }
            else{
                if (p3[1]>p4[1])    {return ((sp<=p1[1] || sp>=p2[1]) || (sp>=p3[1] || sp<=p4[1]));}
                else                {return ((sp>=p1[1] || sp<=p2[1]) || (sp<=p3[1] || sp>=p4[1]));}
            }

        }

        double[] mn2=linearegleichung(p3,p4);
        double x = schnittpunkt(mn1,mn2)[0];
        if (p1[0]>p2[0]){
            if (p3[0]>p4[0])    {return ((x>=p1[0] || x<=p2[0]) || (x>=p3[0] || x<=p4[0]));}
            else                {return ((x>=p1[0] || x<=p2[0]) || (x<=p3[0] || x>=p4[0]));}
            }
        else{
            if (p3[0]>p4[0])    {return ((x<=p1[0] || x>=p2[0]) || (x>=p3[0] || x<=p4[0]));}
            else                {return ((x<=p1[0] || x>=p2[0]) || (x<=p3[0] || x>=p4[0]));}
            }
    }

    /**
     * Funktion berechnet den Schnittpunkt der Graphen zweier linearer Funktionen
     * @param p1D,p2D Arrays mit jeweils dem Anstieg sowie des absoluten Glieds einer linearen Funktion [m,n]
     * @return Array mit Koordinaten des Schnittpunkts
     */
    public double[] schnittpunkt(double[] p1D,double[] p2D){
        double x,y;
        x= (p2D[1]-p1D[1])/(p1D[0]-p2D[0]);
        y=p1D[0]*x+p1D[1];
        return new double[] {x,y};
    }

    /**
     * Berechnet den Anstieg sowie das absolute Glied
     * einer linearen Funktion aus zwei Punkten
     * @param P1,P2 Array mit Koordinaten der zwei Punkte
     * @return Array mit Anstieg und absoluten Glied
     */
    public double[] linearegleichung(double[] P1, double[] P2){

        double m = (P1[1]-P2[1])/(P1[0]-P2[0]);
        double n = P2[1]-m*P2[0];
        return new double[] {m,n};
    }


}

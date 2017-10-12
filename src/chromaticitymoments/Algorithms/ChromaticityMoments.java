/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chromaticitymoments.Algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author frank.aguilar
 */
public class ChromaticityMoments implements Serializable{
    private ArrayList<Integer> Mt;
    private ArrayList<Integer> Md;

    private static final long serialVersionUID = 1L;


    private int[][] orderOfMoments={{0,0,1,1,2,0},{0,1,0,1,0,2}};

    public ChromaticityMoments() {
        Mt=new ArrayList<Integer>();
        Md=new ArrayList<Integer>();
    }

    public ChromaticityMoments(ArrayList<Integer> Mt, ArrayList<Integer> Md) {
        this.Mt = Mt;
        this.Md = Md;
    }
    
    /**
     * Calculates the set of chromaticity moments of an image
     * @param R
     * @param G
     * @param B
     * @param Xs
     * @param Ys 
     */
    public void calculateCM(int[][] R, int[][] G,int[][] B, int Xs,int Ys){
        ArrayList<Double> XChromaticities=new ArrayList<Double>();
        ArrayList<Double> YChromaticities=new ArrayList<Double>();

        double X,Y,Z,x,y;
        
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                X=0.607*R[i][j]+0.174*G[i][j]+0.2*B[i][j];
                Y=0.299*R[i][j]+0.587*G[i][j]+0.114*B[i][j];
                Z=0.066*G[i][j]+1.111*B[i][j];
                
                x=X/(X+Y+Z);
                y=Y/(X+Y+Z);
                
                XChromaticities.add(x);
                YChromaticities.add(y);
            }           
        }
        
        int[][] d=calculateDistribution(XChromaticities, YChromaticities, Xs, Ys);
        int[][] t=calculateTraceFromDistribution(d);
        
        Md=Approximate(d, Xs, Ys);
        Mt=Approximate(t, Xs, Ys);
        
    }
    
    /**
     * Calculates the two-dimensional distribution
     * @param lx
     * @param ly
     * @param Xs
     * @param Ys
     * @return 
     */
    public int[][] calculateDistribution(ArrayList<Double> lx,ArrayList<Double> ly, int Xs,int Ys){
        double maxX=Collections.max(lx);
        double maxY=Collections.max(ly);
        double minX=Collections.min(lx);
        double minY=Collections.min(ly);
        
        double rangeX=1.;//maxX-minX;
        double rangeY=1.;//maxY-minY;
        
        int qX,qY;
        
        int [][] D=new int[Xs][Ys];
        
        for(int i=0;i<lx.size();i++){
//            qX=(int)Math.round(((lx.get(i)-minX)*(Xs-1)/rangeX));
//            qY=(int)Math.round(((ly.get(i)-minY)*(Ys-1)/rangeY));
            qX=(int)Math.round(((lx.get(i)-0.)*(Xs-1)/rangeX));
            qY=(int)Math.round(((ly.get(i)-0.)*(Ys-1)/rangeY));

            D[qX][qY]++;
        }
        return D;
    }
    
    
    public int[][] calculateTraceFromDistribution(int [][]d)
    {
        int[][] t=new int[d.length][d[0].length];
        
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                if(d[i][j]>0)
                    t[i][j]=1;
                else
                    t[i][j]=0;                
            }
        }
        return t;
    }
    
    public ArrayList<Integer> Approximate(int [][] m,int Xs,int Ys){
        ArrayList<Integer> l=new ArrayList<Integer>();
        int x,y,sum;
        for (int j = 0; j < orderOfMoments[0].length; j++) {
            sum=0;    
            for(x=0;x<Xs;x++){
                    for(y=0;y<Ys;y++){
                        sum+=(Math.pow(x,orderOfMoments[0][j])*
                                Math.pow(y,orderOfMoments[1][j])*m[x][y]);
                    }
                }
            l.add(sum);
            }
        return l;
    }

    public ArrayList<Integer> getMd() {
        return Md;
    }

    public void setMd(ArrayList<Integer> Md) {
        this.Md = Md;
    }

    public ArrayList<Integer> getMt() {
        return Mt;
    }

    public void setMt(ArrayList<Integer> Mt) {
        this.Mt = Mt;
    }
    
    
}

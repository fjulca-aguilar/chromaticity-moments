/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chromaticitymoments.Algorithms;

import chromaticitymoments.Util.ImageCM;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author frank.aguilar
 */
public class Metric {
    
    public static int distance(ChromaticityMoments m1,ChromaticityMoments m2){
        int d=0;
        ArrayList<Integer> mt1=m1.getMt();
        ArrayList<Integer> md1=m1.getMd();
        
        ArrayList<Integer> mt2=m2.getMt();
        ArrayList<Integer> md2=m2.getMd();
        
        for (int i = 0; i < mt1.size(); i++) {
            d+=(Math.abs(mt1.get(i)-mt2.get(i)) +Math.abs(md1.get(i)-md2.get(i)));
        }
        
        return d;
    }
    
    public static ArrayList<ImageCM> distance(ImageCM m1,ArrayList<ImageCM> m2){
        int dist=0;
        for (ImageCM imageCM : m2) {
            dist=distance(m1,imageCM);
            imageCM.setDist(dist);        
       }
        
        Collections.sort(m2);
        return m2;
    }
    
    
}

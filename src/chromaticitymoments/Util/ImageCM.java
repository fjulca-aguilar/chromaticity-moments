/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chromaticitymoments.Util;

import chromaticitymoments.Algorithms.ChromaticityMoments;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author frank.aguilar
 */
public class ImageCM extends ChromaticityMoments implements 
        Comparable, Serializable{

    
    private String imageName;
    private int dist;

    public ImageCM(ArrayList<Integer> Mt, ArrayList<Integer> Md, String imageName) {
        super(Mt, Md);
        this.imageName = imageName;
        dist=0;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
    
    
    public int compareTo(Object t) {
        
        return (dist-((ImageCM)t).getDist());
    }
    
}

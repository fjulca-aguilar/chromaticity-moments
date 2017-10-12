/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chromaticitymoments.Util;

import chromaticitymoments.ChromaticityMomentsView;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frank
 */
public class Util {
    private static int NUM_IMAGES=167;

    public static ArrayList<String> getImageNames(){
        String[] name1={"Bark","Brick","Buildings","Clouds","Fabric","Flowers",
        "Food","Grass","Leaves","Metal","Misc","Paintings.1","Paintings.11",
        "Paintings.21","Paintings.31","Paintings.41","Sand","Stone","Terrain",
        "Tile","Water","WheresWaldo","Wood"};
        String[] name2={".0000.ppm",".0001.ppm",".0002.ppm",".0003.ppm",
            ".0004.ppm",".0005.ppm",".0006.ppm",".0007.ppm",".0008.ppm",".0009.ppm",
        ".0010.ppm",".0011.ppm",".0012.ppm",".0013.ppm",".0014.ppm",".0015.ppm",
        ".0016.ppm",".0017.ppm",".0018.ppm",".0019.ppm"};

        ArrayList<String> imgNames=new ArrayList<String>(NUM_IMAGES);

        for (int i = 0; i < name1.length; i++) {
            String string = name1[i];
            for (int j = 0; j < name2.length; j++) {
                String string1 = name2[j];
                imgNames.add(string+string1);
            }

        }

        return imgNames;
    }

    public static BufferedImage getImage(String imageName){
        BufferedImage imagem = null;
            BufferedImageBuilder b=new BufferedImageBuilder();
            PPM p;
            try {
                p = new PPM(imageName);
                Image img = p.getImage();
                imagem=b.bufferImage(img);
            } catch (IOException ex) {
                //Logger.getLogger(ChromaticityMomentsView.class.getName()).log(Level.SEVERE, null, ex);
                imagem=null;
            } catch (Exception ex) {
                //Logger.getLogger(ChromaticityMomentsView.class.getName()).log(Level.SEVERE, null, ex);
                imagem=null;
            }
         return imagem;
    }

}

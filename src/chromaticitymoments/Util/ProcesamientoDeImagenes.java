/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chromaticitymoments.Util;

/**
 *
 * @author Frank
 */
public class ProcesamientoDeImagenes {

    /** aplica Contrast stretching para mejoramiento de contraste
     * 
     * @param matrizI matriz de imagene a procesar
     * @param s1 minimo de los tonos de la matriz de procesada
     * @param s2 maximo de los tonos de la matriz de procesada
     */
    public static int[][] aplicarContrastStretching(int[][] matrizI,int s1,int s2)
    {
        int minI=Utilitarios.getMinimo(matrizI);
        int maxI=Utilitarios.getMaximo(matrizI);
        int numFilas=matrizI.length;
        int numColumnas=matrizI[0].length;
        int matrizIProcesada[][]=new int[numFilas][numColumnas];
        
        for(int i=0;i<numFilas;i++)
        {
            for(int j=0;j<numColumnas;j++)
            {
                matrizIProcesada[i][j]=(int)((matrizI[i][j]-minI)*((s1-s2)/(double)(minI-maxI))+s1);
                
            }
        }
           
        return matrizIProcesada;        
                
    }
    
   public static double[] getHistograma(int[][] matrizTonos,boolean normalizado)
   {
       double histograma[]=new double[256];
       
       for(int i=0;i<256;i++)
       {
           histograma[i]=0;
       }
       int tono=-1;
       for(int i=0;i<matrizTonos.length;i++)
           for(int j=0;j<matrizTonos[0].length;j++)
           {
               tono=matrizTonos[i][j];
               histograma[tono]+=1;
           }
       if(normalizado)
       {
           int numPixels=matrizTonos.length*matrizTonos[0].length;
           for(int i=0;i<256;i++)
           {
               histograma[i]=((double)histograma[i]/(double)numPixels);
           }
       }
       return histograma;
   }
     
   public static int [][] aplicarEcualizacionHistograma(int [][] matrizI)
   {
       int[][] matrizResultado=new int[matrizI.length][matrizI[0].length];
       double[] histograma=getHistograma(matrizI,true);
       double maxTono=255;
       double sumatoria;
       int tono;
       for(int i=0;i<matrizI.length;i++)
           for(int j=0;j<matrizI[0].length;j++)
           {
               tono=matrizI[i][j];
               sumatoria=0;
               for(int x=0;x<tono+1;x++)
               {
                   sumatoria+=histograma[x];
               }
               matrizResultado[i][j]=(int)(maxTono*sumatoria);
           }
       return matrizResultado;
       
   }
      
}

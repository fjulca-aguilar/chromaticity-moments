/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chromaticitymoments.Util;

/**
 *
 * @author anthony
 */
public class Utilitarios {

    
    public static void mostrarMatriz(int[][] matriz)
    {
        int m=matriz.length;
        int n=matriz[0].length;
        
        for(int i=0;i<m;i++)
        {
            System.out.print("[ ");
            for(int j=0;j<n;j++)        
            {
                System.out.print(matriz[i][j]);
                
                if(j<n-1)
                {
                    System.out.print(", ");
                }
                
            }
            System.out.print(" ]");
            System.out.println();
        }
    }
    
    /**
     * 
     * @param matriz
     * @return maximo valor de la matriz
     */
    public static int getMaximo(int [][] matriz)
    {
        int max=-1;
        for(int i=0;i<matriz.length;i++)
        {
            for(int j=0;j<matriz[0].length;j++)
            {
                if(matriz[i][j]>max)
                {
                    max=matriz[i][j];
                }
            }
        }
        return max;
    }
    
    /**
     * 
     * @param matriz
     * @return minimo valor d ela mtriz
     */
    public static int getMinimo(int [][] matriz)
    {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<matriz.length;i++)
        {
            for(int j=0;j<matriz[0].length;j++)
            {
                if(matriz[i][j]<min)
                {
                    min=matriz[i][j];
                }
            }
        }
        return min;
    }
}


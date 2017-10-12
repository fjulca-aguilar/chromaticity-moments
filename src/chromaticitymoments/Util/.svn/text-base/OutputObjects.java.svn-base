/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chromaticitymoments.Util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frank
 */
public class OutputObjects {


     private ObjectOutputStream output; // outputs data to file

      // allow user to specify file name
      public void openFile()
      {
         try // open file
         {
            output = new ObjectOutputStream(
               new FileOutputStream( "cmoments.m" ) );
         } // end try
         catch ( IOException ioException )
         {
            System.err.println( "Error opening file." );
         } // end catch
      } // end method openFile

      public void saveObject(Object o){
        try {
            output.writeObject(o);
        } catch (IOException ex) {
            System.err.println( "Error writing to file." );
               return;        
        }
      }

      // close file and terminate application
      public void closeFile()
      {
         try // close file
         {
            if ( output != null )
               output.close();
         } // end try
         catch ( IOException ioException )
         {
             System.err.println( "Error closing file.");
            System.exit( 1 );
        } // end catch
     } // end method closeFile
}

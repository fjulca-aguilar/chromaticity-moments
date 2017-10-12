/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chromaticitymoments.Util;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author frank
 */
public class ReadObjectInFile {
    private ObjectInputStream input;

     // enable user to select file to open
     public void openFile()
     {
        try // open file
        {
           input = new ObjectInputStream(
              new FileInputStream( "cmoments.m" ) );
        } // end try
        catch ( IOException ioException )
        {
           System.err.println( "Error opening file." );
        } // end catch
     } // end method openFile

     // read record from file
     public Object readRecords()
     {

        try // input the values from the file
        {
           while ( true )
           {
              return input.readObject();


           } // end while
        } // end try
        catch ( EOFException endOfFileException )
        {
           return null; // end of file was reached
        } // end catch
        catch ( ClassNotFoundException classNotFoundException )
        {
           System.err.println( "Unable to create object." );
        } // end catch
        catch ( IOException ioException )
        {
           System.err.println( "Error during read from file." );
        } // end catch
        return null;
     } // end method readRecords

     // close file and terminate application
     public void closeFile()
     {
        try // close file and exit
        {
           if ( input != null )
              input.close();
        } // end try
        catch ( IOException ioException )
        {
           System.err.println( "Error closing file." );
           System.exit( 1 );
        } // end catch
     } // end method

}

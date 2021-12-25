/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phrasalsearch;

import java.io.IOException;


public class PhrasalSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Phrasal_Search ps=new Phrasal_Search();
        ps.reading_it("C:\\Users\\Lenovo\\Desktop\\folders");
        String [] tokens=ps.splitt_query("anton brutus");
         ps.find_phrase(tokens, 0, 0, 0);
    }
    
}

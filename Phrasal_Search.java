package phrasalsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



public class Phrasal_Search {
   Map<String,ArrayList<Unit>>dict;
   HashMap<Integer,ArrayList<Integer>>onesolution;
    ArrayList<ArrayList<Integer>>ans=new ArrayList<>();
     ArrayList<Integer>sol = new ArrayList<>();

    public Phrasal_Search() {
        this.dict = new HashMap<>();
    
    } 
    public void reading_it(String path) throws IOException{
        
        File dir = new File(path);
        File[] files = dir.listFiles();
        // Fetching all the files
        for (File file : files) {
             int num;
            if(file.getName().charAt(1)=='0')num=10;
            else
            num = (file.getName().charAt(0))-'0';
            if(file.isFile()) {
                BufferedReader inputStream = null;
                String line;
                try {
                    inputStream = new BufferedReader(new FileReader(file));
                    while ((line = inputStream.readLine()) != null) {
                        String tmp="";
                        int idx=1;
                        for(int i=0;i<line.length();i++){
                            char ltr=line.charAt(i);
                            if(line.charAt(i)==' '||i==line.length()-1){
                              if(i==line.length()-1)tmp+=line.charAt(i);
                               if(dict.get(tmp)==null){
                                   ArrayList<Integer>pq=new ArrayList<>();
                                   ArrayList<Unit>eu=new ArrayList<>();
                                   pq.add(idx);
                                   Unit e=new Unit(num, pq);
                                   eu.add(e);
                                   dict.put(tmp,eu);
                               }else{
                                   boolean key=false;
                                   for(int j=0;j<dict.get(tmp).size();j++){
                                       if(dict.get(tmp).get(j).thekey==num){
                                           key=true;
                                           dict.get(tmp).get(j).value.add(idx);
                                       }
                                   }
                                   if(key==false){
                                       ArrayList<Integer>vu=new ArrayList<>();
                                       vu.add(idx);
                                       Unit u=new Unit(num, vu);
                                       dict.get(tmp).add(u);
                                   }
                               }
                                idx++;
                                tmp="";
                            }if((ltr>='a'&&ltr<='z')||ltr>='A'&&ltr<='Z'){
                                tmp+=ltr;
                            }
                        }
                    }
                }catch(IOException e) {
                	System.out.println(e);
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
          
        } 
    }
    public String [] splitt_query(String query){
        query=query.trim();
        String [] terms=query.split("\\s+");
        return terms;
    } 
    public void find_phrase(String[] line,int f_num,int idx,int last)throws NullPointerException{
        if(idx==line.length){
            if(sol.size()==line.length+1){
            for(int i=0;i<sol.size();i++){
                if(i==0){
                    System.out.print("exist in file No "+sol.get(i)+" : "+" ");
                }
                else{
                System.out.print(sol.get(i)+" ");
                }
            }
            System.out.println("");
                ans.add(sol);
                sol.clear();
            return;
            }
            return;
        }
       try{
       for(int i=0;i<dict.get(line[idx]).size();i++){
           if(idx==0||(idx>0&&dict.get(line[idx]).get(i).thekey==f_num)){
               for(int j=0;j<dict.get(line[idx]).get(i).value.size();j++){
                 int vals=dict.get(line[idx]).get(i).value.get(j);
                   if(idx==0||(idx>0&&vals-last==1)){
                       if(sol.size()==0)sol.add(dict.get(line[idx]).get(i).thekey);
                       sol.add(vals);
                       find_phrase(line,dict.get(line[idx]).get(i).thekey, idx+1, vals);
                   }  
               }
           }
       }
       }catch(NullPointerException e){
           System.out.println("phrase does not exist in files");
       }
  
    }
}
    


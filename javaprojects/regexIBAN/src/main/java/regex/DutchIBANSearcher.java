package regex;

/*
Copyright by Tanja Bekker 2-12-2024
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DutchIBANSearcher {
    /*
    A small program that
    - Method for conversing a relative filepath into an absolute filepath
    - Method for searching and extracting a Dutch IBAN inside of a txt file by using a BufferedReader and
      a regex pattern that allows for the swift guideline spaces.
      The regex searches for the title IBAN: and the actual IBAN value
    - Searches a Dutch IBAN inside of a String by using a simple regex pattern that only searches
      for the actual IBAN number
     */

    public static void main(String[] args) {

        try{
            // Search Dutch IBAN in String text
            String nlIBAN="NL23ABNB9933445566";
            String nlIBANwithSpaces="NL23ABNB 9933 4455 66";
            searchIBAN_In_Text(nlIBAN);
            searchIBAN_In_Text(nlIBANwithSpaces);

            // Search Dutch IBAN in file text
            String relPathToFile="src/main/resources/order.txt";
            searchIBAN_In_TextFile(relPathToFile);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public static Path getAbsolutePath_FromRelativePath(String fileRelativePath){
        /*
        Method for converting a relative filepath into an absolute filepath
         */
        String file=new File(fileRelativePath).getAbsolutePath();
        return Paths.get(file);
    }

    public static void searchIBAN_In_TextFile(String file){
        /*
          Method for searching and extracting a Dutch IBAN inside of a txt file by using a BufferedReader and
          a regex pattern that allows for the swift guideline spaces.
          The regex searches for the title IBAN: and the actual IBAN value
         */
        // Get the absolute path
        Path absPathToFile=getAbsolutePath_FromRelativePath(file);
        // Search the file in the absolute path
        try(BufferedReader br= Files.newBufferedReader(absPathToFile)){
            // Search for Dutch IBAN
            // Allows for spaces before every numberblock of iban
            String searchREGEX="^IBAN:\\s*([A-Z]{2})([0-9]{2})([A-Z]{4})(\\s*[0-9]{4}\\s*[0-9]{4}\\s*[0-9]{2})$";
            Pattern pattern= Pattern.compile(searchREGEX);
            String line=br.readLine();
            while(line!=null){
                Matcher m=pattern.matcher(line);
                if(m.find()){
                    String countryCode=m.group(1);
                    String countryCheckCode=m.group(2);
                    String bankCode=m.group(3);
                    String bankAccountNumber=m.group(4);
                    System.out.println("Countrycode is: "+countryCode);
                    System.out.println("Country checkcode is: "+countryCheckCode);
                    System.out.println("BankCode is: "+bankCode);
                    System.out.println("Bankaccount number is: "+bankAccountNumber);
                }
                line=br.readLine();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
    public static void searchIBAN_In_Text(String dummyIBAN){
        // Search for Dutch IBAN
        // Allows for space before every number
        String searchREGEX="^[A-Z]{2}[0-9]{2}[A-Z]{4}\\s*[0-9]{4}\\s*[0-9]{4}\\s*[0-9]{2}$";
        try{
            Pattern p= Pattern.compile(searchREGEX);
            if(dummyIBAN==null){
                return;
            }
            Matcher m=p.matcher(dummyIBAN);
            if(m.matches()){
                System.out.println("IBAN found");
                // removes all whitespaces from IBAN
                String removePattern=dummyIBAN.replaceAll("\\s","");
                System.out.println(removePattern);
            }else{
                System.out.println("No IBAN found");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}

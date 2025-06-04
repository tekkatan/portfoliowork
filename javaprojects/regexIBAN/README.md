# Welcome

This Github repo contains a selection of my work
including the following softwaredevelopment 
languages:

Note:
- If you are using any of my code, please add a link to me/my website/ or **this** github repo. Thumbs up for the supporting devs 


# RegexIBAN RegexIBAN

### Info
- A small simple program which searches a Dutch IBAN in a String or a txt File.
- As the title of this project already shows, this program relies on the use of Regex patterns.

### Usage
- In the main method below I've written two examples of how one can
  search for Dutch IBAN in both text files as well as in Strings.

```java

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

```

- Contains a method for converting a relative filepath into an absolute filepath.

**Source: method for converting a relative filepath into an absolute filepath**
```java

    public static Path getAbsolutePath_FromRelativePath(String fileRelativePath){
        /*
        Method for converting a relative filepath into an absolute filepath
         */
        String file=new File(fileRelativePath).getAbsolutePath();
        return Paths.get(file);
    }

```
- Contains a method for searching and extracting a Dutch IBAN inside of a txt file by using a BufferedReader and
  a regex pattern that allows for the swift guideline spaces.
  The regex searches for the title IBAN: and the actual IBAN value.

**Source: method for extracting a Dutch IBAN inside of a txt file**
```java

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

```
- Contains a method for searching a Dutch IBAN inside of a String by using a simple regex pattern that only searches
  for the actual IBAN number.

**Source: method for extracting a Dutch IBAN inside a String**
```java

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
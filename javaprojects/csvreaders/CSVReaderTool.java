package nl.cubicated.csvreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Assert;
import org.junit.Test;

public class CSVReaderTool {

	/* Example of using a BufferedReader in combination with an
	   InputStreamReader for reading csv data
	*/
	private static String readDataFromProvidedInputStream(InputStream in){
		StringBuilder res=new StringBuilder();
		try(
			BufferedReader br=new BufferedReader(new InputStreamReader(in))
		){
			String line;
			while((line=br.readLine())!=null){
				res.append(line).append("\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
			return res.toString();
		}
	@Test
	public void givenFileName_whenUsingClassPath_forDataReturn(){
		String out="12/12/12,Mike,13\n"+
		"01/04/13,Hannah,18\n"+
		"05/05/10,Emma,13\n"+
		"03/08/09,Lee, 12\n";

		InputStream in=null;
		in=CSVReaderTool.class.getResourceAsStream("data.csv");
			String data=readDataFromProvidedInputStream(in);
		Assert.assertEquals(out, data);
	}

	private static String readUTFEncodedData(InputStream file){
		StringBuilder res=new StringBuilder();
		try(
			BufferedReader br=new BufferedReader(new InputStreamReader(
			file,"UTF-8"))
		){
			String line;
			while((line=br.readLine())!=null){
				res.append(line).append("\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return res.toString();
	}

	@Test
	public void givenFileName_withSpecialCharacter_forDataReturn(){
		String out="Français, 法国人\n"+
		"Französisch,ਫ੍ਰੈਂਚ\n";
		InputStream in=null;
		in=CSVReaderTool.class.getResourceAsStream("dataUtf8.txt");
		String data=readUTFEncodedData(in);
		Assert.assertEquals(out, data); 
		
	}

}

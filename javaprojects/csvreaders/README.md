# Welcome

Note:
- If you are using any of my code, please add a link to me/my website/ or **this** github repo. Thumbs up for the supporting devs 


# CSVReaders Project

**Usage**
- Example of BufferedReaders in combination with InputStreamReaders for reading csv data.
- The test cases are included in the Executing class
- Test cases include:
1. Special Latin characters usage 
2. Asian characters usage

**Example**
```java
@Test
	public void givenFileName_withSpecialCharacter_forDataReturn(){
		String out="Français, 法国人\n"+
		"Französisch,ਫ੍ਰੈਂਚ\n";
		InputStream in=null;
		in=CSVReaderTool.class.getResourceAsStream("dataUtf8.txt");
		String data=readUTFEncodedData(in);
		Assert.assertEquals(out, data); 
		
	}
```

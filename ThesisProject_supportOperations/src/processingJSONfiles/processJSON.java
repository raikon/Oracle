package processingJSONfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;

public class processJSON {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws ParseException, IOException {

		ArrayList<String> fileInput = returnDocOfFolder("/home/oracle/workspace/ThesisProject_supportOperations/input_JSON");
		int index=1;

		for(String file : fileInput) {

			System.out.println("Analizzo il file: "+file);

			int lines = countLines(file);
			int p = preProcessing(file,lines);

			Reading r = new Reading();
			
			List<HashMap<String, String>> readJSON = r.readJSON(file,p);
			//System.out.println(readJSON);


			String filename = file.substring(file.lastIndexOf("/"), file.lastIndexOf("_"))+".txt";
			System.out.println();
			Writing elementWriting = new Writing();
			elementWriting.writeJSON(readJSON,"i"+index,filename);

			postProcessing("output/"+filename);
			index++;

		}
	}

	public static int preProcessing(String file,int lines) throws IOException {
		Path path = Paths.get(file);
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);
		BufferedReader br = new BufferedReader(new FileReader(file));
		int i=1,delete=0;
		String strLine,newLine,emptyLine="";
		while (lines!=0) {
			strLine = br.readLine();
			if(countNull(strLine)>=3) {
				content=content.replace(strLine, emptyLine);
				delete++;
			}
			else {
				newLine = strLine.replace("{", "\"p"+i+"\":{");
				content=content.replace(strLine, newLine);
				i++;
			}
			lines--;
		}
		br.close();

		content = "{\"participants\":{\n"+content;

		//delete empty line
		content = content.replaceAll("(?m)^[ \t]*\r?\n", ""); 

		content = content.replaceAll("}", "},");
		System.out.println(content.charAt(content.length()-1)+"FINE");

		if(content.endsWith(",\n"))
			content = content.substring(0,content.length() - 2);
		else if(content.endsWith(","))
			content = content.substring(0,content.length() - 1);

		content = content.concat("\n } \n }");

		Files.write(path, content.getBytes(charset));
		return i-delete-1;
	}

	public static void postProcessing(String file) throws IOException {
		Path path = Paths.get(file);
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String strLine=br.readLine();
		String emptyLine="";
		while(strLine!=null) {
			if(strLine.contains(":null")) {
				content=content.replace(strLine, emptyLine);
			}
			strLine=br.readLine();
		}
		br.close();

		content = content.replaceAll("\"Company\":","");
		content = content.replaceAll("\"FirstName\":","");
		content = content.replaceAll("\"LastName\":","");
		content = content.replaceAll("\"key\":","");
		
		content = content.replaceAll("\\{", "");
		content = content.replaceAll("}", "");
		//delete empty line
		content = content.replaceAll("(?m)^[ \t]*\r?\n", ""); 
		
		String header = "\"Company\",\"First Name\",\"Last Name\",\"Key\"";
		content = content.replace(content.substring(0, content.indexOf("\n")), header);
		
		Files.write(path, content.getBytes(charset));
	}

	public static int countNull(String line) {
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

			lastIndex = line.indexOf(":null",lastIndex);

			if(lastIndex != -1){
				count ++;
				lastIndex += ":null".length();
			}
		}
		return count;
	}

	public static int countLines(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		int lines = 0;
		while (reader.readLine() != null) 
			lines++;
		reader.close();
		return lines;
	}

	public static ArrayList<String> returnDocOfFolder(String pathFolder) {
		ArrayList<String> listFile = new ArrayList<String>();
		File file = new File(pathFolder);
		if(file.isDirectory()){
			File[] filesInDir = file.listFiles();
			for(File f : filesInDir){
				listFile.add(f.toString());
			}
		}
		return listFile;
	}
}
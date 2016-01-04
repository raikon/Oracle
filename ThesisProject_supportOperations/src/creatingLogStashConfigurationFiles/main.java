package creatingLogStashConfigurationFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

//commento di prova commit

public class main {
	
	static String pathTrain = "/home/oracle/Desktop/Claudia/Dati/Train";
	static String pathEvent = "/home/oracle/Desktop/Claudia/Dati/Event";
	static String pathPipeline = "/home/oracle/Desktop/Claudia/Dati/Pipeline";
	
	
	public static void main(String[] args) throws IOException {

		//leggi cartelle dati input
		ArrayList<String> listFileTrain = returnDocOfFolder(pathTrain);
		ArrayList<String> listFileEvent = returnDocOfFolder(pathEvent);
		ArrayList<String> listFilePipeline = returnDocOfFolder(pathPipeline);
		
		//createFile(listFileEvent);
		createFile(listFileTrain);
	}


	//crea e scrivi file
	public static void createFile(ArrayList<String> listFile) throws IOException {
		FileOutputStream file;
		for(String document : listFile) {
			
			String filename = document.substring(document.lastIndexOf("/"), document.lastIndexOf("."));
			String fileOutput = "/home/oracle/Desktop/Claudia/JSON_output"+filename+"_JSON.txt";
			
			String[] csvField = readCsvField(document);
			
			Script script = new Script(document,fileOutput,csvField);
			
			try {
				file = new FileOutputStream("/home/oracle/Desktop/Claudia/logstash-2.0.0/"+filename+".conf");
				PrintStream output = new PrintStream(file);
				if(document.contains("Train") || document.contains("Event")) {
					String scriptInitiatives = script.createScriptInitiatives();
					output.println(scriptInitiatives);
					output.close();
				}
				/*
				else if(document.contains("Pipeline")) {
					String scriptPipeline = createScriptPipeline();
					output.println(scriptPipeline);
					output.close();
				}
				*/
				else {
					System.out.println("Non esiste un default script per questo tipo di dati");
				}
			} 
			catch (IOException e) {
				System.out.println("Errore: " + e);
				System.exit(1);
			}
		}
	}

	private static String[] readCsvField(String document) throws IOException {
		BufferedReader brTest = new BufferedReader(new FileReader(document));
		String text = brTest.readLine();
		while(text.contains(",,")) {
			text = brTest.readLine();
			//System.out.println(text);
		}
		String[] strArray = text.split(",");
		if (strArray[0].equals("")) {
			strArray[0]="Row";
		}
		System.out.println(Arrays.toString(strArray));
		brTest.close();
		return strArray;
	}



	//ritorna una lista con i nomi dei file di una cartella
	public static ArrayList<String> returnDocOfFolder(String pathFolder) {
		ArrayList<String> listFile = new ArrayList<String>();
		File file = new File(pathFolder);
		if(file.isDirectory()){
			File[] filesInDir = file.listFiles();
			for(File f : filesInDir){
				//System.out.println(f.toString());
				listFile.add(f.toString());
			}
		}
		return listFile;
	}
}
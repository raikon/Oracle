package creatingLogStashConfigurationFiles;

public class Script {

	String fileInput;
	String fileOutput;
	String[] csvField;

	public Script(String fileInput, String fileOutput, String[] csvField) {
		super();
		this.fileInput = fileInput;
		this.fileOutput = fileOutput;
		this.csvField = csvField;
	}

	public String createScriptInitiatives() {
		String script,input,filter,output;
		input = "input { \n"
				+ "\tfile { \n"
				+ "\t\tpath => \""+fileInput+"\" \n"
				+ "\t\tstart_position => \"beginning\" \n"
				+ "\t} \n"
				+ "}";
		
		filter = "filter { \n"
				+ "\tcsv { \n"
				+ "\t\tcolumns => [ \n";
		for (String field: csvField) {
			field = field.replaceAll(" ", "");
			field = field.replaceAll("[^a-zA-Z ]", ""); //punteggiatura
			filter = filter+"\t\t\t"+field+",\n";
		}
		//rimuovi ultima virgola
		filter = filter.substring(0, filter.length()-2);
		filter = filter+ "\n\t\t] \n"
				+ "\t\tseparator => \",\" \n"
				+ "\t\tremove_field => [ \n"
				+ "\t\t\t\"message\", \n"
				+ "\t\t\t\"@version\", \n"
				+ "\t\t\t\"@timestamp\", \n"
				+ "\t\t\t\"host\", \n"
				+ "\t\t\t\"path\" \n"
				+ "\t\t] \n"
				+ "\t} \n"
				+ "}";
			/*	
			filter = "filter { \n"
					+ "\tcsv { \n"
					+ "\t\tcolumns => [ \n"
					+ "\t\t\t\"country\", \n"
					+ "\t\t\t\"company\", \n"
					+ "\t\t\t\"name\", \n"
					+ "\t\t\t\"status\" \n"
					+ "\t\t] \n"
					+ "\t\tseparator => \",\" \n"
					+ "\t\tremove_field => [ \n"
					+ "\t\t\t\"message\", \n"
					+ "\t\t\t\"@version\", \n"
					+ "\t\t\t\"@timestamp\", \n"
					+ "\t\t\t\"host\", \n"
					+ "\t\t\t\"path\" \n"
					+ "\t\t] \n"
					+ "\t} \n"
					+ "}";*/
		
		output = "output { \n"
				+ "\tfile { \n"
				+ "\t\tpath => \""+fileOutput+"\" \n"
				+ "\t} \n"
				+ "\tstdout { \n"
				+ "\t\tcodec => rubydebug \n"
				+ "\t} \n"
				+ "} ";

		script = input+"\n \n"+filter+"\n \n"+output;
		return script;
	}

	public String getFileInput() {
		return fileInput;
	}

	public void setFileInput(String fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileOutput() {
		return fileOutput;
	}

	public void setFileOutput(String fileOutput) {
		this.fileOutput = fileOutput;
	}

	public String[] getCsvField() {
		return csvField;
	}

	public void setCsvField(String[] csvField) {
		this.csvField = csvField;
	}
	
}

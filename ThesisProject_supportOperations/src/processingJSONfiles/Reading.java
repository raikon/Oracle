package processingJSONfiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Reading {
	static ArrayList<String> alternativeName;
	static ArrayList<String> alternativeLastName;
	static ArrayList<String> alternativeCompany;


	public Reading() {
		super();
		Reading.alternativeName = loadAlternativeName();
		Reading.alternativeLastName = loadAlternativeLastName();
		Reading.alternativeCompany = loadAlternativeCompany();
	}


	private ArrayList<String> loadAlternativeName() {
		String[] aN = new String[]{"Nome","FirstName","NomePartecipante","Contact","Name","Nominativoregistrato"};
		ArrayList<String> alternativeName = new ArrayList<String>(Arrays.asList(aN));
		return alternativeName;
	}

	private ArrayList<String> loadAlternativeLastName() {
		String[] aLN = new String[]{"Cognome", "Cognomepartecipante","LastName","Contact","Name","Nominativoregistrato"};
		ArrayList<String> alternativeLastName = new ArrayList<String>(Arrays.asList(aLN));
		return alternativeLastName;
	}

	private ArrayList<String> loadAlternativeCompany() {
		String[] aC = new String[]{"Organization", "Customer","Company","Azienda", "Ente", "Partnerprevisti","Partner"};
		ArrayList<String> alternativeCompany = new ArrayList<String>(Arrays.asList(aC));
		return alternativeCompany;
	}


	public static List<HashMap<String,String>> readJSON(String fileJSON,int p) throws org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();

		try {
			Object obj = parser.parse(new FileReader(fileJSON));
			JSONObject jsonObject = (JSONObject) obj;

			JSONObject participants = (JSONObject) jsonObject.get("participants");
			int indexP = 1;

			int i=0;
			String firstNameField = alternativeName.get(i);
			String lastNameField = alternativeLastName.get(i);
			String companyField = alternativeCompany.get(i);
			String firstNameFinal="",lastNameFinal="",companyFinal="";

			System.out.println(p);
			while(p!=0) {	
				JSONObject person = (JSONObject) participants.get("p"+indexP);
				HashMap<String,String> fieldFiltered = new HashMap<String,String>();

				if(indexP==1) {
					firstNameFinal = (String) person.get(firstNameField);
					while(firstNameFinal==(null)) {
						firstNameField = alternativeName.get(i);
						firstNameFinal = (String) person.get(firstNameField);
						i++;
					}

					i=0;
					lastNameFinal = (String) person.get(lastNameField);
					while(lastNameFinal==(null)) {
						lastNameField = alternativeLastName.get(i);
						lastNameFinal = (String) person.get(lastNameField);
						i++;
					}

					i=0;
					companyFinal = (String) person.get(companyField);
					while(companyFinal==(null)) {
						companyField = alternativeCompany.get(i);
						companyFinal = (String) person.get(companyField);
						i++;
					}
				}
				if(firstNameField.equals(lastNameField)) {
					String firstAndLastName = (String) person.get(firstNameField);
					if(firstAndLastName.lastIndexOf(" ")!=-1) {
						firstNameFinal = firstAndLastName.substring(0, firstAndLastName.lastIndexOf(" "));
						lastNameFinal = firstAndLastName.substring(firstAndLastName.lastIndexOf(" ")+1, firstAndLastName.length());
					}
				}
				else {
					firstNameFinal = (String) person.get(firstNameField);
					lastNameFinal = (String) person.get(lastNameField);				
				}
				companyFinal = (String) person.get(companyField);

				fieldFiltered.put("FirstName", firstNameFinal);
				fieldFiltered.put("LastName", lastNameFinal);
				fieldFiltered.put("Company", companyFinal);

				result.add(fieldFiltered);
				p--;
				indexP++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
package extractionCordinatesCity;
	
import java.util.List;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

public class main {

	public static void main(String[] args) {
		String[] cities = new String[]{"Brescia","Trezzo Sull'Adda","Trieste","San Lazzaro Di Savena","Bologna","Montebelluna","Maranello","Mogliano Veneto","Roma",
				"Noventa Di Piave","Breganze","Milano","Anchiano","Borgoricco","San Mauro Torinese","Chieti","Andria","Firenze","Parma","Porto Mantovano","Scandicci",
				"Genova","Padova","Terranuova Bracciolini","Torino","Pomezia","Siena","Monza","Ponzano","Cologno Monzese","Seriate","Rozzano","Acicastello","Collecchio",
				"Cesate","Paris","Perugia","Pontedera","Trivero","Segrate","Verona","San Marino","Città Del Vaticano","Chiasso","Modena","Concesio","Malcesine","Potenza",
				"Monopoli","Imola","Carpi","Caronno Pertusella","San Donato Milanese","Lodi Vecchio","Modugno","San Martino Buon Albergo","Napoli","Ivrea","Codrongianos",
				"Mantova","Albenga","L'Aquila","Palermo","Sassuolo","Novedrate","Vignola","Sarroch","Pesaro","Borgolavezzaro","Reggio Nell'Emilia","Buttrio","Reggello",
				"Bergamo","Piossasco","Agordo","Bordano","Merano","Montelabbate","Camagna Monferrato","Sesto Fiorentino","Catania","Ameglia","Bardolino","Lecco","Tolentino",
				"Budrio","Montevecchia","Venezia","Campodarsego","Teramo","Rivoli","Saronno","Casalmoro","Cavriago","Bolzano","Trento","Castiglione Delle Stiviere",
				"Nova Milanese","Assago","Legnano","Quarona","Alba","San Vito Al Tagliamento","Melfi","Lomagna","Casarano","Treviglio","Longiano","Almese","Brugine","Lecce",
				"Cagliari","Rovereto","Feltre","Ravenna","Pioltello","Agrate Brianza","Formigine","Caserta","Montecchio Maggiore","Biella","Castel D'Ario","Piombino Dese",
				"Sedico","Siracusa","Bari","Tortona","Bruino","Adelfia","Cassano Magnago","Caltanissetta","Rende","Fiorano Modenese","Messina","Monteroni D'Arbia","Arenzano",
				"Valbrembo","Bassano Del Grappa","Curtarolo","Osio Sopra","Hong Kong","Veduggio Con Colzano"
		};
		
		System.out.println("def base_city_location");
		
		int i = 0;
		for(String city : cities) {
			isCity(city,i);
			i++;
		}
		
	}
	
	
	/*
	 * Restituisce 1 se riconosce la citta', -1 se non la riconosce, 
	 * 0 se finisce il numero di richieste che puo' fare
	 */
	public static void isCity(String citta, int cont) {
		try {
			WebService.setUserName("siicdll");
			
			ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
			searchCriteria.setQ(citta);
			ToponymSearchResult searchResult = WebService.search(searchCriteria);
			Toponym mainToponomy = searchResult.getToponyms().get(0);
			
			if(cont==0) {
				System.out.println(
					
					"if(base_city==\""+citta+"\") {"+
					" base_city_location= \""+mainToponomy.getLatitude()+", "+mainToponomy.getLongitude()+"\""+
					"}"
			
					);
			}
			else {
				
				System.out.println(
						
					"else if(base_city==\""+citta+"\") {"+
					" base_city_location= \""+mainToponomy.getLatitude()+", "+mainToponomy.getLongitude()+"\""+
					"}"
				
				);
			}
		}
		catch(Exception e) {};
	}
}

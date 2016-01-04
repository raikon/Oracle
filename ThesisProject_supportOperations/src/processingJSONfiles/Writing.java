package processingJSONfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.json.simple.JSONObject;

public class Writing {
	static String[] customers;
	static String[] partners;
	static String[] all;
	static ArrayList<String> wordsInConflict = new ArrayList<String>();

	@SuppressWarnings("static-access")
	public Writing() {
		super();
		this.customers = new String[]{ "2i rete gas s.p.a.","a.m.i.u. genova s.p.a.","a.menarini industrie farmaceutiche riunite s.r.l.","a2a spa","abi associazione bancaria italiana","acea","acotel group spa","acquedotto pugliese s.p.a.","acsm-agam spa","aeroporto guglielmo marconi di bologna s.p.a.","afv acciaierie beltrame s.p.a","agos ducato spa","agustawestland s.p.a","air liquide italia spa","alfa sistemi srl","alitalia - linee aeree italiane s.p.a.","allianz","almo nature spa","amadori","anas direzione generale","anas s.p.a.","aptuit verona srl","arag assicurazioni s.p.a.","arpal","arriva italia s.r.l.","arrow special parts spa","artsana spa","asl 1 imperiese","asm voghera spa","assicurazioni generali s.p.a.","assimoco spa","at s.r.l","atahotels spa","atena patrimonio spa","audemars piguet (marketing) sa","autostrade per l´italia spa","aviva italia servizi s.c.a.r.l.","axa assicurazioni s.p.a","axa assistance italia","axa tech","azienda ospedaliera di busto arsizio","azienda ospedaliera s.giovanni addolorata","azienda ospedaliera universitaria policlinico paolo giaccone","azienda provinciale servizi sanitari","azienda trasporti verona srl","azienda usl bt 1","banca albertini syz","banca leonardo s.p.a","banca mediolanum s.p.a.","banca popolare di milano","banca popolare di vicenza scparl","banca popolare milano","banca privata leasing","banca sistema","banco popolare","bcube spa","benetton","biesse s.p.a.","bnl - bnp paribas group","borsa italiana s.p.a.","bovis lend lease srl","bper services s.c.p.a.","bper services s.c.p.a. - gruppo banca popolare dell'emilia romagna","bracco s.p.a.","british telecom italia","bticino s.p.a.","bulgari s.p.a.","buzzi unicem s.p.a.","cairo communication s.p.a.","caltagirone editore spa","calzedonia spa","cap gestione spa","capri srl","carige","cariparma credit agricol ses","carrefour","casa di cura maria rosaria spa","cassa di risparmio di parma e piacenza spa","cattolica assicurazioni","cattolica services s.c.p.a.","cedacri s.p.a.","cefla s.c","cesart s.r.l.","ceva logistics holding italy spa","chebanca! gruppo mediobanca","chl spa","cidiverte spa","clivet spa","cns consorzio nazionale servizi","coeclerici spa","colombini spa","compass group italia spa","conbipel s.p.a.","consip s.p.a.","coop consorzio nord ovest s.c.a.r.l.","coop italia societa' cooperativa","coop toscana lazio s.c.a.r.l.","corte dei conti","corte dei conti - sezione giurisdizionale","costa crociere s.p.a.","cotral s.p.a.","credem","credit agricole vita s.p.a.","creditech spa","credito emiliano s.p.a.","credito valtellinese","cremonini spa","crif centrale rischi finanziaria spa","crif decision solutions s.p.a.","crif s.p.a.","cse consorzio servizi bancari soc. cons.a rl","culligan holding italy spa","danieli & c.officine meccaniche s.p.a","dayco europe srl","deutsche bank italia","diasorin spa","diesel s.p.a.","direct line insurance s.p.a.","dmedia commerce spa","dolomiti superski","e-mission s.r.l.","ecodom scarl","edenred","edison s.p.a.","elica s.p.a.","enasarco","enel italia srl","enel spa","eni e&p","eni s.p.a. - divisione eni ict","eni spa","enigen italia srl","enolgas bonomi spa","enpam - ente naz.di prev. e assistenza medici","ente autonomo fiere di verona","equitalia spa","erg s.p.a.","erg services s.p.a.","ermenegildo zegna holditalia spa","esaote","esselunga s.p.a.","eurosystem s.p.a.","eurotrading srl","f.lli piacenza spa","faac spa","fabbrica d'armi p. beretta spa","fassa bortolo spa","fastweb s.p.a.","fastweb spa","fatebenefratelli","fater s.p.a.","fca italy spa","fendi italia srl","fendi srl","fenice spa a socio unico","ferrari s.p.a.","ferriere nord s.p.a.","ferrovie dello stato s.p.a.","feval progetti srl","fiat chrysler automobiles","fiat i.t.e.m. s.p.a.","fiat industrial cnh","fiera milano spa","filippetti s.p.a.","fincantieri cantieri navali italiani s.p.a.","finiper spa","finmeccanica global services spa","finmeccanica s.p.a.","fintel engineering srl","fondazione e.n.p.a.m. ente nazionale di previdenza ed assistenza dei medici e degli odontoiatri","fondimpresa","food and agriculture organisation of the un","fratelli carli spa","fratelli piacenza spa","fujitsu technology solutions s.p.a.","furla s.p.a.","gabel industria tessile s.p.a.","gdf suez energia italia spa","geico spa","generali assicurazioni","generali business solutions scpa","generali group","generali infrastructure services (gis) s.c.a.r.l.","generali italia s.p.a.","geox spa","gesac spa","gi group spa","gianni versace s.p.a.","gias s.r.l.","giorgio armani s.p.a","granarolo felsinea s.p.a","grandi salumifici italiani spa","gruppo banca leonardo spa","gruppo fabbri spa","gruppo fabbri vignola s.p.a.","gruppo monte dei paschi asset management s.p.a.","gruppo piazzetta spa","gruppo végé società cooperativa","gse - gestore dei servizi energetici spa","gtech s.p.a.","h3g","h3g spa","hera spa","i-faber s.p.a.","i.n.p.s.","icbpi spa","ice agenzia per la promozione all'estero e l'internazionalizzazione delle imprese italiane","ifad inter. foud for agricol","iguzzini illuminazione s.p.a.","imperia & monferrina s.p.a.","inail direzione servizi informativi","inarcassa","infocamere s.c.p.a.","infocamere societa' consortile per azioni","informatica alto adige spa","infracom italia spa","insiel spa","international footwear investment b.v.","interpump group","intesa sanpaolo group services spa","irce spa","is srl","isagro spa","istat","istituto centrale banche popolari italiane s.p.a. (icbpi)","istituto europeo di oncologia","istituto nazionale di statistica","istituto poligrafico e zecca dello stato s.p.a.","italtel s.p.a.","ivri direzione spa","juventus football club s.p.a.","kering","kering italia s.p.a","kme europa metalli spa","kopron s.p.a","kuvera s.p.a. (carpisa)","kuwait petroleum italia s.p.a.","la perla global management","la perla global management (uk)","lait spa","laminazione sottile spa","lanificio f.lli cerruti s.p.a.","linea group spa","logistica stura srl","lombardia informatica s.p.a.","lucart spa","luisa spagnoli s.p.a.","luxottica group","m data system s.r.l.","marazzi group srl","marchesi antinori srl","marilab s.r.l.","marina rinaldi s.r.l.","maxmara fashion group srl","mediamente consulting srl","mediaset s.p.a.","mediobanca innovation services s.c.p.a.","mediobanca spa","mediolanum s.p.a.","megadyne spa","ministero del lavoro","ministero del lavoro e delle politiche sociali","ministero dell'economia e finanze - dipartimento generale del personale e dei servizi del tesoro","ministero della giustizia","ministero interno","miroglio s.p.a","miur - ministero istruzione università e ricerca","mm-one group srl","moby s.p.a.","mondadori printing spa","monte dei paschi di siena - banca per l'impresa spa","move group s.r.l.","multicedi srl","nestle' italiana s.p.a.","nidec sole motor corporation srl","ntt data italia s.p.a.","octo telematics spa","panatta sport srl","pasubio tecnologia s.r.l.","pirelli","poltrone e sofa' spa","poste italiane spa","poste vita s.p.a.","postel s.p.a.","pr industrials s.r.l.","prada spa","publitalia '80 spa","rai - radio televisione italiana s.p.a.","reale mutua assicurazioni","recordati industria chimica e farmaceutica s.p.a.","regione campania","regione sicilia","reno de medici s.p.a.","reply","rete ferroviaria italiana spa","rgi outsourcing srl","riello s.p.a.","roma capitale","romatec srl","s.i.a.e.","sace spa","sacmi - cooperativa meccanici imola soc. coop.","saes getters spa","sagat spa","saipem s.p.a.","salini impregilo s.p.a.","salvagnini italia s.p.a.","salvatore ferragamo s.p.a.","sapio produzione idrogeno ossigeno srl","scp srl","sec servizi scpa","sec servizi societa' consortile per azioni","selex es spa","selex service management spa","siad - societa' italiana acetilene e derivati spa","siemens business service spa","silatech srl","sina spa","siram spa","sirti societa' per azioni","sisal s.p.a.","sky italia s.r.l.","snai","snai s.p.a.","snam s.p.a.","società gasdotti italia spa","societa' italiana per condotte d'acqua spa","sogei","solland silicon srl","sorin spa","sose - società per gli studi di settore","summa srl","swinger international spa","tecnocasa franchising spa","telecom italia","telecom italia s.p.a.","terna spa","theolab spa","trenitalia s.p.a.","ubi banca scpa","ubi sistemi e servizi s.c.p.a.","unicoop firenze s.c.","unicredit business integrated solutions s.c.p.a.","unipol","unipolsai assicurazioni s.p.a.","unisalute s.p.a.","universita' degli studi g. d'annunzio","v.i.r. valvoindustria ing.rizzo s.p.a.","veneto banca holding s.c.p.a.","venistar s.p.a","vittoria assicurazioni s.p.a.","vodafone omnitel n.v.","widiba","wind","wind telecomunicazioni s.p.a.","wirex srl","world food programme","xenialab srl","zambon group spa","zurich insurance company" };;
		this.partners = new String[]{ "a.b.i.c. srl","accenture","accenture finance and accounting bpo services spa","accenture spa","accenture technology solutions s.r.l","accenture, s.l.","alfa sistemi srl","ateikon srl","beta 80 s.p.a. software e sistemi","bizmatica spa","blue system srl","bridge consulting srl","business reply s.r.l.","cad it s.p.a.","computer gross italia spa","con.nexo spa","consoft sistemi spa","databiz s.r.l.","deloitte","deloitte consulting srl","deloitte xbs srl","dia srl","ecoh media s.r.l.","engineering ingegneria informatica s.p.a.","ericsson it solutions & services s.p.a.","eustema spa","exprivia spa","feval progetti srl","freedata s.r.l.","garmendia consulting italia s.r.l","gn informatica srl","i.a.n. srl","i.t.d. s.r.l.","iconsulting spa","icos spa","indra italia s.p.a.","indra sistemas, s.a.","kpmg advisory spa","npo sistemi spa","ntt data italia s.p.a.","pricewaterhousecoopers asesores de negocios, s.l.","reason that s.r.l.","reply consulting","reply s.p.a.","romatec srl","santer reply spa con unico azionista","scp srl","sec consulting srl","selex es spa","selex service management spa","selex sistemi integrati s.p.a.","sopra steria group spa","sorint.lab spa","sytel reply roma s.r.l","sytel reply s.r.l.","t.a.i. software solution srl","tai software solution srl","techedge spa","technology reply srl","telecom italia s.p.a.","trebi generalconsult s.r.l.","var group spa","verano ingenieria de mexico s.a.c.v","x view srl","xenia progetti s.r.l." };
		this.all = ArrayUtils.addAll(customers, partners);
		String[] words = new String[]{"banca","italia","spa","s.p.a","s.p.a.","informatica","group","s.r.l.","srl","s.r.l","consulting","roma","tre","sistemi","sistema","gestione","it","regione","ministero","sezione","sez","per","l'italia","istituto","nazionale","agenzia","delle","della","del","degli","dello","dei","ente","università","ricerca","istruzione","club","salute","aga","service","asl","ovest","temi","telecomunicazioni","assistance","italy","holding","scpa","popolare","bank","engineering","servizi","solutions","milan","ing","ing.","bancaria","3","ama","assistenza","cassa","system","service","r.l","trasporti","azienda","eco","store","paolo","studi","solutions","policlinico","osp","san","scarl","siena","capo","europ","associazione","previdenza","medici","soc.cons.","dipartimento","dsi","finanziari","business","ade","cai","atos","sas","consorzio","services","data","mit","generale","milano","tor","systems","software","unico","aed","eds","tech","formatica","gruppo","italian","management","nei","corporation","communications","stato","sio","auto","inc","universita'","company","computer","sita","ass.","sin","innovation","ows","techno","g.d"};
		this.wordsInConflict = new ArrayList<String>(Arrays.asList(words));
	}

	@SuppressWarnings("unchecked")
	public static void writeJSON(List<HashMap<String,String>> input, String key, String output) throws IOException {
		FileWriter file = new FileWriter("output/"+output);
		JSONObject obj = null;
		int checkFirst=0;
		for (HashMap<String,String> map : input) {
			obj = new JSONObject();
			if (checkFirst==0) {
				map.put("key", "key");
				checkFirst=1;
			}
			else {
				map.put("key", key);
			}
			Set<String> keys = map.keySet();
			for(String keyMap : keys) {
				if(keyMap.equals("Company")) {
					//check similarity
					String newCompany = uniformValue(map.get(keyMap),output);
					obj.put(keyMap, newCompany);
				}
				else { 	
					obj.put(keyMap, map.get(keyMap));
				}
			}

			try {
				file.write(obj.toJSONString());
				file.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		file.flush();
		file.close();
	}

	private static String uniformValue(String company, String output) {
		company = company.toLowerCase();
		System.out.println("\n############## Company: "+company);

		int indexOfFirstUnderScore = output.indexOf("_");
		String target = output.substring(indexOfFirstUnderScore+1, indexOfFirstUnderScore+5);

		if(target.equals("Cust")) {
			return computeSimilarity(company,customers);
		}
		else if(target.equals("Part")) {
			return computeSimilarity(company,partners);
		}
		else if(target.equals("ALL_")){
			return computeSimilarity(company,all);
		}
		//System.out.println("Nuovo: "+company);
		return company;
	}

	private static String computeSimilarity(String company, String[] list) {
		String newCompany = company;
		int firstCheck=0, ld_default=0;
		for (String item: list) {

			String itemNorm = item.replaceAll(" ", "").toLowerCase();
			String companyNorm = company.replaceAll("-", "");
			companyNorm = companyNorm.replaceAll(" ", "");

			//System.out.println("itemNorm: "+itemNorm);
			//System.out.println("companyNorm: "+companyNorm);
			
			if (companyNorm.equals("")) {
				newCompany="unspecified";
			}
			else if(companyNorm.length()<=3 || companyNorm.contains("atos") || companyNorm.contains("sita")) {
				newCompany=company;
			}
			else if(itemNorm.contains(companyNorm) || companyNorm.contains(itemNorm) || checkToken(company,item)) { 
				//System.out.println("CONTENIMENTO con: "+itemNorm);


				int ld_new= LevenshteinDistance.computeLevenshteinDistance(companyNorm, itemNorm);

				if(firstCheck==0) {
					ld_default = ld_new; 
					newCompany=item;
					//System.out.println("PRIMO LD: "+ld_default+" calcolato su: "+item);
					firstCheck=1;
				}

				else if(ld_new<=ld_default) {
					//System.out.println("NUOVO DEFAULT: "+ld_new+" calcolato su: "+item);
					ld_default = ld_new;
					newCompany = item;
				}
			}				
		}
		System.out.println("DEFINITIVO: "+newCompany);
		return newCompany;
	}


	private static boolean checkToken(String company, String item) {
		company = company.toLowerCase();
		item = item.replaceAll(" ", "").toLowerCase();
		if(company.contains(" ")) {
			String[] companySplit = company.split(" ");
			for (String token : companySplit) {
				if(item.contains(token) && (!wordsInConflict.contains(token)) && token.length()>2) {
					System.out.println("CHECK TOKEN RILEVATO: "+token);
					return true;
				}
			}
		}
		return false;
	}

}
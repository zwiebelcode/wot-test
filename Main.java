import java.util.HashSet;


public class Main {
	public static void main(String[] args) {
		// Einfaches Testprogramm für den erarbeiteten Algorithmus a1 für die Trust-Prüfung
		// Diese Variante enthält als vereinfachung keine Notare, da diese ja nur der Anonymisierung dienen.
		
		// == Initialisierung der Testwerte
		Blacklist blacklist = new Blacklist();
		
		OmpId rootX = new OmpId("rootx.onion");
		OmpId rootI = new OmpId("rooti.onion");
		OmpId rootR = new OmpId("rootr.onion");
		
		HashSet<OmpId> rootIds = new HashSet<OmpId>();
		rootIds.add(rootX);
		rootIds.add(rootI);
		rootIds.add(rootR);
		
		OmpId userA = new OmpId("a.onion");
		userA.requestSignatureAt(rootX, "finger-a");
		
		HashSet<OmpId> wot = new HashSet<OmpId>();
		wot.add(rootX);
		wot.add(rootI);
		wot.add(rootR);
		wot.add(userA);
		
		// == Der Trust algorithmus
		// Phase 1
		for (OmpId wurzel : rootIds) {
			wurzel.setTrustedRoot();
		}
		
		/*
		 * Phase 2: L1 wird nach L2 kopiert. Es werden in L2 von oben nach unten alle schichten mit
		 * ids gemäß signierung durchlaufen und dann geprüft, ob ein blacklist eintrag "bad-signing"
		 * existiert, für den gilt, dass der fingerprint von der aktuellen ebene signiert wurde
		 * (und der blacklistete muss den arbiter auch festgelegt haben).
		 * --- Schließlich werden alle OmpIds die mindestens eine route ohne Blacklisteinträge
		 * zur wurzel haben, als valid-hash bzw. valid-fingerprint markiert.
		 */
		final int MAX_SCHICHTEN = 20;
		HashSet<OmpId> schichten[] = new HashSet[MAX_SCHICHTEN];
		//HashSet<OmpId> signingBlacklist = new HashSet<OmpId>();
		HashSet<String> signingBlacklistedFingerprints = new HashSet<String>();
		HashSet<OmpId> currentSchicht;

		
		int anzahlSchichten = 0;
		{
			currentSchicht = new HashSet<OmpId>();
			
			// erste schicht?
			if(anzahlSchichten==0) {
				for (OmpId wurzel : rootIds) {
					currentSchicht.add(wurzel);
				}
			} else
			
			// zweite oder weitere schicht?
			{
				// gehe durch alle ids der oberen schicht (obige ids sind gültig)
				for(OmpId id : schichten[anzahlSchichten-1]) {
					// gehe durch die kinder dieser id
					OmpId childs[] = id.getSignedFingerprints();
					for(OmpId child : childs)
					{
						// markiere es als valid-fingerprint
						child.setValidFingerprint(true);
						
						// prüfe, ob das kind in der lokalen blacklist für "bad-signing" ist (1)
						boolean blacklisted = signingBlacklistedFingerprints.contains(child.getFingerprint());
						
						if(blacklisted) {
							// falls ja ignorieren
						} else
						// falls nein
						{
							// übernehme dieses kind in die aktuelle schicht
							currentSchicht.add(child);
							
							// und markiere es vorübergehend als trusted-Identmanager
							child.setTrustedIdentmanager(true);
						}
					}
				}
			}
			
			// gehe durch alle ids der aktuellen schicht
			for(OmpId id : currentSchicht) {
				// prüfe, ob von dieser id ein blacklisteintrag kommt.
				
				/*HashSet<BlacklistEntry> bles =
				blacklist.getBlacklistEntriesBy(
						new String[]{child.getFingerprint()},
						(OmpId[])currentSchicht.toArray(),
						new String[]{"bad-signing"});*/
				
				// falls ja
				{
					// nehme den eintrag in die lokale blacklist auf (1)
					// falls der geblacklistete das flag trusted-identmanager
					// besitzt
					{
						// das flag trusted-identmanager wird wieder entzogen.
						// Gehe rekursiv durch seine Kinder und entferne bei
						// ihnen sowohl das trusted-identmanager flag, als auch
						// das valid fingerprint flag (2)
					}
				}
			}
			
			schichten[anzahlSchichten] = currentSchicht;
			anzahlSchichten++;
		}
		
		// TODO in späteren Schritten: prüfen, ob der blacklisteintrag legitim ist
		// (also ob überhaupt ein solecher vertraug unterzeichnet wurde)
		

		

		// Phase 3
		

		// === Das Ergebnis der Trust Berechnung ausgeben
		for (OmpId ompId : wot) {
			ompId.toString();
		}
	}
}

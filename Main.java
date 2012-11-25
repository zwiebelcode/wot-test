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
		HashSet<OmpId> signingBlacklist = new HashSet<OmpId>();
		//HashSet<OmpId> currentSchicht = new HashSet<OmpId>();

		
		// für jede schicht
		{
			// erste schicht?
			{
				// fülle die erste schicht mit den wurzeln
			}
			
			// zweite schicht?
			{
				// gehe durch alle ids der oberen schicht (obige ids sind gültig)
				{
					// gehe durch die kinder dieser id
					{
						// markiere es als valid-fingerprint
						
						// prüfe, ob das kind in der lokalen blacklist für "signing" ist (1)
						// falls ja ignorieren
						// falls nein
						{
							// übernehme dieses kind in die aktuelle schicht
							// und markiere es zunächst als trusted-Identmanager
						}
					}
				}
			}
			
			// gehe durch alle ids der aktuellen schicht
			{
				// prüfe, ob von dieser id ein blacklisteintrag kommt.
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
		}
		
		// TODO in späteren Schritten: prüfen, ob der blacklisteintrag legitim ist
		// (also ob überhaupt ein solecher vertraug unterzeichnet wurde)
		
		/*
		 *  (2)
		 *  
		 */
		
		/*
		int schicht = -1;
		do {
			schicht++;
			schichten[schicht] = new HashSet<OmpId>();
			currentSchicht = schichten[schicht];
			
			if(schicht==0) {
				for (OmpId ompId : rootIds) {
					currentSchicht.add(ompId);
				}
			} else {
				// nachfolgende schichten - nehme childs der parents auf
				for (OmpId signer : schichten[0]) {
					OmpId childs[] = signer.getSignedFingerprints();
					for (OmpId child : childs) {
						currentSchicht.add(child);
						child.setValidFingerprint(true); // vorübergehend, sollte nie auf einem live wot geschehen
					}
				}
			}
			// TODO: Abbrechen, wenn keine weiteren Kinder mehr vorhanden sind oder wenn es nur noch schleifen gibt
		} while(schicht<MAX_SCHICHTEN);
		int anzSchichten = schicht+1;*/
		
		// Alle Kinder von Wurzeln sind jetzt als valid markiert
		// Jetzt müssen die Blacklisteinträge schichtenweise verarbeitet werden
		/*
		for(int i=0;i<anzSchichten;i++) {
			currentSchicht = schichten[i];
			
			HashSet<BlacklistEntry> bles =
					blacklist.getBlacklistEntriesBy(null,
							(OmpId[])currentSchicht.toArray(),
							new String[]{"bad-signing"});
			
			for (BlacklistEntry e : bles) {
				// e ist ein Blacklisteintrag auf aktueller Schicht
			}
		}

		*/
		
		// Phase 3
		

		// === Das Ergebnis der Trust Berechnung ausgeben
		for (OmpId ompId : wot) {
			ompId.toString();
		}
	}
}

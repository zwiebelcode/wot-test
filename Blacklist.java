import java.util.HashSet;
import java.util.Set;


public class Blacklist {
	private HashSet<BlacklistEntry> blacklist = new HashSet<BlacklistEntry>();
	
	public Blacklist() {
		
	}
	
	public void addBlackListEntry(BlacklistEntry e) {
		blacklist.add(e);
	}
	
	/*
	// Mit Sets ließe sich nachfolgendes einfacher und performanter umsetzen. testen!
	public HashSet<BlacklistEntry> getBlacklistEntriesBy(
			String searchFingerprints[],
			OmpId searchFingerprintSigners[],
			String searchFraudNames[]) {
		
		HashSet<BlacklistEntry> filteredBlacklist = new HashSet<BlacklistEntry>();
		
		boolean fingerprintMatches;
		boolean fingerprintSignerMatches;
		boolean fraudNameMatches;
		
		for (BlacklistEntry entry : blacklist) {
			fingerprintMatches = false;
			fingerprintSignerMatches = false;
			fraudNameMatches = false;
			
			if(searchFingerprints!=null) {
				for (String searchFingerprint : searchFingerprints) {
					if(entry.fingerprint.equals(searchFingerprint)) {
						fingerprintMatches = true;
						break;
					}
				}
			}
			
			if(searchFingerprintSigners!=null) {
				for (OmpId searchFingerprintSigner : searchFingerprintSigners) {
					if(entry.fingerprintSigner.equals(searchFingerprintSigner)) {
						fingerprintSignerMatches = true;
						break;
					}
				}
			}
			
			if(searchFraudNames!=null) {
				for (String searchFraudName : searchFraudNames) {
					if(entry.fraudName.equals(searchFraudName)) {
						fraudNameMatches = true;
						break;
					}
				}
			}
			
			
			
			if( (searchFingerprints==null || fingerprintMatches) &&
					(searchFingerprintSigners==null || fingerprintSignerMatches) &&
					(searchFraudNames==null || fraudNameMatches) ) {
				filteredBlacklist.add(entry);
			}
		}
		
		return filteredBlacklist;
	}*/
	
	public HashSet<BlacklistEntry> getBlacklistEntriesBy(
			Set<String> searchFingerprints,
			Set<OmpId> searchFingerprintSigners,
			Set<String> searchFraudNames) {
		
		HashSet<BlacklistEntry> filteredBlacklist = new HashSet<BlacklistEntry>();
		
		boolean fingerprintMatches;
		boolean fingerprintSignerMatches;
		boolean fraudNameMatches;
		
		for (BlacklistEntry entry : blacklist) {
			fingerprintMatches = false;
			fingerprintSignerMatches = false;
			fraudNameMatches = false;
			
			if(searchFingerprints!=null) {
				for (String searchFingerprint : searchFingerprints) {
					if(entry.fingerprint.equals(searchFingerprint)) {
						fingerprintMatches = true;
						break;
					}
				}
			}
			
			if(searchFingerprintSigners!=null) {
				for (OmpId searchFingerprintSigner : searchFingerprintSigners) {
					if(entry.fingerprintSigner.equals(searchFingerprintSigner)) {
						fingerprintSignerMatches = true;
						break;
					}
				}
			}
			
			if(searchFraudNames!=null) {
				for (String searchFraudName : searchFraudNames) {
					if(entry.fraudName.equals(searchFraudName)) {
						fraudNameMatches = true;
						break;
					}
				}
			}
			
			
			
			if( (searchFingerprints==null || fingerprintMatches) &&
					(searchFingerprintSigners==null || fingerprintSignerMatches) &&
					(searchFraudNames==null || fraudNameMatches) ) {
				filteredBlacklist.add(entry);
			}
		}
		
		return filteredBlacklist;
	}
	
	public HashSet<BlacklistEntry> getBlacklistEntriesBy(
			String fingerprint,
			OmpId fingerprintSigner,
			String fraudName) {
		
		HashSet<BlacklistEntry> filteredBlacklist = new HashSet<BlacklistEntry>();
		
		for (BlacklistEntry entry : blacklist) {
			if( (fingerprint==null || entry.fingerprint.equals(fingerprint)) &&
					(fingerprintSigner==null || entry.fingerprintSigner==fingerprintSigner) &&
					(fraudName==null || entry.fraudName.equals(fraudName)) ) {
				filteredBlacklist.add(entry);
			}
		}
		
		return filteredBlacklist;
	}
}

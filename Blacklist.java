import java.util.HashSet;


public class Blacklist {
	private HashSet<BlacklistEntry> blacklist = new HashSet<BlacklistEntry>();
	
	public Blacklist() {
		
	}
	
	public void addBlackListEntry(BlacklistEntry e) {
		blacklist.add(e);
	}
	
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
			
			if(searchFingerprints!=null && searchFingerprints.length>0) {
				for (String searchFingerprint : searchFingerprints) {
					if(entry.fingerprint.equals(searchFingerprint)) {
						fingerprintMatches = true;
						break;
					}
				}
			}
			
			if(searchFingerprintSigners!=null && searchFingerprintSigners.length>0) {
				for (OmpId searchFingerprintSigner : searchFingerprintSigners) {
					if(entry.fingerprintSigner.equals(searchFingerprintSigner)) {
						fingerprintSignerMatches = true;
						break;
					}
				}
			}
			
			if(searchFraudNames!=null && searchFraudNames.length>0) {
				for (String searchFraudName : searchFraudNames) {
					if(entry.fraudName.equals(searchFraudName)) {
						fraudNameMatches = true;
						break;
					}
				}
			}
			
			
			
			if( (searchFingerprints==null || searchFingerprints.length<=0 || fingerprintMatches) &&
					(searchFingerprintSigners==null || searchFingerprintSigners.length<=0 || fingerprintSignerMatches) &&
					(searchFraudNames==null || searchFraudNames.length<=0 || fraudNameMatches) ) {
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

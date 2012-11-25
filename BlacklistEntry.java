
public class BlacklistEntry {
	public String fingerprint;
	public OmpId fingerprintSigner;
	public String fraudName;
	
	public BlacklistEntry(String fingerprint, OmpId fingerprintSigner, String fraudName) {
		this.fingerprint = fingerprint;
		this.fingerprintSigner = fingerprintSigner;
		this.fraudName = fraudName;
	}
}

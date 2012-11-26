import java.util.HashSet;


// die valid markierungen sind nur der einfachbarkeit halber hier enthalten
// eigentlich haben die markierungen nichts mit der identität selbst zu tun un müssten separat
// als klasse TrustPeed<Context> geispeichert werden (oder so ähnlich)

public class OmpId {
	private String onionkey;
	
	private boolean validFingerprint = false;
	private boolean trustedIdentmanager = false;
	private boolean trustedArbiter = false;
	
	private OmpId parentIdentManager;
	private OmpId parentArbiter;
	
	private String fingerprint;
	
	private HashSet<OmpId> signedChildFingerprints = new HashSet<OmpId>();
	
	public OmpId(String onionkey) {
		this.onionkey = onionkey;
	}

	public void setValidFingerprint(boolean validFingerprint) {
		this.validFingerprint = validFingerprint;
	}

	public void setTrustedIdentmanager(boolean trustedIdentmanager) {
		this.trustedIdentmanager = trustedIdentmanager;
	}
	
	public void setTrustedArbiter(boolean trustedArbiter) {
		this.trustedArbiter = trustedArbiter;
	}

	public void setTrustedRoot() {
		trustedArbiter = true;
		trustedIdentmanager = true;
	}
	
	private void signFingerprint(OmpId child, String fingerprint) {
		child.fingerprint = fingerprint;
		signedChildFingerprints.add(child);
	}
	
	// inside, it needs a check in real life
	public void requestSignatureAt(OmpId signer, String fingerprint) {
		signer.signFingerprint(this, fingerprint);
	}
	
	public OmpId[] getSignedFingerprints() {
		return (OmpId[]) signedChildFingerprints.toArray();
	}

	public String getOnionkey() {
		return onionkey;
	}

	public boolean isValidHash() {
		return validFingerprint;
	}

	public boolean isTrustedIdentmanager() {
		return trustedIdentmanager;
	}

	public boolean isTrustedArbiter() {
		return trustedArbiter;
	}

	public OmpId getParentIdentManager() {
		return parentIdentManager;
	}

	public OmpId getParentArbiter() {
		return parentArbiter;
	}

	public String getFingerprint() {
		return fingerprint;
	}
	
	@Override
	public String toString() {
		return onionkey + " witch fingerprint " + fingerprint;
	}
}

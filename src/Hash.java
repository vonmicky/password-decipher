import java.math.BigInteger;
import java.security.MessageDigest;

public class Hash {
	private String password, algorithm, hash;
	
	public Hash (String password, String algorithm){
		this.setPassword(password);
		this.setAlgorithm(algorithm);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getHash() {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
	        digest.reset();
	        digest.update(password.getBytes("utf8"));
	        hash = String.format("%32x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		return hash;
	}
	
}

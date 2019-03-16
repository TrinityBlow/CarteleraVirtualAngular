package ttps.spring.restfulClasses;

public class TokenValidation {

	public static String getValidation() {
		return "123456";
	}
	
	public static String getTokenId(String token) {
		if(token.length() > TokenValidation.getValidation().length()) {
			return token.substring(0,token.length() - TokenValidation.getValidation().length());
		}
		return "";
	}
	
	public static String getValidationToken(String token) {
		if(token.length() > TokenValidation.getValidation().length()) {
			return token.substring(token.length() - TokenValidation.getValidation().length(), token.length());
		}
		return "";
	}

	public static boolean tokenValidation(String token) {
		if(token.length() > TokenValidation.getValidation().length()) {
			return TokenValidation.getValidationToken(token).equals(TokenValidation.getValidation());
		}
		return false;
	}
	
	public static boolean tokenValidation(String token, long id) {
		if(token.length() > TokenValidation.getValidation().length()) {
			if(TokenValidation.tokenValidation(token)) {
				return getTokenId(token).equals(String.valueOf(id));
			}
		}
		return false;
	}
	

	
}

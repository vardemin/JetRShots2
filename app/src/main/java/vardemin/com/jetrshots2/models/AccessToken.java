package vardemin.com.jetrshots2.models;

/**
 * Token Model
 */
public class AccessToken {

    private String access_token;
    private String token_type;
    private String scope;

    public String getAccessToken() {
        return access_token;
    }

    public String getTokenType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (! Character.isUpperCase(token_type.charAt(0))) {
            token_type =
                    Character
                            .toString(token_type.charAt(0))
                            .toUpperCase() + token_type.substring(1);
        }

        return token_type;
    }

    public String getScope() {
        return scope;
    }
}

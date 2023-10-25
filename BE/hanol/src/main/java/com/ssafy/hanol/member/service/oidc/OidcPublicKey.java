package com.ssafy.hanol.member.service.oidc;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OidcPublicKey {

    private String kid;
    private String alg;
    private String use;
    private String n;
    private String e;

    public OidcPublicKey(String kid, String alg, String use, String n, String e) {
        this.kid = kid;
        this.alg = alg;
        this.use = use;
        this.n = n;
        this.e = e;
    }
}

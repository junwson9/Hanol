package com.ssafy.hanol.member.service.dto;

import com.ssafy.hanol.member.service.oidc.OidcPublicKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class OidcPublicKeyResponse {

    private List<OidcPublicKey> keys;

}

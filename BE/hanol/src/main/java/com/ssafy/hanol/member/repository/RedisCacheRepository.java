package com.ssafy.hanol.member.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.hanol.common.exception.CommonErrorCode;
import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.service.oidc.OidcPublicKey;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheRepository {

    private final RedisTemplate<String, String> redisStringTemplate;
    private final ValueOperations<String, String> operation;
    private final ObjectMapper mapper = new ObjectMapper();

    public RedisCacheRepository(RedisTemplate<String, String> redisStringTemplate) {
        this.redisStringTemplate = redisStringTemplate;
        this.operation = redisStringTemplate.opsForValue();
    }

    public void savePublicKey(String key, List<OidcPublicKey> publicKeys){
        try{
            String publicKeyString = mapper.writeValueAsString(publicKeys);
            operation.set(key, publicKeyString, 1, TimeUnit.DAYS);
        }catch (JsonProcessingException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }
    }

//    public void saveGoogleOidcDocs(String key , GoogleOpenSearchDocsResponse oidcDocs){
//        try{
//            String oidcDocsString = mapper.writeValueAsString(oidcDocs);
//            operation.set(key, oidcDocsString, 1, TimeUnit.DAYS);
//        }catch (JsonProcessingException e){
//            throw new CustomException(CommonErrorCode.SERVER_ERROR);
//        }
//    }
//
//    public GoogleOpenSearchDocsResponse getOidcDocs(String key){
//        String values = operation.get(key);
//        if(values == null){
//            return null;
//        }
//        try{
//            return mapper.readValue(values, GoogleOpenSearchDocsResponse.class);
//        }catch (JsonProcessingException e){
//            throw new CustomException(CommonErrorCode.SERVER_ERROR);
//        }
//    }


    public List<OidcPublicKey> getOIDCPublicKeys(String key){
        String values = operation.get(key);
        if(values == null){
            return null;
        }
        try{
            return Arrays.asList(mapper.readValue(values, OidcPublicKey[].class));
        }catch (JsonProcessingException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }
    }
}

package com.app.token.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.model.Roles;
import com.app.model.UserToken;
import com.app.model.Users;
import com.app.util.CommonUtil;
import com.app.util.DateUtil;
import com.app.util.constant.CommonConstants;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.error.response.ErrorInfo;
import com.app.util.error.response.ServiceException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * Security Token Service Generating And Parsing Token
 *
 */

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

	
    @Resource(name = CommonConstants.ERROR_CODE_HELPER)
    private ErrorCodeHelper errorCodeHelper;

    @Autowired
    private UserTokenService userTokenService;

    private final Logger logger = Logger.getLogger(TokenServiceImpl.class);

    private static Key secret = MacProvider.generateKey();
    
    /**
     * Generate Security JWT token
     * 
     * @param userName
     * @param roles
     * @return UserToken object
     */
    @Override
    public UserToken generateUserToken(Users user) {

        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getId()));
        claims.put("CLAIM_TOKEN_VERSION", getRandomToken());
        UserToken token = new UserToken();
        token.setToken(Jwts.builder().setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, secret).compact());
        token.setLastUsed(DateUtil.getCurrentTimestampInUTC());
        token.setUser(user);
        token.setSecretKey(secret.getEncoded());
        return token;
    }
    
    @Override
	public UserToken generateUserToken(UserToken userToken) {
    	 Claims claims = Jwts.claims().setSubject(String.valueOf(userToken.getUser().getId()));
         claims.put("CLAIM_TOKEN_VERSION", getRandomToken());
         userToken.setToken(Jwts.builder().setClaims(claims)
             .signWith(SignatureAlgorithm.HS512, secret).compact());
         userToken.setLastUsed(DateUtil.getCurrentTimestampInUTC());
         userToken.setSecretKey(secret.getEncoded());
         return userToken;
	}
    /**
     * Generates random key
     * 
     * @return Double
     */
    private Double getRandomToken() {
        Double randomToken = null;
        try {

            // Create a secure random number generator using the "SHA1PRNG" algorithm
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            randomToken = secureRandom.nextDouble();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Invalid Algorithm used.", e);
        }

        return randomToken;

    }

    /**
    * Get user information from token
    * @param token
    * @return  Map<String, Object>
    */
    @Override
    public Map<String, Object> parseUserToken(String token) {
        Map<String, Object> userDetail = new HashMap<>();
        String userMailid = null;
        UserToken userToken;
        Integer id = null;
        userToken = userTokenService.getKeyByToken(token);
        Key secretKey = null;
        
        if (userToken != null) {
        	   // if user is deleted from the application
        	if(!userToken.getUser().getEnable()){
          	  ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1010_ERROR_CODE,
                        CommonConstants.E1010_ERROR_DESCRIPTION);
                    throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);
          }
            if (!CommonUtil.isTokenExpired(userToken.getLastUsed())) {
                secretKey = new SecretKeySpec(userToken.getSecretKey(), SignatureAlgorithm.HS512.getJcaName());
                userMailid = userToken.getUser().getUserProfile().getUserEmailId();
                id=userToken.getUser().getId();
                userToken.setLastUsed(DateUtil.getCurrentTimestampInUTC());
                userToken.setSecretKey(secretKey.getEncoded());
            }else{
                
                ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1009_ERROR_CODE,
                    CommonConstants.E1009_ERROR_DESCRIPTION);
                throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);
            }
        }
        try {
            Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            if (body != null) {
                if (null!=id && id.equals(Integer.valueOf(body.getSubject()))) {
                    userDetail.put(CommonConstants.USER_EMAIL_TXT, userMailid);
                    Set<String> userRoles = userToken.getUser().getRoleses().stream().map(Roles :: getRole).collect(Collectors.toSet());
                    userDetail.put(CommonConstants.ROLES, userRoles);
                    userDetail.put(CommonConstants.USER_ID,Long.valueOf(body.getSubject()));
                    userTokenService.updateToken(userToken);
                }else{
                	ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1009_ERROR_CODE,
                            CommonConstants.E1009_ERROR_DESCRIPTION);
                        throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);
                }
            }else{
            	ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1009_ERROR_CODE,
                        CommonConstants.E1009_ERROR_DESCRIPTION);
                    throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);
            }

        }
        catch (ExpiredJwtException e) {
            logger.error(e);
            ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1009_ERROR_CODE,
                CommonConstants.E1009_ERROR_DESCRIPTION);
            throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            logger.error(e);
            ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1009_ERROR_CODE,
                CommonConstants.E1009_ERROR_DESCRIPTION);
            throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);

        }

        return userDetail;

    }

	

       
}

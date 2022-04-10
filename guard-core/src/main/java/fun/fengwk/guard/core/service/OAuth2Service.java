package fun.fengwk.guard.core.service;

import fun.fengwk.guard.share.model.AuthorizationCodeRespDTO;
import fun.fengwk.guard.share.model.EmailPasswordAuthorizeReqDTO;
import fun.fengwk.guard.share.model.EmailVerificationCodeAuthorizeReqDTO;
import fun.fengwk.guard.share.model.MobilePasswordAuthorizeReqDTO;
import fun.fengwk.guard.share.model.MobileVerificationCodeAuthorizeReqDTO;
import fun.fengwk.guard.share.model.TokenDTO;
import fun.fengwk.guard.share.model.TokenReqDTO;
import fun.fengwk.guard.share.model.UsernamePasswordAuthorizeReqDTO;

/**
 * OAuth2服务
 *
 * @author fengwk
 */
public interface OAuth2Service {

    /**
     * 授权：用户名+密码
     *
     * @param authorizeReqDTO
     * @return
     */
    AuthorizationCodeRespDTO authorizeByUsernamePassword(UsernamePasswordAuthorizeReqDTO authorizeReqDTO);

    /**
     * 授权：邮箱+密码
     *
     * @param authorizeReqDTO
     * @return
     */
    AuthorizationCodeRespDTO authorizeByEmailPassword(EmailPasswordAuthorizeReqDTO authorizeReqDTO);

    /**
     * 授权：邮箱+验证码
     *
     * @param authorizeReqDTO
     * @return
     */
    AuthorizationCodeRespDTO authorizeByEmailVerificationCode(EmailVerificationCodeAuthorizeReqDTO authorizeReqDTO);

    /**
     * 授权：手机+密码
     *
     * @param authorizeReqDTO
     * @return
     */
    AuthorizationCodeRespDTO authorizeByMobilePassword(MobilePasswordAuthorizeReqDTO authorizeReqDTO);

    /**
     * 授权：手机+验证码
     *
     * @param authorizeReqDTO
     * @return
     */
    AuthorizationCodeRespDTO authorizeByMobileVerificationCode(MobileVerificationCodeAuthorizeReqDTO authorizeReqDTO);

    /**
     * 请求获取令牌。
     *
     * @param tokenReqDTO
     * @return
     */
    TokenDTO token(TokenReqDTO tokenReqDTO);

}

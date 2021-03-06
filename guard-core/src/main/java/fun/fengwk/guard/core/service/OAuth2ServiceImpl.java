package fun.fengwk.guard.core.service;

import fun.fengwk.convention4j.api.code.ErrorCodeFactory;
import fun.fengwk.convention4j.common.ConvertUtils;
import fun.fengwk.guard.aus.AbstractUserSystem;
import fun.fengwk.guard.aus.model.UserIdentityView;
import fun.fengwk.guard.aus.util.PasswordDigester;
import fun.fengwk.guard.core.dao.AuthorizationCodeDAO;
import fun.fengwk.guard.core.dao.ClientDAO;
import fun.fengwk.guard.core.manager.UserSystemManager;
import fun.fengwk.guard.core.model.AuthorizationCodeDO;
import fun.fengwk.guard.core.model.ClientDO;
import fun.fengwk.guard.share.constant.GuardCodeTable;
import fun.fengwk.guard.share.model.AuthorizationCodeRespDTO;
import fun.fengwk.guard.share.model.BaseAuthorizeReqDTO;
import fun.fengwk.guard.share.model.EmailPasswordAuthorizeReqDTO;
import fun.fengwk.guard.share.model.EmailVerificationCodeAuthorizeReqDTO;
import fun.fengwk.guard.share.model.MobilePasswordAuthorizeReqDTO;
import fun.fengwk.guard.share.model.MobileVerificationCodeAuthorizeReqDTO;
import fun.fengwk.guard.share.model.TokenDTO;
import fun.fengwk.guard.share.model.TokenReqDTO;
import fun.fengwk.guard.share.model.UsernamePasswordAuthorizeReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;

/**
 * @author fengwk
 */
@Slf4j
@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    private final ErrorCodeFactory errorCodeFactory;
    private final UserSystemManager userSystemManager;
    private final ClientDAO clientDAO;
    private final AuthorizationCodeDAO authorizationCodeDAO;

    public OAuth2ServiceImpl(ErrorCodeFactory errorCodeFactory,
                             UserSystemManager userSystemManager,
                             ClientDAO clientDAO,
                             AuthorizationCodeDAO authorizationCodeDAO) {
        this.errorCodeFactory = errorCodeFactory;
        this.userSystemManager = userSystemManager;
        this.clientDAO = clientDAO;
        this.authorizationCodeDAO = authorizationCodeDAO;
    }

    @Override
    public AuthorizationCodeRespDTO authorizeByUsernamePassword(UsernamePasswordAuthorizeReqDTO authorizeReqDTO) {
        return authorize(authorizeReqDTO, (req, userSystem) -> {
            // ??????????????????????????????
            UserIdentityView userIdentityView = userSystem.getUserIdentityViewByUsername(req.getUsername());

            // ????????????????????????????????????????????????????????????
            if (userIdentityView == null) {
                log.warn("user not found by username, username={}", req.getUsername());
                throw errorCodeFactory.create(GuardCodeTable.USER_NOT_FOUND).asThrowable();
            }

            // ?????????????????????
            checkPassword(userIdentityView, userSystem.getPasswordDigester(), req.getPassword());

            return userIdentityView;
        });
    }

    @Override
    public AuthorizationCodeRespDTO authorizeByEmailPassword(EmailPasswordAuthorizeReqDTO authorizeReqDTO) {
        return authorize(authorizeReqDTO, (req, userSystem) -> {
            // ??????????????????????????????
            UserIdentityView userIdentityView = userSystem.getUserIdentityViewByEmail(req.getEmail());

            // ?????????????????????????????????????????????????????????
            if (userIdentityView == null) {
                log.warn("user not found by email, email={}", req.getEmail());
                throw errorCodeFactory.create(GuardCodeTable.USER_NOT_FOUND).asThrowable();
            }

            // ?????????????????????
            checkPassword(userIdentityView, userSystem.getPasswordDigester(), req.getPassword());

            return userIdentityView;
        });
    }

    @Override
    public AuthorizationCodeRespDTO authorizeByEmailVerificationCode(EmailVerificationCodeAuthorizeReqDTO authorizeReqDTO) {
        return null;
    }

    @Override
    public AuthorizationCodeRespDTO authorizeByMobilePassword(MobilePasswordAuthorizeReqDTO authorizeReqDTO) {
        return authorize(authorizeReqDTO, (req, userSystem) -> {
            // ??????????????????????????????
            UserIdentityView userIdentityView = userSystem.getUserIdentityViewByMobile(req.getMobile());

            // ????????????????????????????????????????????????????????????
            if (userIdentityView == null) {
                log.warn("user not found by mobile, mobile={}", req.getMobile());
                throw errorCodeFactory.create(GuardCodeTable.USER_NOT_FOUND).asThrowable();
            }

            // ?????????????????????
            checkPassword(userIdentityView, userSystem.getPasswordDigester(), req.getPassword());

            return userIdentityView;
        });
    }

    @Override
    public AuthorizationCodeRespDTO authorizeByMobileVerificationCode(MobileVerificationCodeAuthorizeReqDTO authorizeReqDTO) {
        return null;
    }

    @Override
    public TokenDTO token(TokenReqDTO tokenReqDTO) {
        return null;
    }

    /**
     * ????????????????????????????????????
     *
     * @param authorizeReqDTO
     * @param checkAndGetIdentityFunc ???????????????????????????????????????????????????????????????????????????????????????????????????
     * @param <T>
     * @return
     */
    private <T extends BaseAuthorizeReqDTO> AuthorizationCodeRespDTO authorize(
        T authorizeReqDTO, BiFunction<T, AbstractUserSystem, UserIdentityView> checkAndGetIdentityFunc) {
        // ?????????????????????
        ClientDO clientDO = clientDAO.findByClientId(authorizeReqDTO.getClientId());

        // ??????????????????????????????????????????????????????
        if (clientDO == null) {
            log.warn("client not found, clientId={}", authorizeReqDTO.getClientId());
            throw errorCodeFactory.create(GuardCodeTable.CLIENT_NOT_FOUND).asThrowable();
        }

        // ?????????????????????????????????????????????
        if (!Objects.equals(ConvertUtils.int2bool(clientDO.getIsEnabled()), true)) {
            log.warn("client disabled, clientId={}", authorizeReqDTO.getClientId());
            throw errorCodeFactory.create(GuardCodeTable.CLIENT_DISABLED).asThrowable();
        }

        // ????????????????????????????????????????????????
        AbstractUserSystem userSystem = userSystemManager.getUserSystem(clientDO.getUserNamespace());

        // ??????????????????????????????????????????????????????
        if (userSystem == null) {
            log.warn("user system not found, userNamespace={}", clientDO.getUserNamespace());
            throw errorCodeFactory.create(GuardCodeTable.CLIENT_NOT_FOUND).asThrowable();
        }

        // ??????????????????
        UserIdentityView userIdentityView = checkAndGetIdentityFunc.apply(authorizeReqDTO, userSystem);

        // ??????????????????????????????
        AuthorizationCodeDO authorizationCodeDO = newAuthorizationCodeDO(
            authorizeReqDTO, userIdentityView.getUserId(), clientDO.getAuthorizationCodeExpiresIn());
        authorizationCodeDAO.insert(authorizationCodeDO);

        // ?????????????????????????????????
        AuthorizationCodeRespDTO authorizationCodeRespDTO = new AuthorizationCodeRespDTO();
        authorizationCodeRespDTO.setCode(authorizationCodeDO.getCode());
        authorizationCodeRespDTO.setState(authorizeReqDTO.getState());
        return authorizationCodeRespDTO;
    }

    /**
     * ????????????????????????
     *
     * @param authorizeReqDTO ??????????????????
     * @param userId ??????id???
     * @param authorizationCodeExpiresIn ???????????????????????????????????????
     * @return
     */
    private AuthorizationCodeDO newAuthorizationCodeDO(BaseAuthorizeReqDTO authorizeReqDTO, String userId, long authorizationCodeExpiresIn) {
        LocalDateTime now = LocalDateTime.now();
        AuthorizationCodeDO authorizationCodeDO = new AuthorizationCodeDO();
        authorizationCodeDO.setGmtCreated(now);
        authorizationCodeDO.setGmtModified(now);
        authorizationCodeDO.setCode(genCode());
        authorizationCodeDO.setUserId(userId);
        authorizationCodeDO.setClientId(authorizeReqDTO.getClientId());
        authorizationCodeDO.setRedirectUri(authorizeReqDTO.getRedirectUri());
        authorizationCodeDO.setScope(authorizeReqDTO.getScope());
        authorizationCodeDO.setGmtExpired(now.plusSeconds(authorizationCodeExpiresIn));
        authorizationCodeDO.setIsUsed(ConvertUtils.INT_FALSE);
        return authorizationCodeDO;
    }

    private String genCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private void checkPassword(UserIdentityView userIdentityView, PasswordDigester passwordDigester, String inputPassword) {
        String inputPasswordDigest = passwordDigester.digest(inputPassword);
        if (!Objects.equals(inputPasswordDigest, userIdentityView.getPasswordDigest())) {
            log.warn("password error, userId={}, userPasswordDigest={}, inputPasswordDigest={}",
                userIdentityView.getUserId(), userIdentityView.getPasswordDigest(), inputPasswordDigest);
            throw errorCodeFactory.create(GuardCodeTable.PASSWORD_ERROR).asThrowable();
        }
    }

}

package fun.fengwk.guard.sus.core.dao;

import fun.fengwk.convention4j.api.page.LitePage;
import fun.fengwk.convention4j.api.page.LitePageQuery;
import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.guard.sus.core.SusCoreTestApplication;
import fun.fengwk.guard.sus.core.model.UserDO;
import fun.fengwk.guard.sus.core.model.UserIdentityBO;
import fun.fengwk.guard.sus.core.model.UserPropertiesBO;
import fun.fengwk.guard.sus.share.model.UserQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author fengwk
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SusCoreTestApplication.class)
public class UserDAOTest {

    private static final String TEST_NAMESPACE = "test";

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserIdentityDAO userIdentityDAO;
    @Autowired
    private UserPropertiesDAO userPropertiesDAO;

    @Transactional
    @Test
    public void test() {
        long userId = 1L;
        long version = 0L;
        String username = "UserDAOTest_fengwk";
        String passwordDigest = "xxxxx";
        String mobile = "15706846666";
        String email = "759543714@qq.com";
        String properties = "{\"age\":12}";

        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        userDO.setGmtCreated(LocalDateTime.now());
        userDO.setGmtModified(LocalDateTime.now());
        userDO.setVersion(version);
        userDO.setUsername(username);
        userDO.setPasswordDigest(passwordDigest);
        userDO.setMobile(mobile);
        userDO.setEmail(email);
        userDO.setProperties(properties);

        userDAO.insert(TEST_NAMESPACE, userDO);

        check(userDO, userDAO.findByUserIdAndVersion(TEST_NAMESPACE, userId, version));
        check(userDO, userDAO.findByUsername(TEST_NAMESPACE, username));
        check(userDO, userDAO.findByEmail(TEST_NAMESPACE, email));
        check(userDO, userDAO.findByMobile(TEST_NAMESPACE, mobile));

        PageQuery pageQuery = new PageQuery(1, 10);
        UserQuery userQuery = new UserQuery();
        userQuery.setUsernamePrefix("UserDAOTest_");
        userQuery.setEmailPredix("75");
        userQuery.setMobilePrefix("15");
        Page<UserDO> page = userDAO.page(TEST_NAMESPACE, pageQuery, userQuery);
        assert page.getTotalCount() == 1;
        check(userDO, page.getResults().get(0));

        LitePageQuery litePageQuery = new LitePageQuery(1, 10);
        LitePage<UserDO> litePage = userDAO.litePage(TEST_NAMESPACE, litePageQuery, userQuery);
        assert !litePage.hasNext();
        check(userDO, litePage.getResults().get(0));

        check(userDO, userIdentityDAO.getById(TEST_NAMESPACE, userId));
        check(userDO, userIdentityDAO.getByUsername(TEST_NAMESPACE, username));
        check(userDO, userIdentityDAO.getByEmail(TEST_NAMESPACE, email));
        check(userDO, userIdentityDAO.getByMobile(TEST_NAMESPACE, mobile));

        check(userDO, userPropertiesDAO.getById(TEST_NAMESPACE, userId));

        assert userDAO.deleteByUserIdAndVersion(TEST_NAMESPACE, userId, version);
    }

    private void check(UserDO userDO, UserDO found) {
        assert userDO.getUserId().equals(found.getUserId());
        assert Duration.between(userDO.getGmtCreated(), found.getGmtCreated()).abs().getSeconds() <= 1;
        assert Duration.between(userDO.getGmtModified(), found.getGmtModified()).abs().getSeconds() <= 1;
        assert userDO.getVersion().equals(found.getVersion());
        assert userDO.getUsername().equals(found.getUsername());
        assert userDO.getPasswordDigest().equals(found.getPasswordDigest());
        assert userDO.getMobile().equals(found.getMobile());
        assert userDO.getEmail().equals(found.getEmail());
        assert userDO.getProperties().equals(found.getProperties());
    }

    private void check(UserDO userDO, UserIdentityBO found) {
        assert userDO.getUserId().equals(found.getUserId());
        assert Duration.between(userDO.getGmtCreated(), found.getGmtCreated()).abs().getSeconds() <= 1;
        assert Duration.between(userDO.getGmtModified(), found.getGmtModified()).abs().getSeconds() <= 1;
        assert userDO.getVersion().equals(found.getVersion());
        assert userDO.getUsername().equals(found.getUsername());
        assert userDO.getPasswordDigest().equals(found.getPasswordDigest());
        assert userDO.getMobile().equals(found.getMobile());
        assert userDO.getEmail().equals(found.getEmail());
    }

    private void check(UserDO userDO, UserPropertiesBO found) {
        assert userDO.getUserId().equals(found.getUserId());
        assert Duration.between(userDO.getGmtCreated(), found.getGmtCreated()).abs().getSeconds() <= 1;
        assert Duration.between(userDO.getGmtModified(), found.getGmtModified()).abs().getSeconds() <= 1;
        assert userDO.getVersion().equals(found.getVersion());
        assert userDO.getProperties().equals(found.getProperties());
    }

}

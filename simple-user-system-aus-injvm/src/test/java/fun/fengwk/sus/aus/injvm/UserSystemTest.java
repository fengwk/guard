package fun.fengwk.sus.aus.injvm;

import fun.fengwk.convention4j.common.page.LitePage;
import fun.fengwk.convention4j.common.page.LitePageQuery;
import fun.fengwk.convention4j.common.page.Page;
import fun.fengwk.convention4j.common.page.PageQuery;
import fun.fengwk.guard.aus.AbstractUserSystem;
import fun.fengwk.guard.aus.UserSystemFactoryRegistry;
import fun.fengwk.guard.aus.model.UserCreator;
import fun.fengwk.guard.aus.model.UserIdentityView;
import fun.fengwk.guard.aus.model.UserModifier;
import fun.fengwk.guard.aus.model.UserPropertiesView;
import fun.fengwk.guard.aus.model.UserQuery;
import fun.fengwk.guard.aus.model.UserView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;

/**
 * @author fengwk
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SusInjvmTestApplication.class)
public class UserSystemTest {

    @Autowired
    private UserSystemFactoryRegistry userSystemFactoryRegistry;

    @Transactional
    @Test
    public void test() {
        AbstractUserSystem userSystem = userSystemFactoryRegistry.get(
            SimpleUserSystem.class.getSimpleName(), userSystemFactory -> userSystemFactory.getUserSystem("test"));

        UserCreator userCreator = new UserCreator.Builder()
            .setUsername("fengwk")
            .setPasswordDigest("xxx")
            .setEmail("759543714@qq.com")
            .setMobile("15706888888")
            .putProperty("p1", "v1")
            .putProperty("p2", "v2")
            .build();
        UserView userView = userSystem.createUser(userCreator);
        check(userCreator, userView);

        UserModifier userModifier = new UserModifier.Builder(userView.getUserId(), userView.getVersion())
            .setUsername("mmm")
            .putProperty("p2", "vv2")
            .putProperty("p3", "v3")
            .build();
        UserView modifiedUserView = userSystem.modifyUser(userModifier);
        assert modifiedUserView.getUsername().equals("mmm");
        assert modifiedUserView.getProperties().get("p1").equals("v1");
        assert modifiedUserView.getProperties().get("p2").equals("vv2");
        assert modifiedUserView.getProperties().get("p3").equals("v3");
        assert modifiedUserView.getPasswordDigest().equals(userView.getPasswordDigest());
        assert modifiedUserView.getEmail().equals(userView.getEmail());
        assert modifiedUserView.getMobile().equals(userView.getMobile());

        check(userSystem.getUserIdentityViewByUserId(modifiedUserView.getUserId()), modifiedUserView);
        check(userSystem.getUserIdentityViewByUsername(modifiedUserView.getUsername()), modifiedUserView);
        check(userSystem.getUserIdentityViewByEmail(modifiedUserView.getEmail()), modifiedUserView);
        check(userSystem.getUserIdentityViewByMobile(modifiedUserView.getMobile()), modifiedUserView);

        check(userSystem.getUserPropertiesViewByUserId(modifiedUserView.getUserId()), modifiedUserView);

        check(userSystem.getUserViewByUserId(modifiedUserView.getUserId()), modifiedUserView);
        check(userSystem.getUserViewByUsername(modifiedUserView.getUsername()), modifiedUserView);
        check(userSystem.getUserViewByEmail(modifiedUserView.getEmail()), modifiedUserView);
        check(userSystem.getUserViewByMobile(modifiedUserView.getMobile()), modifiedUserView);

        PageQuery pageQuery = new PageQuery(1, 10);
        UserQuery userQuery = new UserQuery.Builder()
            .setUsernamePrefix(modifiedUserView.getUsername())
            .setEmailPrefix(modifiedUserView.getEmail())
            .setMobilePrefix(modifiedUserView.getMobile())
            .build();
        Page<UserView> page = userSystem.pageUserViews(pageQuery, userQuery);
        assert page.getTotalCount() == 1;
        check(page.getResults().get(0), modifiedUserView);

        LitePageQuery litePageQuery = new LitePageQuery(1, 10);
        LitePage<UserView> litePage = userSystem.litePageUserViews(litePageQuery, userQuery);
        assert !litePage.hasNext();
        check(litePage.getResults().get(0), modifiedUserView);

        userModifier = new UserModifier.Builder(userView.getUserId(), modifiedUserView.getVersion())
            .setUsername("mmm")
            .putProperty("p2", "vv2")
            .putProperty("p3", "v3")
            .build();
        UserView coveredUserView = userSystem.coverUser(userModifier);
        assert coveredUserView.getUsername().equals("mmm");
        assert !coveredUserView.getProperties().containsKey("p1");
        assert coveredUserView.getProperties().get("p2").equals("vv2");
        assert coveredUserView.getProperties().get("p3").equals("v3");
        assert coveredUserView.getPasswordDigest() == null;
        assert coveredUserView.getEmail() == null;
        assert coveredUserView.getMobile() == null;

        assert userSystem.deleteUser(coveredUserView.getUserId(), coveredUserView.getVersion());
        assert userSystem.getUserViewByUserId(coveredUserView.getUserId()) == null;
    }

    private void check(UserCreator userCreator, UserView userView) {
        assert userCreator.getUsername().equals(userView.getUsername());
        assert userCreator.getPasswordDigest().equals(userView.getPasswordDigest());
        assert userCreator.getEmail().equals(userView.getEmail());
        assert userCreator.getMobile().equals(userView.getMobile());
        assert userCreator.getProperties().equals(userView.getProperties());
    }

    private void check(UserIdentityView userIdentityView, UserView userView) {
        assert Objects.equals(userIdentityView.getUserId(), userView.getUserId());
        assert Objects.equals(userIdentityView.getUsername(), userView.getUsername());
        assert Objects.equals(userIdentityView.getPasswordDigest(), userView.getPasswordDigest());
        assert Objects.equals(userIdentityView.getMobile(), userView.getMobile());
        assert Objects.equals(userIdentityView.getEmail(), userView.getEmail());
        assert Duration.between(userIdentityView.getCreatedTime(), userView.getCreatedTime()).abs().getSeconds() <= 1L;
        assert Duration.between(userIdentityView.getModifiedTime(), userView.getModifiedTime()).abs().getSeconds() <= 1L;
        assert Objects.equals(userIdentityView.getVersion(), userView.getVersion());
    }

    private void check(UserPropertiesView userPropertiesView, UserView userView) {
        assert Objects.equals(userPropertiesView.getUserId(), userView.getUserId());
        assert Objects.equals(userPropertiesView.getProperties(), userView.getProperties());
        assert Duration.between(userPropertiesView.getCreatedTime(), userView.getCreatedTime()).abs().getSeconds() <= 1L;
        assert Duration.between(userPropertiesView.getModifiedTime(), userView.getModifiedTime()).abs().getSeconds() <= 1L;
        assert Objects.equals(userPropertiesView.getVersion(), userView.getVersion());
    }

    private void check(UserView userView1, UserView userView2) {
        assert Objects.equals(userView1.getUserId(), userView2.getUserId());
        assert Objects.equals(userView1.getUsername(), userView2.getUsername());
        assert Objects.equals(userView1.getPasswordDigest(), userView2.getPasswordDigest());
        assert Objects.equals(userView1.getMobile(), userView2.getMobile());
        assert Objects.equals(userView1.getEmail(), userView2.getEmail());
        assert Duration.between(userView1.getCreatedTime(), userView2.getCreatedTime()).abs().getSeconds() <= 1L;
        assert Duration.between(userView1.getModifiedTime(), userView2.getModifiedTime()).abs().getSeconds() <= 1L;
        assert Objects.equals(userView1.getPasswordDigest(), userView2.getPasswordDigest());
        assert Objects.equals(userView1.getVersion(), userView2.getVersion());
    }

}

package fun.fengwk.guard.sus.core.service;

import com.google.common.collect.ImmutableMap;
import fun.fengwk.guard.sus.core.SusCoreTestApplication;
import fun.fengwk.guard.sus.share.model.UserCreatorDTO;
import fun.fengwk.guard.sus.share.model.UserDTO;
import fun.fengwk.guard.sus.share.model.UserModifierDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fengwk
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SusCoreTestApplication.class)
public class UserServiceTest {

    private static final String TEST_NAMESPACE = "test";

    @Autowired
    private UserService userService;

    @Transactional
    @Test
    public void test() {
        UserCreatorDTO userCreatorDTO = new UserCreatorDTO();
        userCreatorDTO.setUsername("fengwk");
        userCreatorDTO.setPasswordDigest("xxxxx");
        userCreatorDTO.setMobile("15706846666");
        userCreatorDTO.setEmail("759543714@qq.com");
        userCreatorDTO.setProperties(ImmutableMap.of("age", "12"));
        UserDTO userDTO = userService.createUser(TEST_NAMESPACE, userCreatorDTO);
        check(userCreatorDTO, userDTO);

        UserModifierDTO userModifierDTO = new UserModifierDTO();
        userModifierDTO.setUserId(userDTO.getUserId());
        userModifierDTO.setUsername("fengwk_m");
        userModifierDTO.setPasswordDigest("xxxxx_m");
        userModifierDTO.setMobile("15706846668");
        userModifierDTO.setEmail("759543714@mm.com");
        userModifierDTO.setProperties(ImmutableMap.of("age", "12", "sex", "1"));
        userDTO = userService.modifyUser(TEST_NAMESPACE, userModifierDTO);
        check(userModifierDTO, userDTO);
    }

    private void check(UserCreatorDTO userCreatorDTO, UserDTO userDTO) {
        assert userCreatorDTO.getUsername().equals(userDTO.getUsername());
        assert userCreatorDTO.getPasswordDigest().equals(userDTO.getPasswordDigest());
        assert userCreatorDTO.getMobile().equals(userDTO.getMobile());
        assert userCreatorDTO.getEmail().equals(userDTO.getEmail());
        assert userCreatorDTO.getProperties().equals(userDTO.getProperties());
    }

    private void check(UserModifierDTO userModifierDTO, UserDTO userDTO) {
        assert userModifierDTO.getUsername().equals(userDTO.getUsername());
        assert userModifierDTO.getPasswordDigest().equals(userDTO.getPasswordDigest());
        assert userModifierDTO.getMobile().equals(userDTO.getMobile());
        assert userModifierDTO.getEmail().equals(userDTO.getEmail());
        assert userModifierDTO.getProperties().equals(userDTO.getProperties());
    }

}

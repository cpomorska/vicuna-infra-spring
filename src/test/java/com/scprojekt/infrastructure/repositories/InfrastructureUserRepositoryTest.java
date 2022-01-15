package com.scprojekt.infrastructure.repositories;

import com.scprojekt.domain.entities.User;
import com.scprojekt.domain.entities.UserType;
import com.scprojekt.infrastructure.data.JpaConfiguration;
import com.scprojekt.infrastructure.main.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, JpaConfiguration.class})
@TestPropertySource(locations = "classpath:application-test.yaml")
@ActiveProfiles("test")
@Transactional
public class InfrastructureUserRepositoryTest {

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    private InfrastructureUserRepository infrastructureUserRepository;

    @Before
    public void setUp() {
        User user = createTestUser();
        infrastructureUserRepository.createEntity(user);
    }


    @Test
    public void findAll() {
        List<User> result = infrastructureUserRepository.findAll();
        assertNotNull(result);
        assertEquals("Testuser",result.get(0).getUserName());
        assertEquals(1, result.get(0).getUserType().get(0).getUserTypeId());
    }

    @Test
    public void createUser() {
        UUID uuid1 = UUID.fromString("35fa10da-594a-4601-a7b7-0a707a3c1ce7");
        User user1 = createTestUser();
        user1.setUserName("Insertuser");
        user1.setUserNumber(uuid1);
        infrastructureUserRepository.createEntity(user1);

        List<User> result = infrastructureUserRepository.findAll();
        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals(uuid1, result.get(1).getUserNumber());
        assertEquals("Insertuser",result.get(1).getUserName());
    }

    private User createTestUser() {
        User user = new User();
        UserType userType = new UserType();
        List<UserType> userTypeList = new ArrayList<>();

        userType.setUserTypeId(1);
        userType.setUserRoleType("testrole");
        userType.setUserTypeDescription("Testuser");
        userTypeList.add(userType);

        user.setUserName("Testuser");
        user.setUserNumber(UUID.fromString("586c2084-d545-4fac-b7d3-2319382df14f"));
        user.setUserType(userTypeList);

        return user;
    }
}
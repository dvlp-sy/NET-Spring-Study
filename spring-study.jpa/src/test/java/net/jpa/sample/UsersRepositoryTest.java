package net.jpa.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsersRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testMember()
    {
        // given
        Users users = new Users();
        users.setName("memberA");

        // when
        Long saveId = userRepository.save(users);
        Users findUsers = userRepository.find(saveId);

        // then
        assertThat(users.getId()).isEqualTo(findUsers.getId());
        assertThat(users.getName()).isEqualTo(findUsers.getName());
        assertThat(users).isEqualTo(findUsers);
        assertThat(users).isSameAs(findUsers);

    }
  
}
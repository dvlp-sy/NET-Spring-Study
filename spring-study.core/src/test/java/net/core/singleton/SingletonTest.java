package net.core.singleton;

import net.core.AppConfig;
import net.core.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest
{
    @Test
    @DisplayName("싱글톤 테스트")
    void singleToneService()
    {
        // given
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // then
        assertThat(singletonService1).isEqualTo(singletonService2);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer()
    {
        // Spring Container
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService : " + memberService1);
        System.out.println("memberService : " + memberService2);

        // Spring Container = Singleton Container므로 테스트 성공
        assertThat(memberService1).isSameAs(memberService2);
    }

}

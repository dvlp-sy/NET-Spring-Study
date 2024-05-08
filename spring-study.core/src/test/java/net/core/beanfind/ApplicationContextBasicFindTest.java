package net.core.beanfind;

import net.core.AppConfig;
import net.core.service.MemberService;
import net.core.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextBasicFindTest
{
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void finBeanByName()
    {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        System.out.println("memberService = "+memberService);
        System.out.println("MemberServiceImpl.class = "+MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void finBeanByType()
    {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        System.out.println("memberService = "+memberService);
        System.out.println("MemberServiceImpl.class = "+MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void finBeanByName2()
    {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        System.out.println("memberService = "+memberService);
        System.out.println("MemberServiceImpl.class = "+MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void finBeanByNameX()
    {
        // 존재하지 않는 빈을 조회하려 하면 NoSuchBeanDefinitionException이 발생해야 한다
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
    }
}

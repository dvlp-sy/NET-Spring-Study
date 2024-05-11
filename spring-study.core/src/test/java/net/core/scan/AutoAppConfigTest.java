package net.core.scan;

import net.core.AutoAppConfig;
import net.core.service.MemberService;
import net.core.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest
{
    @Test
    void basicScan()
    {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig .class);
        // when
        MemberService memberService = ac.getBean(MemberService.class);
        // then
        assertThat(memberService).isInstanceOf(MemberService.class);

    }
}

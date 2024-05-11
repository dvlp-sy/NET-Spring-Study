package net.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 클래스 레벨에 붙는 어노테이션
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent { }

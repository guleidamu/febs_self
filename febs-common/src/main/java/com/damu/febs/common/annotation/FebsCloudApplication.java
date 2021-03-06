package com.damu.febs.common.annotation;

import com.damu.febs.common.selector.FebsCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsCloudApplicationSelector.class)
public @interface FebsCloudApplication {

}
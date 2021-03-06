package com.damu.febs.common.selector;

import com.damu.febs.common.configure.FebsAuthExceptionConfigure;
import com.damu.febs.common.configure.FebsServerProtectConfigure;
import com.damu.febs.common.entity.FebsOAuth2FeignConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class FebsCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                FebsAuthExceptionConfigure.class.getName(),
                FebsOAuth2FeignConfigure.class.getName(),
                FebsServerProtectConfigure.class.getName()
        };
    }
}

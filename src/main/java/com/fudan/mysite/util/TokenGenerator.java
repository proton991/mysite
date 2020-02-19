package com.fudan.mysite.util;

import org.springframework.stereotype.Component;

/**
 * @author cailei.lu
 * @description
 * @date 2018/8/3
 */
@Component
public interface TokenGenerator {

    String generate(String... strings);

}

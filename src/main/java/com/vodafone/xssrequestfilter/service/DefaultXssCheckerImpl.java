package com.vodafone.xssrequestfilter.service;

import static com.vodafone.xssrequestfilter.util.ConstantUtil.FILTER_PATTERNS;

import java.util.regex.Pattern;

/**
 * Default implementation of {@link XssChecker}, You can override this implementation, create your
 * custom  implementation of xssChecker and mask as a bean
 *
 * @author Ilyas ATAS
 */
public class DefaultXssCheckerImpl implements XssChecker {

  @Override
  public String checkAndThrow(String value) {
    if (value != null) {
      for (Pattern pattern : FILTER_PATTERNS) {
        if (pattern.matcher(value).find()) {
          throw new IllegalArgumentException("Xss error");
        }
      }
    }
    return value;
  }

}

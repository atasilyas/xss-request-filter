package com.vodafone.xssrequestfilter.service;

/**
 * xssChecker service is responsible for remove xss from targeted request. You can create your own
 * custom implementation of it.
 *
 * @author Ilyas ATAS
 */
public interface XssChecker {

  String checkAndThrow(String value);

}

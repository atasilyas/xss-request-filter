package com.vodafone.xssrequestfilter.service;

import com.vodafone.xssrequestfilter.httpwrapper.CaptureRequestWrapper;
import javax.servlet.ServletInputStream;

/**
 * ServletInputStreamXssFilterProvider to get ServletInputStream based on supported content type
 *
 * @author Ilyas ATAS
 */
public interface ServletInputStreamXssFilterProvider {

  ServletInputStream getInputStream(CaptureRequestWrapper captureRequestWrapper,
      XssChecker xssChecker);

  boolean isSupportedContentType(String contentType);

}

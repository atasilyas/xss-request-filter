package com.vodafone.xssrequestfilter.service;

import com.vodafone.xssrequestfilter.httpwrapper.CaptureRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.servlet.ServletInputStream;

/**
 * ServletRequestXssFilterManager will process ServletInputStream and BufferedReader for
 * CaptureRequestWrapper based on ServletInputStreamXssProviders.
 *
 * @author Ilyas ATAS
 */
public class ServletRequestXssFilterManager {

  private final List<ServletInputStreamXssFilterProvider> servletInputStreamXssFilterProviders;

  public ServletRequestXssFilterManager(
      List<ServletInputStreamXssFilterProvider> servletInputStreamXssFilterProviders) {
    this.servletInputStreamXssFilterProviders = servletInputStreamXssFilterProviders;
  }

  public ServletInputStream getServletInputStream(CaptureRequestWrapper captureRequestWrapper,
      XssChecker xssChecker)
      throws IOException {
    ServletInputStream servletInputStream = null;
    if (servletInputStreamXssFilterProviders != null) {
      for (ServletInputStreamXssFilterProvider servletInputStreamXssFilterProvider : servletInputStreamXssFilterProviders) {
        if (servletInputStreamXssFilterProvider != null
            && servletInputStreamXssFilterProvider.isSupportedContentType(
            captureRequestWrapper.getContentType())) {
          servletInputStream = servletInputStreamXssFilterProvider.getInputStream(
              captureRequestWrapper,
              xssChecker);
        }
      }
    }
    return servletInputStream != null ? servletInputStream
        : captureRequestWrapper.getRequest().getInputStream();
  }

  public BufferedReader getBufferedReader(CaptureRequestWrapper captureRequestWrapper,
      XssChecker xssChecker)
      throws IOException {
    BufferedReader bufferedReader = null;
    if (servletInputStreamXssFilterProviders != null) {
      for (ServletInputStreamXssFilterProvider servletInputStreamXssFilterProvider : servletInputStreamXssFilterProviders) {
        if (servletInputStreamXssFilterProvider != null
            && servletInputStreamXssFilterProvider.isSupportedContentType(
            captureRequestWrapper.getContentType())) {
          bufferedReader = new BufferedReader(
              new InputStreamReader(this.getServletInputStream(captureRequestWrapper, xssChecker)));
        }
      }
    }
    return bufferedReader == null ? captureRequestWrapper.getRequest().getReader() : bufferedReader;
  }

}

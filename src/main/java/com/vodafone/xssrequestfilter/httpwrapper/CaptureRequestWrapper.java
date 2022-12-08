package com.vodafone.xssrequestfilter.httpwrapper;

import com.vodafone.xssrequestfilter.service.XssChecker;
import com.vodafone.xssrequestfilter.service.ServletRequestXssFilterManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * This class is responsible for filter the XSS in request you can add or remove the XSS handling
 * logic in #xssChecker method in CaptureRequestWrapper, CustomXssFilter use this class for remove
 * xss in request.
 *
 * @author Ilyas ATAS
 */
public class CaptureRequestWrapper extends HttpServletRequestWrapper {

  private final XssChecker xssChecker;
  private final ServletRequestXssFilterManager servletRequestXssFilterManager;


  public CaptureRequestWrapper(HttpServletRequest request, XssChecker xssChecker,
      ServletRequestXssFilterManager servletRequestXssFilterManager) {
    super(request);
    if (Objects.isNull(xssChecker)) {
      throw new IllegalArgumentException("xssChecker can't be null");
    }
    this.xssChecker = xssChecker;
    this.servletRequestXssFilterManager = servletRequestXssFilterManager;
  }

  @Override
  public String[] getParameterValues(String parameter) {
    String[] values = super.getParameterValues(parameter);
    if (values == null) {
      return new String[0];
    }
    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = xssChecker.checkAndThrow(values[i]);
    }
    return encodedValues;
  }

  @Override
  public String getParameter(String parameter) {
    String value = super.getParameter(parameter);
    return xssChecker.checkAndThrow(value);
  }

  @Override
  public String getHeader(String name) {
    String value = super.getHeader(name);
    return xssChecker.checkAndThrow(value);
  }

  @Override
  public BufferedReader getReader() throws IOException {
    super.getReader();
    return servletRequestXssFilterManager.getBufferedReader(this, xssChecker);
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    super.getInputStream();
    return servletRequestXssFilterManager.getServletInputStream(this, xssChecker);
  }

}

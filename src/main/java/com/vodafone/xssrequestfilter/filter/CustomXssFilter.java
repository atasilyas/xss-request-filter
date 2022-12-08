package com.vodafone.xssrequestfilter.filter;

import com.vodafone.xssrequestfilter.annotation.XssFilter;
import com.vodafone.xssrequestfilter.config.XssFiltersConfiguration;
import com.vodafone.xssrequestfilter.httpwrapper.CaptureRequestWrapper;
import com.vodafone.xssrequestfilter.service.ServletRequestXssFilterManager;
import com.vodafone.xssrequestfilter.service.XssChecker;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


/**
 * <p>This filter will only work for request which action have annotated {@link XssFilter} (with
 * help of   {@link XssFiltersConfiguration})</p>
 *
 * @author Ilyas ATAS
 */
@RequiredArgsConstructor
public class CustomXssFilter implements Filter {

  private final XssChecker xssChecker;
  private final ServletRequestXssFilterManager servletRequestXssFilterManager;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    filterChain.doFilter(new CaptureRequestWrapper((HttpServletRequest) servletRequest, xssChecker,
            servletRequestXssFilterManager),
        servletResponse);
  }

}

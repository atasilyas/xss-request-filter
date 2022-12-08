//package com.vodafone.xssrequestfilter.httpwrapper;
//
//import static org.mockito.Mockito.when;
//
//import com.vodafone.xssrequestfilter.service.DefaultXssCheckerImpl;
//import com.vodafone.xssrequestfilter.service.XssChecker;
//import com.vodafone.xssrequestfilter.service.ServletRequestXssFilterManager;
//import javax.servlet.http.HttpServletRequest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class CaptureRequestWrapperTest {
//
//  @Mock
//  private HttpServletRequest httpServletRequest;
//
//  @Mock
//  private ServletRequestXssFilterManager servletRequestXssFilterManager;
//
//  @Test
//  void testShouldxssCheckerFromHeaderAndParameter() {
//    when(httpServletRequest.getHeader("alerts")).thenReturn("<script>devilInAction();</script>123");
//    when(httpServletRequest.getParameter("userName")).thenReturn(
//        "<script>alert(\"fun time\");</script>Ilyas");
//
//    XssChecker xssChecker = new DefaultXssCheckerImpl();
//    CaptureRequestWrapper captureRequestWrapper = new CaptureRequestWrapper(httpServletRequest,
//        xssChecker, servletRequestXssFilterManager);
//
//    Assertions.assertEquals("123", captureRequestWrapper.getHeader("alerts"));
//    Assertions.assertEquals("Ilyas", captureRequestWrapper.getParameter("userName"));
//  }
//
//}

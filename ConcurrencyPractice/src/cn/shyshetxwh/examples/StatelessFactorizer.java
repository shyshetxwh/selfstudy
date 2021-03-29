package cn.shyshetxwh.examples;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.math.BigInteger;


/**
 * FileName: StatelessFactorizer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 9:46
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(servletResponse, factors);
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {

    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{i};
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return new BigInteger("7");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

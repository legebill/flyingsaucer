package org.xhtmlrenderer.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xhtmlrenderer.util.Uu;

import java.io.File;
import java.util.Date;

public class NetBeansPageTest {

    private static final Logger log = LoggerFactory.getLogger(NetBeansPageTest.class);

    public static void main(String[] args) throws Exception {
        long total = 0;
        int cnt = 1;
        String demosDir = "d:/data/projects/xhtmlrenderer/demos";
        String page = demosDir + "/browser/xhtml/layout/multicol/glish/one.html";
        //String page = demosDir + "/browser/xhtml/hamlet.xhtml";
        //String page = demosDir + "/splash/splash.html";
        log.info("Testing with page " + page);
        for (int i = 0; i < cnt; i++) {
            Date start = new Date();
            Graphics2DRenderer.renderToImage(
                    new File(page).toURL().toExternalForm(),
                    700, 700);
            Date end = new Date();
            long diff = (end.getTime() - start.getTime());
            Uu.p("ms = " + diff);
            if (i > 4) total += diff;
        }
        long avg = total / cnt;
        log.info("average : " + avg);
    }
}

package p1.web;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import p1.Str4;

import java.text.DecimalFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/x")
@Slf4j
public class Cntrl1 {


    @Value("${c_me.d}")
    String d1;

    DecimalFormat df = new DecimalFormat("#.##");

    String getP(HttpServletRequest req, String nm, String def, int minLen) {
        String s = req.getParameter(nm);
        if (s == null || s.isEmpty() || (minLen > 0 && s.length() < minLen)) {
            return def;
        }
        return s;
    }

    @RequestMapping(value = "ca", method = RequestMethod.GET)
    public Str4 up1(HttpServletRequest req, HttpServletResponse response) {
        Str4 s4 = new Str4();
        s4.setS1(new Date().toString());
        try {
            double i = Double.parseDouble(getP(req, "i", "1", 1));
            double j = Double.parseDouble(getP(req, "j", "1", 1));
            String k = "" + getP(req, "k", "u", 1).charAt(0);

            double ans = 0;
            switch (k) {
                case "-":
                    ans = i - j;
                    break;
                case "*":
                    ans = i * j;
                    break;
                case "/":
                    ans = i / j;
                    break;
                case "^":
                    ans = Math.pow(i, j);
                    break;

                default:
                    k = "+";
                    ans = i + j;
            }
            s4.setS4(String.valueOf(ans));
            s4.setS2(i + ' ' + k + ' ' + j);
        } catch (Throwable e) {
            log.warn("Err " + e.getMessage() + "| " + e, e);
        }

        return s4;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET
            , produces = MediaType.TEXT_HTML_VALUE)
    public String hme() {
        return "hi x2 " + new java.util.Date();
    }

    @PostConstruct
    public void init(){
        String d = System.getProperty("spring.profiles.active");
        log.info("init ***");
        log.info(d1);
        log.info(d);
    }
}

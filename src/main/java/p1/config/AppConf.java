package p1.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class AppConf {

    @PostConstruct
    public void init(){

        try {
            System.out.println("Conf ttt " );
            File f = new File(".");
            System.out.println("XX " + f.getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

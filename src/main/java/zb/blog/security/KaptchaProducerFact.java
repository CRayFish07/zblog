package zb.blog.security;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
 * Created by zhmt on 2017/6/14.
 */
@Component
public class KaptchaProducerFact {
    @Bean
    public DefaultKaptcha captchaProducer(){
        DefaultKaptcha captchaProducer =new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_BORDER,"yes");
        //properties.setProperty("kaptcha.border.color","105,179,90");
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH,"150");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT,"45");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE,"40");
        //properties.setProperty("kaptcha.session.key","code");
        //选择不易混淆的字母
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING,"345678ACEFHKMNRSTUWXY");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,"5");
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR,"blue");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR,"blue");
        //properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM,"white");
        //properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO,"blue");
        
        //properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL,"com.google.code.kaptcha.impl.FishEyeGimpy");
        //properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL,"com.google.code.kaptcha.impl.ShadowGimpy");
        Config config=new Config(properties);
        captchaProducer.setConfig(config);
        return  captchaProducer;
    }
}

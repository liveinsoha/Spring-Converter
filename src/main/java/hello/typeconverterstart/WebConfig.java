package hello.typeconverterstart;

import hello.typeconverterstart.converter.IntegerToStringConverter;
import hello.typeconverterstart.converter.IpPortToStringConverter;
import hello.typeconverterstart.converter.StringToIntegerConverter;
import hello.typeconverterstart.converter.StringToIpPortConverter;
import hello.typeconverterstart.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //registry.addConverter(new IntegerToStringConverter());
        //registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        registry.addFormatter(new MyNumberFormatter());
    }

    /**
     * **인터페이스 분리 원칙 - ISP(Interface Segregation Principle)**
     * 인터페이스 분리 원칙은 클라이언트가 자신이 이용하지 않는 메서드에 의존하지 않아야 한다.
     * `DefaultConversionService` 는 다음 두 인터페이스를 구현했다.
     * `ConversionService` : 컨버터 사용에 초점
     * `ConverterRegistry` : 컨버터 등록에 초점
     * 이렇게 인터페이스를 분리하면 컨버터를 사용하는 클라이언트와 컨버터를 등록하고 관리하는 클라이언트의 관심사를
     * 명확하게 분리할 수 있다. 특히 컨버터를 사용하는 클라이언트는 `ConversionService` 만 의존하면 되므로, 컨버터
     * 를 어떻게 등록하고 관리하는지는 전혀 몰라도 된다. 결과적으로 컨버터를 사용하는 클라이언트는 꼭 필요한 메서드만
     * 알게된다. 이렇게 인터페이스를 분리하는 것을 `ISP` 라 한다.
     */

    /**
     * DefaultFormattingConversionService 상속 관계**
     * `FormattingConversionService` 는 `ConversionService` 관련 기능을 상속받기 때문에 결과적으로 컨버터도
     * 포맷터도 모두 등록할 수 있다. 그리고 사용할 때는 `ConversionService` 가 제공하는 `convert` 를 사용하면 된다.
     * 추가로 스프링 부트는 `DefaultFormattingConversionService` 를 상속 받은 `WebConversionService` 를
     * 내부에서 사용한다.
     */
}

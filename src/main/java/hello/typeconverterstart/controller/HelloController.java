package hello.typeconverterstart.controller;

import hello.typeconverterstart.type.IpPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println("integer data = " + data);
        return "ok";
    }

    @GetMapping("/hello-v3")
    public String helloV3(@ModelAttribute UserData userData) {
        System.out.println("UserData userData = " + userData);
        return "ok";
    }

    /**
     * `@RequestParam` 은 `@RequestParam` 을 처리하는 `ArgumentResolver` 인
     * `RequestParamMethodArgumentResolver` 에서 `ConversionService` 를 사용해서 타입을 변환한다. 부모 클
     * 래스와 다양한 외부 클래스를 호출하는 등 복잡한 내부 과정을 거치기 때문에 대략 이렇게 처리되는 것으로 이해해도 충
     * 분하다. 만약 더 깊이있게 확인하고 싶으면 `IpPortConverter` 에 디버그 브레이크 포인트를 걸어서 확인해보자.
     */
    @GetMapping("/ip-port")
    public String helloV3(@RequestParam("ipPort") IpPort ipPort) {
        System.out.println("IpPort = " + ipPort);
        return "ok";
    }

    @Data
    @RequiredArgsConstructor
    static class UserData {
        private final String data;
    }
}

package hello.typeconverterstart.controller;

import hello.typeconverterstart.type.IpPort;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConverterController {

    /**
     * 타임리프는 렌더링 시에 컨버터를 적용해서 렌더링 하는 방법을 편리하게 지원한다.
     * 이전까지는 문자를 객체로 변환했다면, 이번에는 그 반대로 객체를 문자로 변환하는 작업을 확인할 수 있다.
     */

    @GetMapping("converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));
        return "converter-view";
    }

    @GetMapping("converter-edit")
    public String converterEdit(Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);
        return "converter-form";
    }

    /**
     * 문자"127.0.0.1:8080"이 뷰에서는 문자의 상태로 존재하고 다시 Form객체로 변환하려고 하고,
     * 필드에 IpPort가 존재한다. IpPort로 변환하기 위해서 다시 컨버터를 사용한다
     *
     * GET /converter/edit`
     * `th:field` 가 자동으로 컨버전 서비스를 적용해주어서 `${{ipPort}}` 처럼 적용이 되었다. 따라서
     * `IpPort` `String` 으로 변환된다.
     * `POST /converter/edit`
     * `@ModelAttribute` 를 사용해서 `String` `IpPort` 로 변환된다
     */
    @PostMapping("converter-edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        model.addAttribute("ipPort", form.getIpPort());
        return "converter-view";
    }

    @Data
    static class Form {


        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }
}

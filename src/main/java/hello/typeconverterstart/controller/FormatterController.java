package hello.typeconverterstart.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm(Model model) {
        Form form = new Form();
        form.setNumber(10000);
        form.setLocalDateTime(LocalDateTime.now());
        model.addAttribute("form", form);

        return "formatter-form";
    }
    @PostMapping("/formatter/edit")
    public String formatterEdit(@ModelAttribute Form form) {
        log.info("form.getNumber() = {} , form.getLocalDateTime() = {}",form.getNumber(),form.getLocalDateTime());
        return "formatter-view";
    }

    @Data
    static class Form {
        @NumberFormat(pattern = "###,###") //문자와 객체로 서로 바꾼다.
        private Integer number;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }
}

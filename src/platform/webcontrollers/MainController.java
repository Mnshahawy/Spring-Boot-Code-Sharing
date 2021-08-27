package platform.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import platform.models.CodeSnippet;
import platform.services.CodeSnippetService;

@Controller
public class MainController {
    private final CodeSnippetService codeSnippetService;

    @Autowired
    public MainController(CodeSnippetService codeSnippetService){
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/code")
    public String getCode(Model model) {
        CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet();
        model.addAttribute("code", codeSnippet.getCode());
        model.addAttribute("date",codeSnippet.getDate());
        return "codeView";
    }

    @GetMapping("/code/new")
    public String addCode() {
        return "codeFormView";
    }
}

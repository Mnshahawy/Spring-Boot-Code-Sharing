package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.models.CodeSnippet;
import platform.services.CodeSnippetService;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Controller
public class CodeSnippetController {
    private final CodeSnippetService codeSnippetService;

    @Autowired
    public CodeSnippetController(CodeSnippetService codeSnippetService){
        this.codeSnippetService = codeSnippetService;
    }

    @Validated
    @GetMapping("/code/{id}")
    public String getCode(@PathVariable @NotBlank String id, Model model) {
        Optional<CodeSnippet> codeSnippetOpt = codeSnippetService.getCodeSnippetById(id);
        if(codeSnippetOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Code Snippet with id:" + id);
        }
        CodeSnippet codeSnippet = codeSnippetOpt.get();
        model.addAttribute("code", codeSnippet.getCode());
        model.addAttribute("time", codeSnippet.getTime());
        model.addAttribute("views",codeSnippet.getViews());
        model.addAttribute("isLastView", codeSnippet.isLastView());
        model.addAttribute("date", codeSnippet.getFormattedDateTime());
        return "codeView";
    }

    @GetMapping("/code/new")
    public String addCode() {
        return "codeFormView";
    }

    @GetMapping("/code/latest")
    public String getLatestCodeSnippets(Model model) {
        List<CodeSnippet> snippets = codeSnippetService.getLatestTen();
        model.addAttribute("snippets",snippets);
        return "latestView";
    }
}

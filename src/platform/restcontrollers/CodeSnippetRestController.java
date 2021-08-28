package platform.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.models.CodeSnippet;
import platform.services.CodeSnippetService;
import javax.validation.Valid;

@RestController
public class CodeSnippetRestController {
    private final CodeSnippetService codeSnippetService;

    @Autowired
    public CodeSnippetRestController(CodeSnippetService codeSnippetService){
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/api/code")
    public ResponseEntity<CodeSnippet> getCodeSnippet() {
        return new ResponseEntity<>(codeSnippetService.getCodeSnippet(), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<String> setCodeSnippet(@RequestBody @Valid CodeSnippet codeSnippet) {
        codeSnippetService.setCodeSnippet(codeSnippet);
        return new ResponseEntity<>("{}",HttpStatus.OK);
    }
}

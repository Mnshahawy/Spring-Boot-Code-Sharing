package platform.restcontrollers;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.models.CodeSnippet;
import platform.services.CodeSnippetService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class CodeSnippetRestController {
    private final CodeSnippetService codeSnippetService;

    @Autowired
    public CodeSnippetRestController(CodeSnippetService codeSnippetService){
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<CodeSnippet> getCodeSnippet(@PathVariable @Min(1) int id) {
        CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet(id);
        if(null == codeSnippet){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Code Snippet with id:" + id);
        }
        return new ResponseEntity<>(codeSnippet, HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public List<CodeSnippet> getLatestCodeSnippets() {
        return codeSnippetService.getLatestTen();
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String, String>> setCodeSnippet(@RequestBody @Valid CodeSnippet codeSnippet) {
        int id = codeSnippetService.getNextId();
        codeSnippetService.addCodeSnippet(codeSnippet, id);
        System.out.println("POST req with CodeSnippet Id: " + id);
        return new ResponseEntity<>(Map.of("id",String.valueOf(id)),HttpStatus.OK);
    }
}

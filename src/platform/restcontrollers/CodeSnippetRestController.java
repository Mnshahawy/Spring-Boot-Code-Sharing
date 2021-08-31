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
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CodeSnippetRestController {
    private final CodeSnippetService codeSnippetService;

    @Autowired
    public CodeSnippetRestController(CodeSnippetService codeSnippetService){
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<CodeSnippet> getCodeSnippet(@PathVariable @NotBlank String id) {
        Optional<CodeSnippet> codeSnippet = codeSnippetService.getCodeSnippetById(id);
        if(codeSnippet.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Code Snippet with id:" + id);
        }
        return new ResponseEntity<>(codeSnippet.get(), HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public List<CodeSnippet> getLatestCodeSnippets() {
        return codeSnippetService.getLatestTen();
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String, String>> setCodeSnippet(@RequestBody @Valid CodeSnippet codeSnippet) {
        return new ResponseEntity<>(
                Map.of("id", codeSnippetService.addCodeSnippet(codeSnippet).getId().toString()),
                HttpStatus.OK);
    }
}

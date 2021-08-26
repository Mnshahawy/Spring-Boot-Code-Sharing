package platform.services;

import org.springframework.stereotype.Service;
import platform.models.CodeSnippet;
import platform.util.Utils;

import java.time.LocalDateTime;

@Service
public class CodeSnippetService {
    private CodeSnippet codeSnippet;

    public CodeSnippetService(){
        this.codeSnippet = new CodeSnippet(Utils.TEST_CODE, Utils.getFormattedDateTime(LocalDateTime.now()));
    }

    public CodeSnippet getCodeSnippet() {
        codeSnippet.updateDate();
        return codeSnippet;
    }

    public void setCodeSnippet(CodeSnippet codeSnippet) {
        this.codeSnippet = codeSnippet;
    }
}

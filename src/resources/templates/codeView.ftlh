<!DOCTYPE HTML>
<html lang="en-US">
    <head>
        <title>Code</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link rel="stylesheet" href="/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.2.0/build/styles/default.min.css">
        <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.2.0/build/highlight.min.js"></script>
        <script>hljs.highlightAll({cssSelector: "#code_snippet"}); /*Deprecated API hljs.initHighlightingOnLoad();*/</script>
    </head>
    <body>
        <div class="mt-5 w-75 align-content-center">
            <div class="card" style="width: 100%;">
                <div class="card-body">
                    <h5 class="card-title">Posted: <span id="load_date">${date}</span></h5>
                    <#if views gt 0  >
                        <h6 class="card-subtitle mb-2 text-muted" id="views_restriction">${views} more views allowed</h6>
                    <#elseif isLastView == true >
                        <h6 class="card-subtitle mb-2 text-muted" id="views_restriction">0 more views allowed; This is the last view.</h6>
                    </#if>
                    <#if time gt 0  >
                        <h6 class="card-subtitle mb-2 text-muted" id="time_restriction">The code will be available for ${time} seconds</h6>
                    </#if>
                    <pre class="card-text" id="code_snippet"><code>${code}</code></pre>
                </div>
            </div>
        </div>
    </body>
</html>
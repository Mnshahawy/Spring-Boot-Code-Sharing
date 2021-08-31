<!DOCTYPE HTML>
<html lang="en-US">

<head>
    <title>Latest</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.2.0/build/styles/default.min.css">
    <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.2.0/build/highlight.min.js"></script>
    <script>hljs.highlightAll({cssSelector: "#code_snippet"}); /*Deprecated API hljs.initHighlightingOnLoad();*/</script>
</head>
<body>
<#list snippets as snippet>
    <div class="snippet-container">
        <span id="load_date">${snippet.getFormattedDateTime()}</span>
        <br>
        <pre id="code_snippet"><code>${snippet.code}</code></pre>
    </div>
</#list>
</body>
</html>
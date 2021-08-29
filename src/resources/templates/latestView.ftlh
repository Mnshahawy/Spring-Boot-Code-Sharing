<!DOCTYPE HTML>
<html lang="en-US">

<head>
    <title>Latest</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/static/style.css">
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
<!DOCTYPE HTML>
<html lang="en-US">

<head>
    <title>Code</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/static/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.2.0/build/styles/default.min.css">
    <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.2.0/build/highlight.min.js"></script>
    <script>hljs.highlightAll({cssSelector: "#code_snippet"}); /*Deprecated API hljs.initHighlightingOnLoad();*/</script>
</head>
<body>
    <div class="snippet-container">
        <span id="load_date">${date}</span>
        <br>
        <pre id="code_snippet"><code>${code}</code></pre>
    </div>
</body>
</html>
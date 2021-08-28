<!DOCTYPE HTML>
<html lang="en-US">

<head>
    <title>Latest</title>
    <link rel="stylesheet" href="/static/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<#list snippets as snippet>
    <div class="snippet-container">
        <span id="load_date">${snippet.date}</span>
        <br>
        <pre id="code_snippet">${snippet.code}</pre>
    </div>
</#list>
</body>
</html>
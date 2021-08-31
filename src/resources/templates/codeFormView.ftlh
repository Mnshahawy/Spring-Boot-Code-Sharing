<!DOCTYPE HTML>
<html lang="en-US">
    <head>
        <title>Create</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="/form.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h3 class="mt-5">Share a Code Snippet</h3>
            <br>
            <form class="row g-3 w-auto h-auto">
                <div class="mb-3">
                    <label for="code_snippet" class="form-label">Code Snippet</label>
                    <textarea class="form-control" id="code_snippet" rows="3"></textarea>
                </div>
                <div class="mb-3">
                    <label for="time_restriction" class="form-label">Time Restriction</label>
                    <input type="number" class="form-control" id="time_restriction" value=0>
                </div>
                <div class="mb-3">
                    <label for="views_restriction" class="form-label">Views Restriction</label>
                    <input type="number" class="form-control" id="views_restriction" value=0>
                </div>
                <div class="mb-3">
                    <button id="send_snippet" type="submit" class="btn btn-primary" onclick="send()">Submit</button>
                </div>
            </form>
        </div>
    </body>
</html>
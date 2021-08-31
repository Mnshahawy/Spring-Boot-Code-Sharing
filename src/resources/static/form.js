window.onload = () => {
    let form = document.getElementById('code_form');
    let alertPlaceholder = document.getElementById('alert_placeholder');

    form.onsubmit = (event) => {
        event.preventDefault();
        let data = {}
        let fd = new FormData(form);
        for (let [key, prop] of fd) {
            data[key] = prop;
        }
        data = JSON.stringify(data, null, 2);

        fetch('/api/code/new/', {
            method: 'POST',
            credentials: 'same-origin',
            mode: 'same-origin',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: data
        }).then((response)=> {
            if (response.ok) {
                response.json().then((content) => {
                    alert("Success! We successfully added your code snippet with id: " + content.id, 'success');
                    form.reset();
                });
            } else {
                alert("Oops! Something went wrong on the server side. HTTP code: " + response.status, 'danger');
            }
        });
    }

    /** From BootStrap Docs **/
    function alert(message, type) {
        let wrapper = document.createElement('div');
        wrapper.innerHTML = '<div class="alert alert-' + type + ' alert-dismissible" role="alert">' + message +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
        alertPlaceholder.append(wrapper);
    }
}






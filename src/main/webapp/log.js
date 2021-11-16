function validate() {
    var rsl = true;
    if ($('#email').val() === '') {
        alert($('#email').attr('title'));
        rsl = false;
    }
    if ($('#password').val() === '') {
        alert($('#password').attr('title'));
        rsl = false;
    }
    return rsl;
}

function checkUser() {
    event.preventDefault();
    let name = $('#email').val();
    let pas = $('#password').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cinema/auth.do',
        data: JSON.stringify({
            name: name,
            password: pas,
        }), dataType: 'text'
    }).done(function (data) {
        if (data !== "400 Bad Request") {
            localStorage.setItem("user", name);
            window.location.href = "http://localhost:8080/cinema/index.html";
        } else {
            alert("You entered incorrect login or password");
            document.getElementById('email').value='';
            document.getElementById('password').value='';
        }
    })
}
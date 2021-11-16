function validate() {
    var rsl = true;
    if ($('#login').val() === '') {
        alert($('#login').attr('title'));
        rsl = false;
    }
    if ($('#email').val() === '') {
        alert($('#email').attr('title'));
        rsl = false;
    }
    if ($('#password').val() === '') {
        alert($('#password').attr('title'));
        rsl = false;
    }
    if ($('#numb').val() === '') {
        alert($('#numb').attr('title'));
        rsl = false;
    }
    return rsl;
}

function addUser() {
    event.preventDefault();
    let name = $('#login').val();
    let email = $('#email').val();
    let pas = $('#password').val();
    let phone = $('#numb').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cinema/user.do',
        data: JSON.stringify({
            name: name,
            email: email,
            password: pas,
            phone: phone,
        }), dataType: 'text'
    }).done(function (data) {
        if (data === "200 OK") {
            window.location.href = "http://localhost:8080/cinema/log.html";
        } else {
            alert("User exist");
            document.getElementById('login').value='';
            document.getElementById('email').value='';
            document.getElementById('password').value='';
        }
    })
}
$(document).ready(function () {
    getText();
});


/*
Заказ 1 билета
 */
function orderTicket(array) {
    let rowArray = array[0];
    let cellArray = array[1];
    let ix = rowArray.toString() + ',' + cellArray.toString();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cinema/tick.do',
        data: JSON.stringify({
            row: rowArray,
            cell: cellArray
        }), dataType: 'text'
    }).done(function (data) {
        if (data === '200 OK') {
            $('#notification').text('Билет' + ' на место ' + ix + ' успешно забронирован.');
        } else {
            $('#notification').prop( "style", "padding-top: 10px").prop("style", "color:#923C53");
            $('#notification').text('Упс, место ' + rowArray +',' + cellArray + ' уже кто-то выкупил');
        }
    })
}

$(document).on("click", ".paybut", function () {
    alert('pay');
    var id = $(this).attr('id');
    alert(typeof (id));
    alert(id);
    var array = id.toString().split(',');
    orderTicket(array)
});

/*
вывод информации о билетах*/
function getText() {
    var array = JSON.parse(localStorage.getItem("arr"));
    $("#ta").empty();
    let ind = 0;
    let rsl = "";

    for (let i = 0; i < array.length - 1; i = i + 2) {
        ind++;
        let row = array[i];
        let cell = array[i + 1];
        rsl += `<div class="new-todo">`
            + `<span class="ticksp" id="${row},${cell}">Билет ${ind}. Ряд ${row}. Место ${cell}. Сумма 500 рублей. </span>`
            + `<button id = "${row},${cell}"  type="button" class="paybut">Оплата</button>`
            + `</div>`
    }
    let sum = ind * 500;
    $("#ta").append(`<div class="new-todo" style="margin-bottom:10px">`
        + `<span class="alsp">Вы выбрали ${ind} билета</span>`
        + `</div>`
        + `<div class="new-todo-input">`
        + `</div>`
        + `<div class="new-todo" style="margin-bottom:10px">`
        + `<div class="new-todo-input">`
        + ` <input id="card"  type="number"  min="1000 0000 0000 0000" max="9999 9999 9999 9999"`
        + ` title="Введите номер вашей карты" autocomplete="off" placeholder="Введите номер вашей карты"/>`
        + `</div>`
        + `</div>`
        + `${rsl}`
        + `<div class="new-todo">`
        + `<span id="notification" class="alsp";>К оплате ${sum} рублей</span>`
        + `</div>`
        + `<button type="button" class="button_ac"><a href="http://localhost:8080/cinema/index.html">Выйти в главное меню</a>`
        + `</button>`)
}
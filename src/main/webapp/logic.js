const seats = new Set;
const tick = new Map;
let movId = null;
const indexSeats = new Set;
var choose = new Array();
var storeAr = new Array();

/*инфа сколько билетов в зале*/

function sizeItem() {
    let ty = seats.size;
    document.getElementById('bc').innerHTML = "В корзине " + ty + " мест(а)";
}


$(document).ready(function () {
    getMovies();
});

/*
заполняем выкупленные билеты
 */
function getTickets() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cinema/tick.do',
        dataType: 'json'
    }).done(function (data) {
        for (var ticket of data) {
            let rowTick = ticket.row;
            let cellTick = ticket.cell;
            let ind = rowTick.toString() + "," + cellTick.toString();
            indexSeats.add(ind);
            getHall();
        }
    });
}

/* выбор фильма*/
$(document).on("click", ".st", function () {
    seats.clear();
    tick.clear();
    var id = $(this).attr('id');
    setMovie(id);
    getTickets();
});

/*выбрать свободное место*/
$(document).on("click", ".check-mark", function () {
    var id = $(this).parent().attr('id');
    if (!indexSeats.has(id)) {
        seats.add(id);
        $(this).attr('class', 'check-mark checked');
        sizeItem();
        alert(seats.size)
    }
});

/*отменить выбранное место*/
$(document).on("click", ".check-mark.checked", function () {
    var idPar = $(this).parent().attr('id').toString();
    alert(idPar);
    alert(indexSeats.has(idPar));
    alert(seats.size);
    if (!indexSeats.has(idPar)) {
        $(this).attr('class', 'check-mark');
        seats.delete(idPar);
        alert(typeof (idPar));
        sizeItem();
    }
});


$(document).on("click", "#bc", function () {
    getSeats();
    localStorage.setItem("arr", JSON.stringify(storeAr));
    window.location.href = "http://localhost:8080/cinema/order.html";
});



/*
Кладет все выбранные места в map по количеству. Значение массив
 */
function getSeats() {
    tick.clear();
    let s;
    let ind = 1;
    for (let value of seats) {
        choose = value.toString().split(',');
        alert(choose[0]);
        alert(choose[1]);
        storeAr.push(choose[0]);
        storeAr.push(choose[1]);
        tick.set(ind++, choose);
    }
}

/*
Отправляет атрибут фильма
 */
function setMovie(id) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cinema/movid.do',
        data: id,
        dataType: 'text'
    }).done(function (data) {
        movId = id;
        alert(id)
    });
}

/*
Строит все дивы с фильмами
 */
function getMovies() {
    seats.clear();
    $("#mt").empty();
    let ind = 1;
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cinema/mov.do',
        dataType: 'json'
    }).done(function (data) {
        for (let mov of data) {
            let ids = mov.id;
            let name = mov.name;
            $("#mt").append(`<div class="mov">`
                + ` <span id="${ids}" class="st">${name}</span>`
                + `  </div>`);
        }
    });
}


/*создание таблицы с местами*/
function getHall() {
    $("#tb").empty();
    let s = "";
    for (let i = 1; i <= 6; i++) {
        let ind = i;
        s += `<tr>`
            + `<th>${ind} ряд</th>`;
        for (let j = 1; j <= 6; j++) {
            let index = i.toString() + "," + j.toString();
            let bool = indexSeats.has(index);
            const done = (bool === false) ? "check-mark" : "check-mark checked";
            let jm = j;
            s += ` <td>`
                + `<div class="check" id="${index}">`
                + `<div class="${done}"><img src="img/icon-check.svg">`
                + `</div>`
                + `</div>`
                + `</td>`
        }
        s = s + `</tr>`;
    }
    $('#tb').append(`<thead>`
        + `<div class="check">`
        + ` <th></th>`
        + ` <th>1 место</th>`
        + ` <th>2 место</th>`
        + ` <th>3 место</th>`
        + ` <th>4 место</th>`
        + ` <th>5 место</th>`
        + ` <th>6 место</th>`
        + ` </tr>`
        + `</thead>`
        + `<tbody>`
        + `${s}`
        + `</tbody>`
    )
};

/*
getMovies();*/

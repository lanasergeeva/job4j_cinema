$(document).ready(function () {
    getText();
});


/*
Заказ всех выбраных мест
 */
function makeOrder() {
    alert("makeOrder")
    var array = JSON.parse(localStorage.getItem("arr"));
    let ar = [];
    for (let i = 0; i < array.length - 1; i = i + 2) {
        alert("икл")
        let rowArray = array[i];
        let cellArray = array[i + 1];
        alert(rowArray);
        alert(cellArray)
        ar.push(rowArray);
        ar.push(cellArray);
        orderTicket(ar)
        ar.pop();
        ar.pop();
    }
}

    /*
    Заказ 1 билета
     */
    function orderTicket(array) {
        let rowArray = array[0];
        let cellArray = array[1];
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/cinema/tick.do',
            data: JSON.stringify({
                row: rowArray,
                cell: cellArray
            }), dataType: 'text'
        }).done(function (data) {
            alert(rowArray)
        })
    }

    $(document).on("click", "#pay", function () {
        makeOrder();
    });


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
                + `<span>Билет ${ind}. Ряд ${row}. Место ${cell}</span>`
                + `</div>`
        }
        $("#ta").append(`<div class="new-todo" style="margin-bottom:20px">`
            + `<span>Вы выбрали ${ind} билета</span>`
            + `</div>`
            + `${rsl}`
            + `<div class="new-todo" style="margin-top: 20px">`
            + `<div class="new-todo-input">`
            + ` <input id="card"  type="number"  min="1000 0000 0000 0000" max="9999 9999 9999 9999"`
            + `title="Enter you card" autocomplete="off" placeholder="Enter you card..."/>`
            + `</div>`
            + `</div>`
            + `<button id="pay" type="button" class="button">Оплата</button>`)
    };
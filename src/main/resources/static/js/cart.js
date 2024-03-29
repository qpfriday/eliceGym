function remove_tr(This) {
    This.closest('tr').remove();
}

function remove_all_tr() {
    var table = document.getElementById('cart');
    for(var i=0; i <= table.rows.length; i++) {
        table.deleteRow(-1);
    }
}

function set_price(id) {
    var pIndex = String(id.substring(4, id.length));
    var cost_name = "cost-" + pIndex;
    var tot_name = "total-cost-" + pIndex;
    var qty = document.getElementById(id).value;
    var cost = to_num(cost_name);
    document.getElementById(tot_name).innerHTML = (qty.replace(",", "") * cost)
                                                      .toString()
                                                      .replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
}

function set_pay() {
    var sum = to_num("total-cost-1") + to_num("total-cost-2") + to_num("total-cost-3");
    document.getElementById('pay').innerHTML = sum
                                                    .toString()
                                                    .replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
}

function to_num(id) {
    var str = document.getElementById(id).innerText;
    return parseInt(str.slice(0,-2).replace(",",""));
}
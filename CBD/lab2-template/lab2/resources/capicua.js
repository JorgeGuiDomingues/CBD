capicua = function () {
    var numbers = db.phones.find().toArray();

    var capicuas = numbers.filter(function (phone) {
        var num = phone.components.number.toString();
        return num == num.split('').reverse().join('');
    });

    print("Capicua numbers in the collection:");
    printjson(capicuas);
}
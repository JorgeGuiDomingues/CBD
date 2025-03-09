prefix = function () {
    var prefixes = db.phones.aggregate([
        { $group: { _id: "$components.prefix", total: { $sum: 1 } } },
        { $project: { _id: 0, "prefix": "$id", total: 1 } }
    ]).toArray();

    print("Prefixes in the collection:");
    printjson(prefixes);
}
export function sendWithImage(method, url, data, image, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.status !== 200) callback("[" + xhr.status + "]" + xhr.responseText, null);
        else callback(null, JSON.parse(xhr.responseText));
    };
    xhr.open(method, url, true);
    if (!data) xhr.send();
    else {
        let fd = new FormData();
        for (let key in data) {
            fd.append(key, data[key]);
        }
        fd.append("file", image);
        xhr.send(fd);
    }
}

export function send(method, url, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.status !== 200) callback("[" + xhr.status + "]" + xhr.responseText, null);
        else callback(null, JSON.parse(xhr.responseText));
    };
    xhr.open(method, url, true);
    if (!data) xhr.send();
    else {
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(data));
    }
}
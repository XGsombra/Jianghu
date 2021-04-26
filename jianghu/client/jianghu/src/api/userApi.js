import axios from "axios"

let base = "http://localhost:8080";

let config = { headers: { 'Content-Type': 'application/json' } };

export function registerUser(username, email, phone, password, onSuccess, onError) {
    if (email === "") {
        email = null;
    }
    if (phone === "") {
        phone = null;
    }
    console.log("called registerUser");
    const data = JSON.stringify({ username, email, phone, password });
    axios.post(base + '/api/users/signup', data, config).then(onSuccess).catch(onError);
}

export function loginUser(account, password, onSuccess, onError) {
    console.log("called loginUser");
    let reqUrl = base + "/api/users/login/userId?";
    if (account.includes("@")) {
        reqUrl = reqUrl + "email=";
    } else if (!isNaN(account)) {
        reqUrl = reqUrl + "phone=";
    } else {
        alert("Please enter a valid account");
        return;
    }
    reqUrl = reqUrl + encodeURIComponent(account);
    axios.get(reqUrl, config).then(
        function (res) {
            let userId = res.data.userId;
            const credentials = JSON.stringify({ userId, password });
            axios.post(base + "/api/users/login/", credentials, config).then(
                onSuccess
            ).catch(
                onError
            );
        }).catch(
            onError
        );
}
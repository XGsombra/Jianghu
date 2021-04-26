import axios from "axios"
import { Redirect } from "react-router";

let base = "http://localhost:8080";

let config = { headers: { 'Content-Type': 'application/json' } };

export function registerUser(username, email, phone, password, onSuccess, onError) {
    console.log("called ggggg");
    const data = JSON.stringify({ username, email, phone, password });
    axios.post(base + '/api/users/signup', data, config).then(onSuccess).catch(onError);
}
import axios from "axios";

const baseURL = axios.create({
  baseURL: "http://34.64.138.49:8080",
});

export default baseURL;

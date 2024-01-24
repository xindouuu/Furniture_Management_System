import axios from "axios";
//Create an axios instance - request object for sending requests to the backend
const request = axios.create({
    timeout: 5000
})

// Request interceptor handling
// 1. Allows for unified handling of requests
// 2. For example, uniformly adding token, Content-Type, etc.

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8'
    return config
}, error => {
    return Promise.reject(error)
})

// Response interceptor
// Allows for unified handling of the returned results after calling an API

request.interceptors.response.use(
    response => {
// The 'response' here is the result returned by the backend
// Here, we assign the 'data' property of the response to 'res'
// So, when we get the result in the request method, it is 'response.data'

        let res = response.data
// If the response is a file, return it
        if (response.config.responseType === 'blob') {
            return res
        }
// If it is a string, convert it to a JSON object
        if (typeof res === 'string') {
// If 'res' is not null, convert it to a JSON object
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log("err", error)
        return Promise.reject(error);
    }
)

// Export the 'request' object so that it can be used in other components
export default request

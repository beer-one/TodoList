

import axios from "axios"

let instance = null;

class TodoApiClient {
  constructor() {
    this.url = process.env.VUE_APP_API_HOST;

    if (instance) return instance;
    instance = this;
  }

  getTodoItems(year, month) {
    return axios.get(
      `${this.url}/todoList?year=${year}&month=${month}`
    );
  }

  getTodoDetail(no) {
    return axios.get(
      `${this.url}/todoList/${no}`
    );
  }

  add(requestBody) {
    console.log("[add todo] requestBody : " + JSON.stringify(requestBody))
    return axios.post(
      `${this.url}/todoList`,
      requestBody
    );
  }

  edit(requestBody, no) {
    return axios.put(
      `${this.url}/todoList/${no}`,
      requestBody
    );
  }

  removeTodo(no) {
    return axios.delete(
      `${this.url}/todoList/${no}`
    );
  }

}

export default TodoApiClient;

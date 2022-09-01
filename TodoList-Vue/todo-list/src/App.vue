<template>
  <div id="app">
    <TodoHeader v-bind:propsYear="year" v-bind:propsMonth="month" @toNextMonth="toNextMonth" @toPrevMonth="toPrevMonth"></TodoHeader>
    <TodoInput v-on:addTodo="addTodo"></TodoInput>
    <TodoList v-bind:propsdata="todoItems" @removeTodo="removeTodo"></TodoList>
  </div>
</template>

<script>
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'
import TodoApiClient from '@/http/TodoApiClient.js'
import DateFormatter from '@/utils/DateFormatter.js'

export default {
  data () {
    return {
      todoItems: [],
      year: 0,
      month: 0
    }
  },
  created () {
    const current = new Date();
    this.year = current.getFullYear();
    this.month = current.getMonth() + 1;

    this.getTodoItems(this.year, this.month)
  },
  methods: {
    addTodo (todoItem) {
      let client = new TodoApiClient()
      client.add(todoItem);

      this.getTodoItems(this.year, this.month)
    },

    removeTodo (todoItem, index) {
      let client = new TodoApiClient();

      client.removeTodo(todoItem.no);
      this.todoItems.splice(index, 1);
    },

    getTodoItems(year, month) {
      let client = new TodoApiClient()

      client.getTodoItems(this.year, this.month)
        .then(res => {
          this.todoItems = res.data;
          console.log(res.data);

          let current = new Date();

          this.todoItems.map(function(item){
            item.proceeding = (current >= DateFormatter.toDate(item.startDateTime)) && (current < DateFormatter.toDate(item.endDateTime));
            return item;
          });
        });
    },

    toNextMonth() {
      this.month++;
      if (this.month > 12) {
        this.month = 1;
        this.year++;
      }

      this.getTodoItems(this.year, this.month)
    },

    toPrevMonth() {
      this.month--;
      if (this.month < 1) {
        this.month = 12;
        this.year--;
      }

      this.getTodoItems(this.year, this.month)
    },
  },
  components: {
    TodoHeader: TodoHeader,
    TodoInput: TodoInput,
    TodoList: TodoList,
    TodoFooter: TodoFooter
  }

}
</script>

<style>
  header {
    padding-bottom: 40px;
  }
  body {
    text-align: center;
    background-color: #F6F6F8;
  }
  input {
    border-style: groove;
    width: 200px;
  }
  button {
    border-stype: groove;
  }
  .shadow {
    box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03)
  }
</style>

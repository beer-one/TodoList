<template>
  <div id="app">
    <TodoHeader></TodoHeader>
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
    this.year = current.getYear();
    this.month = current.getMonth() + 1;

    this.getTodoItems(this.Year, this.month)
  },
  methods: {
    addTodo (todoItem) {
      let client = new TodoApiClient()
      client.add(todoItem);

      this.getTodoItems(this.page, this.pageSize)
    },
    removeTodo (todoItem, index) {
      let client = new TodoApiClient();

      client.removeTodo(todoItem.no);
      this.todoItems.splice(index, 1);
    },
    getTodoItems(page, pageSize) {
      let client = new TodoApiClient()

      client.getTodoItems(page, pageSize)
        .then(res => {
          this.todoItems = res.data;

          let current = new Date();

          this.todoItems.map(function(item){
            item.proceeding = (current >= DateFormatter.toDate(item.startDateTime)) && (current < DateFormatter.toDate(item.endDateTime));
            return item;
          });
        });
    }
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
